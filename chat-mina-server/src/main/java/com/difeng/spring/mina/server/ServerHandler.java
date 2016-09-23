package com.difeng.spring.mina.server;


import com.difeng.spring.enumics.LoginSourceCode;
import com.difeng.spring.mina.Message.MessageConstants;
import com.difeng.spring.mina.minaSession.SessionManager;
import com.difeng.spring.mina.minaSession.event.BaseEvent;
import com.difeng.spring.mina.minaSession.event.LoginEvent;
import com.difeng.spring.mina.minaSession.event.MessageEvent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.mina.core.future.CloseFuture;
import org.apache.mina.core.future.IoFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:mina的server的处理器
 * @author:difeng
 * @time:2016年6月12日 上午10:15:56
 */
public class ServerHandler  extends IoHandlerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
		logger.info("session created,sessionid=" + session.getId());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		super.sessionClosed(session);
        final IoSession temp = session;
		logger.info("session closed,sessionid=" + session.getId());
        CloseFuture future = session.close(true);
        future.addListener(new IoFutureListener() {
            public void operationComplete(IoFuture future) {
                if (future instanceof CloseFuture) {
                    Long userId = SessionManager.removeSession(temp);
                    //移除userId对应的在线用户
                    ((CloseFuture) future).setClosed();
                }
            }
        });

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		super.messageReceived(session, message);
		logger.info("received message(" + message + ")from session id=" + session.getId());
        //解析消息
		Gson gson = new Gson();
		HashMap<String, String> map = gson.fromJson((String) message, new TypeToken<Map<String, String>>() {
		}.getType());

		BaseEvent baseEvent = new BaseEvent(map);
		//消息类型
		String messageType = baseEvent.getMessageType();
        if (messageType != null) {

            if (messageType.equals(MessageConstants.LOGIN_EVENT)){
                //登录事件,用户登录,redis中更新在线用户,并记录IoSession
                LoginEvent event = new LoginEvent(baseEvent.getEventMap());
                // 记住用户的session
                SessionManager.addSession(event.getUserId(), session);

            }else if(messageType.equals(MessageConstants.LOGOUT_EVENT)){
                //登出事件,用户登出,redis中更新在线用户,并删除对应的IoSession

                // 移除用户的session
                SessionManager.removeSession(session);

            }else if(messageType.equals(MessageConstants.MESSAGE_EVENT)){

                MessageEvent from = new MessageEvent(baseEvent.getEventMap());
                MessageEvent to = new MessageEvent(baseEvent.getEventMap());

                SessionManager.sendMessage(from,to.getToUserId());

            }else if(messageType.equals(MessageConstants.NOTIFY_EVENT)){

            }else if(messageType.equals(MessageConstants.REPORT_EVENT)){

            }
        }
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.inputClosed(session);
	}

}

