package com.ihongqiqu.webactivity.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.ihongqiqu.webactivity.R;
import com.ihongqiqu.webactivity.WebActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                WebActivity.launch(this, "http://ihongqiqu.com", "标题");
                break;
            default:

                break;
        }
    }
}
