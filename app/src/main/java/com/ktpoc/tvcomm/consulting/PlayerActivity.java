package com.ktpoc.tvcomm.consulting;

import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlayerActivity extends AppCompatActivity {

    private final String _TAG = "SONG";
    //private final String _roomUrl = "https://121.135.146.221:3000/users/amuzlab";
    //private final String _roomUrl = "https://amuzlab.iptime.org:3000/users/amuzlab";
    private final String _roomUrl = "https://tvcomm.dlinkddns.com:3000/users/amuzlab";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        WebView mWebView = (WebView)findViewById(R.id.web_view);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                Log.d(_TAG, "onPermissionRequest");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        request.grant(request.getResources());
                    }
                });
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        mWebView.loadUrl(_roomUrl);
        Log.i(_TAG, "Agent VERSION---->" + mWebView.getSettings().getUserAgentString());
    }
}
