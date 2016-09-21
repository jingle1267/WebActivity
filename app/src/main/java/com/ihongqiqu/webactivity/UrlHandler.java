package com.ihongqiqu.webactivity;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * URL 拦截基类
 * <p>
 * Created by zhenguo on 9/21/16.
 */

public abstract class UrlHandler {

    protected Context mContext;
    private UrlHandler nextUrlHandler;


    public UrlHandler(Context context) {
        mContext = context;
    }


    public void setContext(Context context) {
        mContext = context;
    }

    public UrlHandler getNextUrlHandler() {
        return nextUrlHandler;
    }

    public void setNextUrlHandler(UrlHandler nextUrlHandler) {
        this.nextUrlHandler = nextUrlHandler;
    }

    public abstract boolean handlerUrl(@NonNull String url);
}
