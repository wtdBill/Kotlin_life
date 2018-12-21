package com.example.ypp.life.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.ProgressBar
import com.example.ypp.life.R
import com.example.ypp.life.application.HostRuntime
import com.example.ypp.life.application.SonicSessionClientImpl
import com.example.ypp.life.utils.StatusBarUtil
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import com.tencent.sonic.sdk.SonicSession
import com.tencent.sonic.sdk.SonicSessionConfig
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    private var url: String = ""
    private var sonicSession: SonicSession? = null
    private var sonicSessionClient: SonicSessionClientImpl = SonicSessionClientImpl();

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,127)
        url = intent.getStringExtra(PARAM_URL)

        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(HostRuntime(application), SonicConfig.Builder().build())
        }

        val sessionConfigBuilder = SonicSessionConfig.Builder()
        sonicSession = SonicEngine.getInstance().createSession(url, sessionConfigBuilder.build())
        if (null != sonicSession) {
            sonicSession!!.bindClient(sonicSessionClient)
        } else {
        }


        val webSettings = webView!!.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowContentAccess = true
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.savePassword = false
        webSettings.saveFormData = false
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true
        webView!!.canGoBack()
        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(webView!!)
            sonicSessionClient.clientReady()
        }

        webView!!.loadUrl(url)
        webView!!.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                if (sonicSession != null) {
                    sonicSession!!.sessionClient.pageFinish(url)
                }
            }

            @TargetApi(21)
            override fun shouldInterceptRequest(view: WebView, request: WebResourceRequest): WebResourceResponse? {
                return shouldInterceptRequest(view, request.url.toString())
            }

            override fun shouldInterceptRequest(view: WebView, url: String): WebResourceResponse? {
                return if (sonicSession != null) {
                    sonicSession!!.sessionClient.requestResource(url) as WebResourceResponse
                } else null
            }

        }

        webView!!.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                progressbar!!.progress = newProgress
                if (newProgress == 100){
                    progressbar!!.visibility = View.GONE
                }
                super.onProgressChanged(view, newProgress)
            }
        }
    }


    override fun onBackPressed() {
        if (webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (null != sonicSession) {
            sonicSession!!.destroy()
            sonicSession = null
        }
        super.onDestroy()
    }

    companion object {

        val PARAM_URL = "param_url"
        val PARAM_MODE = "param_mode"
    }
}
