package yy.androidgeneralmodule.mvp.bean;

import java.util.List;

/**
 * Created by YY on 2019/6/15.
 * 网络请求解析Array基类
 */

public class BaseArrayBean<T> {
    private int code;

    private String msg;

    private List<T> mDates;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDates() {
        return mDates;
    }

    public void setDates(List<T> dates) {
        mDates = dates;
    }
}
