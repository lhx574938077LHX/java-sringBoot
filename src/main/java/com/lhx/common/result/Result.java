package com.lhx.common.result;

import java.io.Serializable;

/**
 * @author lihongxiang
 * @date
 */
public class Result  implements Serializable {
    private static final long serialVersionUID = 5693188279817546271L;
    //返回代码
    private String code;
    //结果
    private boolean  success;
    //返回消息
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
