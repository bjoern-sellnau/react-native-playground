package com.myplayground;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CalendarModule extends ReactContextBaseJavaModule {
    private int eventCount = 0;
    CalendarModule(ReactApplicationContext context) {
        super(context);
    }

    private void sendEvent(ReactContext reactContext,
                           String eventName,
                           int params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }

    @NonNull
    @Override
    public String getName(){
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location, Callback callback) {
        Log.d("CalendarModuleJava", "Create event called with name: " + name
                + " and location: " + location);
        callback.invoke("Data returned from Native Calendar Module");
    }

    @ReactMethod
    public void createCalendarEventPromise(String name, String location, Promise promise){
        try {
            Log.d("CalendarModuleJava", "Create promised-event called with name: " + name
                    + " and location: " + location);
            promise.resolve("Data return from promise in native calendar module");
            eventCount++;
            sendEvent(getReactApplicationContext(),"EventCount",eventCount);
        } catch(Exception e){
            promise.reject("Error returned from native calendar (java) module",e);
        }
    }
}





