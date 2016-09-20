package com.ihongqiqu.webactivity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    /**
     * 启动 Web 容器页面
     *
     * @param from
     * @param url  URL 链接
     */
    public static void launch(@NonNull Activity from, @NonNull String url, String title) {
        Intent intent = new Intent(from, WebActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        from.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");

        setTitle(title);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_web, WebFragment.newInstance(url));
        transaction.commit();
    }
}
