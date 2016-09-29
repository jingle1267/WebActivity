package com.ihongqiqu.webactivity;

import android.graphics.Bitmap;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
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
        // 自定义404页面可以在这里设置
        super.onReceivedError(view, request, error);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        // 如果需要对某些网页进行处理可以在这里处理
        return super.shouldInterceptRequest(view, request);
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
