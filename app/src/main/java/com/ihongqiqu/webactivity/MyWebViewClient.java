package com.ihongqiqu.webactivity;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * MyWebViewClient
 * <p>
 * Created by zhenguo on 9/20/16.
 */
public class MyWebViewClient extends WebViewClient {

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        FirstUrlHandler firstUrlHandler = new FirstUrlHandler(view.getContext());
        OriginUrlHandler originUrlHandler = new OriginUrlHandler(view.getContext());
        firstUrlHandler.setNextUrlHandler(originUrlHandler);
        boolean isHandle = firstUrlHandler.handlerUrl(url);
        if (isHandle) {
            return true;
        } else {
            view.loadUrl(url);
            return false;
        }
    }
}
