package com.ihongqiqu.webactivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by zhenguo on 9/21/16.
 */

public class OriginUrlHandler extends UrlHandler {

    public OriginUrlHandler(Context context) {
        super(context);
    }

    @Override
    public boolean handlerUrl(@NonNull String url) {
        if (url.toLowerCase().startsWith("http")) {
            if (getNextUrlHandler() != null) {
                return getNextUrlHandler().handlerUrl(url);
            } else {
                return false;
            }
        } else {
            // Otherwise allow the OS to handle things like tel, mailto, etc.
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(intent);
            return true;
        }
    }

}
