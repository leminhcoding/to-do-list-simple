package com.todo;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
  }

  /**
   * Override registerReceiver to add RECEIVER_EXPORTED flag on Android 14+ (API 34+).
   * React Native 0.64 does not pass this flag, causing a SecurityException.
   */
  @Override
  public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
    if (Build.VERSION.SDK_INT >= 34) {
      return super.registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED);
    }
    return super.registerReceiver(receiver, filter);
  }

  @Override
  public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, int flags) {
    if (Build.VERSION.SDK_INT >= 34 && flags == 0) {
      flags = Context.RECEIVER_EXPORTED;
    }
    return super.registerReceiver(receiver, filter, flags);
  }

  @Override
  public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler) {
    if (Build.VERSION.SDK_INT >= 34) {
      return super.registerReceiver(receiver, filter, broadcastPermission, scheduler, Context.RECEIVER_EXPORTED);
    }
    return super.registerReceiver(receiver, filter, broadcastPermission, scheduler);
  }

  @Override
  public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter, String broadcastPermission, Handler scheduler, int flags) {
    if (Build.VERSION.SDK_INT >= 34 && flags == 0) {
      flags = Context.RECEIVER_EXPORTED;
    }
    return super.registerReceiver(receiver, filter, broadcastPermission, scheduler, flags);
  }
}
