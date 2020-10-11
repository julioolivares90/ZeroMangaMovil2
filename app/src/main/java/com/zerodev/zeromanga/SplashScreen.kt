package com.zerodev.zeromanga

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.core.content.edit
import com.zerodev.zeromanga.utlities.constantes
import com.zerodev.zeromanga.utlities.constantes.URL_LECTOR

class SplashScreen : AppCompatActivity() {
    lateinit var webViewObtenerDatos : WebView

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)


        val pref = getSharedPreferences(getString(R.string.my_shared_preference), MODE_PRIVATE)

        webViewObtenerDatos = findViewById(R.id.wb_obtenerdatos)


        webViewObtenerDatos.webChromeClient = object : WebChromeClient(){

        }
        webViewObtenerDatos.webViewClient = object  : WebViewClient(){

            override fun onPageFinished(view: WebView?, url: String?) {

                super.onPageFinished(view, url)

               val cookieTumangaOnline =  getCookie(url!!,"tumangaonline_session")

                val _cfduid = getCookie(url,"_cfduid")
                Log.d("_cfduid =>",_cfduid)

                val _ga = getCookie(url,"_ga")

                Log.d("_ga => ",_ga)

                val __cf_bm = getCookie(url,"__cf_bm")

                Log.d("__cf_bm =>",__cf_bm)

                val XSRF_TOKEN = getCookie(url,"XSRF-TOKEN")
                
                Log.d("XSRF_TOKEN => ",XSRF_TOKEN)

                cookieTumangaOnline.let {cookie->

                    pref.edit {

                        putString(getString(R.string.key_tumangaonline_session),cookie)

                        putString(getString(R.string.key_cfduid),_cfduid)

                        putString(getString(R.string.key_ga),_ga)

                        putString(getString(R.string.key__cf_bm),__cf_bm)

                        putString(getString(R.string.key_XSRF_TOKEN),XSRF_TOKEN)

                        val intent = Intent(applicationContext,MainActivity::class.java)

                        startActivity(intent)

                        finish()
                    }
                }
            }
        }

        val settinsClient = webViewObtenerDatos.settings
        settinsClient.javaScriptEnabled = true

        webViewObtenerDatos.loadUrl(constantes.URL_LECTOR)
    }

    fun getCookie(siteName : String,cookieName: String) : String{

        var cookieValue = ""

        val cookieManager = CookieManager.getInstance()

        val cookies = cookieManager.getCookie(siteName)

        if (cookies !=  null){
            val temp = cookies.split(";")

            for (ara1 in temp){

                if (ara1.contains(cookieName)){

                    val temp1 = ara1.split("=")

                    cookieValue = temp1[1]

                    Log.d("COOKIE ${cookieName} => ",cookieValue)
                }
            }
        }
        return  cookieValue
    }
}
