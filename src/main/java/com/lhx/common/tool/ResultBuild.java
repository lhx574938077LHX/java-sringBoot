package com.lhx.common.tool;

import com.lhx.common.constant.ResultConstant;
import com.lhx.common.exception.BusinessException;
import com.lhx.common.result.Result;

/**
 * @author lihongxiang
 * @date
 */
public class ResultBuild {

    public static void success(Result result) {
        result.setSuccess(true);
        result.setCode(ResultConstant.SUCCESS);
        result.setMsg("成功");
    }

    public static void exception(Result result, BusinessException e) {
        result.setSuccess(false);
        result.setCode(e.getCode());
        result.setMsg(e.getMessage());
    }

    public static void exception(Result result, Exception e) {
        result.setSuccess(false);
        result.setCode(ResultConstant.PARAMS_INAVAILABLE);
        result.setMsg(e.getMessage());
    }

    public static void fail(Result result, String code, String msg) {
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
    }

}
