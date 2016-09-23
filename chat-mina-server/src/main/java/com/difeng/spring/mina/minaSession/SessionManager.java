package com.difeng.spring.mina.minaSession;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.difeng.spring.mina.minaSession.event.BaseEvent;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

public class SessionManager {
	//IoSession保存地,改成redis保存???
	private static final Map<Long, IoSession> clientSessions = new ConcurrentHashMap<Long, IoSession>();

	public static IoSession addSession(Long userId, IoSession session) {
		return clientSessions.put(userId, session);
	}

	public static IoSession removeSession(Long userId) {
		return clientSessions.remove(userId);
	}

	public static Long removeSession(IoSession session) {
		Long ret = null;

		Set<Long> ks = clientSessions.keySet();
		for (Long k : ks) {
			if (session.getId() == clientSessions.get(k).getId()) {
				clientSessions.remove(k);
				ret = k;
				break;
			}
		}

		return ret;
	}

	public static IoSession getSession(Long userId) {
		return clientSessions.get(userId);
	}

	public static Map<Long, IoSession> getSessions() {
		return clientSessions;
	}

    public static boolean sendMessage(BaseEvent ev, Long userId){
        IoSession session = SessionManager.getSession(userId);
        // 推送该消息
        WriteFuture writeResult = session.write(ev.toJson());
        writeResult.awaitUninterruptibly(1, TimeUnit.SECONDS);
        if (writeResult.isWritten()) {
            return true;
        }
        return false;
    }
}
