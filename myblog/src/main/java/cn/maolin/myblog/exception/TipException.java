package cn.maolin.myblog.exception;

/**
 * @author 张茂林
 * @since 2018/4/19 12:28
 */
public class TipException extends RuntimeException {
    private String msg;
    private int code = 500;

    public TipException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public TipException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public TipException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public TipException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
