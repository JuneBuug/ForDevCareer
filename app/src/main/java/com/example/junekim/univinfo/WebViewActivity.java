package com.example.junekim.univinfo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_webview)
public class WebViewActivity extends FragmentActivity{


    @Extra("url")
    String url;

    @ViewById
    WebView webview;

    @ViewById
    TextView web_title;

    @ViewById
    ImageView ic_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Click
    void ic_cancel(){
        finish();
    }

    @AfterViews
    protected void afterViews(){

            webview.loadUrl(url);

            // 웹뷰 상단 title 설정
            webview.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedTitle(WebView view, String sTitle) {
                    super.onReceivedTitle(view, sTitle);
                    if (sTitle != null && sTitle.length() > 0) {
                        web_title.setText(sTitle);
                    } else {
                        web_title.setText("Web Page");
                    }
                }
            });


            webview.getSettings().setBuiltInZoomControls(true);
            webview.getSettings().setSupportZoom(true);
            webview.getSettings().setDisplayZoomControls(false); // 확대 / 축소 아이콘 안보이게 하기
//            webview.getSettings().setLoadWithOverviewMode(true); // 가로 사이즈를 맞추기 위함
            webview.getSettings().setJavaScriptEnabled(true); // 자바스크립트 허용
//            webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webview.getSettings().setUseWideViewPort(true);


    }



}
