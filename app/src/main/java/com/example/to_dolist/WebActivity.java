package com.example.to_dolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient( new MyWebViewClient());
        // включаем поддержку JavaScript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://geektech.kg");
    }
}
