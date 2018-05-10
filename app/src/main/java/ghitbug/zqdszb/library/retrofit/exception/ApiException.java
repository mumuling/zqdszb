package ghitbug.zqdszb.library.retrofit.exception;

/**
 * 回调统一请求异常
 *
 * @version v1.0
 * @date 2017/3/14
 * @auth wcy
 * @company 重庆锐云科技有限公司
 */

public class ApiException extends Exception {
    /*错误码*/
    private int code;
    /*显示的信息*/
    private String displayMessage;
    private boolean isExecute = true;

    public ApiException(Throwable e) {
        super(e);
    }

    public ApiException(Throwable cause, @CodeException.CodeEp int code, String showMsg) {
        super(showMsg, cause);
        setCode(code);
        setDisplayMessage(showMsg);
    }

    @CodeException.CodeEp
    public int getCode() {
        return code;
    }

    public void setCode(@CodeException.CodeEp int code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public boolean isExecute() {
        return isExecute;
    }

    public void setExecute(boolean execute) {
        isExecute = execute;
    }
}
