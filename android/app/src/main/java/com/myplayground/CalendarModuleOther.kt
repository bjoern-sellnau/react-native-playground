package com.myplayground

import android.util.Log
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter

class CalendarModuleOther internal constructor(context: ReactApplicationContext?) :
    ReactContextBaseJavaModule(context) {
    private var eventCount = 0
    private fun sendEvent(
        reactContext: ReactContext,
        eventName: String,
        params: Int
    ) {
        reactContext
            .getJSModule(RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }

    override fun getName(): String {
        return "CalendarModuleOther"
    }

    @ReactMethod
    fun createCalendarEvent(name: String, location: String, callback: Callback) {
        Log.d(
            "CalendarModuleWithKotli", "Create event called with name: " + name
                    + " and location: " + location
        )
        callback.invoke("Data returned from Native CalendarWithKotlin Module")
    }

    @ReactMethod
    fun createCalendarEventPromise(name: String, location: String, promise: Promise) {
        try {
            Log.d(
                "CalendarModuleWithKotli", "Create promised-event called with name: " + name
                        + " and location: " + location
            )
            promise.resolve("Data return from promise in native CalendarWithKotlin module")
            eventCount++
            sendEvent(reactApplicationContext, "EventCount", eventCount)
        } catch (e: Exception) {
            promise.reject("Error returned from native CalendarWithKotlin module", e)
        }
    }
}