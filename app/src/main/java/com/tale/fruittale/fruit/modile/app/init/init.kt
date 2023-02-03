package com.tale.fruittale.fruit.modile.app.init

import android.app.Application
import com.onesignal.OneSignal
import com.tale.fruittale.fruit.modile.app.fly
import org.json.JSONObject

class init : Application() {

    override fun onCreate() {
        super.onCreate()
        try {

            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
            OneSignal.initWithContext(this)
            OneSignal.setAppId(keys.signal)
            OneSignal.setExternalUserId(fly!!, object :
                OneSignal.OSExternalUserIdUpdateCompletionHandler {
                override fun onSuccess(jsonObject: JSONObject) {

                }
                override fun onFailure(externalIdError: OneSignal.ExternalIdError) {

                }
            })


        }catch (e: Exception){

        }
    }
}