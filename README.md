# WebActivity

  Android WebActivity

### 原生和 H5 介绍

  原生应用的用户体验不错，但是开发成本相对较高。原生应用的灵活性相对页不如 Web 页，切 Web 页相对原生有更强的控制力。原生应用出了 Bug，如果没有热补丁等热修复技术，应用就需要重新发版上线。Web 页面的控制权都在服务器端，故出了问题，在服务器端修复问题，就把线上的 Bug 修复完了。下图列出了它们的优势和劣势(包括混合型应用)：
  
  ![Native VS H5](http://ihongqiqu.com/sliders/assets/images/Android-Intro/NativeVSH5.png)
  
  原生和 Web 页各有优势和劣势，混合应用顺势而生。在原生应用中显示 Web 页也成为很流行的一种策略，即保证了主体功能的用户体验，又包含了 Web 页的灵活性和高控制性。

### 现状  
  
  现在，我们的应用中很多都需要能够支持显示 H5 页面。对于活动页和变动比较频繁以及尚在尝试阶段的需求，一般都会先用 H5 来实现。我们原生应用显示 H5 页面也称为应用的一个强需求，故做此组件。

### 实现
  
  Android 中使用 WebView 来显示网页。WebViewClient 帮助 WebView 处理各种通知、请求事件。WebChromeClient 主要辅助 WebView 处理 Javascript 的对话框、网站图标、网站 title、加载进度等。

#### 使用

  简单使用方法调用如下：

```java
    WebActivity.launch(this, "http://ihongqiqu.com", "标题");
```

#### 实现介绍

  UrlHandler 负责对 url 进行拦截。独立的业务可以实现自己的 UrlHandler，只需要在 MyWebViewClient 修改一下方法：
  
```java
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        FirstUrlHandler firstUrlHandler = new FirstUrlHandler(view.getContext());
        OriginUrlHandler originUrlHandler = new OriginUrlHandler(view.getContext());
        firstUrlHandler.setNextUrlHandler(originUrlHandler);
        // 此处可以设置自己的 UrlHandler 处理
        boolean isHandle = firstUrlHandler.handlerUrl(url);
        if (isHandle) {
            return true;
        } else {
            view.loadUrl(url);
            return false;
        }
    }
```

  WebView 设置在 WebFragment 中，代码如下：

```java
    private void initWebView() {
        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setSupportZoom(true);
        // 设置是否支持执行JS，如果设置为true会存在XSS攻击风险
        mWebView.getSettings().setJavaScriptEnabled(true);
        // mWebView.addJavascriptInterface(new HTMLheaderJavaScriptInterface(), "local_obj");
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // 水平不显示
        mWebView.setHorizontalScrollBarEnabled(false);
        // 垂直不显示
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient(mListener));
        mWebView.getSettings().setUseWideViewPort(true);
        // 安全考虑，防止密码泄漏，尤其是root过的手机
        mWebView.getSettings().setSavePassword(false);
        String ua = mWebView.getSettings().getUserAgentString();
        String appUA = ua + "; MYAPP";
        mWebView.getSettings().setUserAgentString(appUA);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        mWebView.getSettings().setDatabaseEnabled(true);
        String dir = getActivity().getApplicationContext().getDir("database", Context.MODE_PRIVATE).getPath();

        // 启用地理定位
        mWebView.getSettings().setGeolocationEnabled(true);
        // 设置定位的数据库路径
        mWebView.getSettings().setGeolocationDatabasePath(dir);

        // 最重要的方法，一定要设置，这就是出不来的主要原因
        mWebView.getSettings().setDomStorageEnabled(true);

        mWebView.loadUrl(mUrl);
    }
```