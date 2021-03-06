package com.example.essentialenglishwords

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.example.essentialenglishwords.Json.JsonProcess
import com.example.essentialenglishwords.databinding.ActivityExerciseBinding
import java.io.File

class ExerciseActivity : AppCompatActivity() {

    private val jsonProcess: JsonProcess = JsonProcess()
    private var positionUnit: Int = 0


    lateinit var exerciseBinding: ActivityExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exerciseBinding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(exerciseBinding.root)

        exerciseBinding.loopLearn.setOnClickListener {

            if (exerciseBinding.loopLearn.text == "Words") {
                val intent = Intent(this@ExerciseActivity, WordActivity::class.java)
                intent.putExtra("key", positionUnit)
                finish()
                startActivity(intent)

            }
        }

        val positionUnit = intent.getIntExtra("key", 0)

        val content = jsonProcess.exercise(this@ExerciseActivity, positionUnit).exercise1
        val content1=jsonProcess.exercise(this@ExerciseActivity, positionUnit).exercise2
        val answer=jsonProcess.exercise(this@ExerciseActivity, positionUnit).answer

        exerciseBinding.textExercise.settings.javaScriptEnabled = true
        //exerciseBinding.textExercise.addJavascriptInterface("JSBridge")

        exerciseBinding.textExercise.settings.builtInZoomControls = true
        exerciseBinding.textExercise.settings.displayZoomControls = false
        exerciseBinding.textExercise.settings.useWideViewPort = true
        exerciseBinding.textExercise.settings.loadWithOverviewMode = true
        exerciseBinding.textExercise.settings.defaultFontSize = 40

        exerciseBinding.textExercise.setInitialScale(0)

        exerciseBinding.textExercise.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        exerciseBinding.textExercise.settings.setAppCacheEnabled(true)
        exerciseBinding.textExercise.settings.setAppCachePath(getFileStreamPath("").path
                + "${File.separator}cache${File.separator}")

        exerciseBinding.textExercise.webViewClient = BuiltInWebViewClient()
        exerciseBinding.textExercise.webChromeClient = BuiltInChromeWebViewClient()


        exerciseBinding.textExercise.loadData(content+content1,"text/html","UTF-8")

        exerciseBinding.textAnswer.settings.javaScriptEnabled = true

        exerciseBinding.textAnswer.settings.builtInZoomControls = true
        exerciseBinding.textAnswer.settings.displayZoomControls = false
        exerciseBinding.textAnswer.settings.useWideViewPort = true
        exerciseBinding.textAnswer.settings.loadWithOverviewMode = true
        exerciseBinding.textAnswer.settings.defaultFontSize = 50

        exerciseBinding.textAnswer.setInitialScale(0)

        exerciseBinding.textAnswer.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        exerciseBinding.textAnswer.settings.setAppCacheEnabled(true)
        exerciseBinding.textAnswer.settings.setAppCachePath(getFileStreamPath("").path
                + "${File.separator}cache${File.separator}")

        exerciseBinding.textAnswer.webViewClient = BuiltInWebViewClient()
        exerciseBinding.textAnswer.webChromeClient = BuiltInChromeWebViewClient()

        exerciseBinding.textAnswer.loadData(answer,"text/html","UTF-8")


    }

    inner class BuiltInWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?,
        ): Boolean {
            if (request != null) {
                view?.loadUrl(request.url.toString())
            }

            return false
        }

        override fun onPageStarted(webView: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(webView, url, favicon)

        }

        override fun onPageFinished(webView: WebView?, url: String?) {
            super.onPageFinished(webView, url)

        }

    }

    inner class BuiltInChromeWebViewClient : WebChromeClient() {

    }
}