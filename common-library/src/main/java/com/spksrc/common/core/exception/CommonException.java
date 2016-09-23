package com.spksrc.common.core.exception;

/**
 * Created by apple on 16/9/9.
 */
public class CommonException extends Exception {

    private static final long serialVersionUID = 8969215825932134325L;

    /** 定义code **/
    private String code;
    /** 异常抛出时的说明,解释 **/
    private String msg;

    private Throwable cause;

    public CommonException(){
        super();
    }

    public CommonException(String msg){
        super(msg);
    }

    public CommonException(String code,String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CommonException(String code,Throwable cause){
        super(cause);
        this.code = code;
    }

    public CommonException(String code,String msg,Throwable cause){
        super(code,cause);
        this.code = code;
        this.msg = msg;
        this.cause = cause;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
