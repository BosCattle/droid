package word.labradors.android.droid.support;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/8
 */

public class SupportApp extends Application {

  private static volatile Context sAppContext;
  private static volatile SupportApp mInstance;
  private static volatile Handler sAppHandler;
  private static volatile AppInfo mAppInfo;

  @Override public void onCreate() {
    super.onCreate();
    initialize();
  }

  @Override public void onTerminate() {
    super.onTerminate();
    sAppContext = null;
    mInstance = null;
    sAppHandler = null;
    mAppInfo = null;
  }

  /**
   * @return applicaton info
   */
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) public static AppInfo appInfo() {
    if (mAppInfo == null) {
      mAppInfo = new AppInfo(appContext());
    }
    return mAppInfo;
  }

  /**
   * @return application context
   */
  public static Context appContext() {
    return sAppContext;
  }

  /**
   * @return application resource
   */
  public static Resources appResources() {
    return appContext().getResources();
  }

  /**
   * @return Resource dimension value multiplied by the appropriate metric.
   */
  public static float dimen(@DimenRes int dimenRes) {
    return appResources().getDimension(dimenRes);
  }

  public static int color(@ColorRes int colorRes) {
    return ContextCompat.getColor(appContext(), colorRes);
  }

  public static Drawable drawable(@DrawableRes int drawableRes) {
    return ContextCompat.getDrawable(appContext(), drawableRes);
  }

  /**
   * @return application handler
   */
  public static Handler appHandler() {
    return sAppHandler;
  }

  /**
   * @return current application instance
   */
  public static SupportApp getInstance() {
    return mInstance;
  }

  private void initialize() {
    mInstance = this;
    sAppContext = getApplicationContext();
    sAppHandler = new Handler(sAppContext.getMainLooper());
  }

  /**
   * 更新本地versionCode
   */
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) public static void enterApp() {
    PreferencesHelper.INSTANCE.setVersionCode(appInfo().versionCode);
  }

  /**
   * 是否有版本更新显示引导页
   */
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) public static boolean shouldEnterApp() {
    return appInfo().versionCode > PreferencesHelper.INSTANCE.getVersionCode();
  }
}
