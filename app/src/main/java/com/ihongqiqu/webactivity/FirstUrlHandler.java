package com.ihongqiqu.webactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * Created by zhenguo on 9/21/16.
 */

public class FirstUrlHandler extends UrlHandler {

    public FirstUrlHandler(Context context) {
        super(context);
    }

    @Override
    public boolean handlerUrl(@NonNull String url) {
        if (url.contains("http://ihongqiqu.com/archives/")) {
            Toast.makeText(mContext, url, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.handlerUrl(url);
    }
}
