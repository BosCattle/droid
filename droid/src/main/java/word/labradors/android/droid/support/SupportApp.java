package word.labradors.android.droid.support;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;
import word.labradors.android.droid.api.LabradorsApiService;
import word.labradors.android.droid.api.UpdateApi;
import word.labradors.android.droid.provide.ErrorAction;
import word.labradors.android.droid.provide.Message;
import word.labradors.android.droid.provide.UpdateManagerListener;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/8
 */

public class SupportApp extends Application {

    private static volatile Context sAppContext;
    @SuppressLint("StaticFieldLeak")
    private static volatile SupportApp mInstance;
    private static volatile Handler sAppHandler;
    private static volatile AppInfo mAppInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        sAppContext = null;
        mInstance = null;
        sAppHandler = null;
        mAppInfo = null;
    }

    /**
     * @return applicaton info
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static AppInfo appInfo() {
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
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void enterApp() {
        PreferencesHelper.INSTANCE.setVersionCode(appInfo().versionCode);
    }

    /**
     * 是否有版本更新显示引导页
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean shouldEnterApp() {
        return appInfo().versionCode > PreferencesHelper.INSTANCE.getVersionCode();
    }

    /**
     * app更新方法
     *
     * @param managerListener
     */
    @SuppressLint("CheckResult")
    public void updateApp(UpdateManagerListener managerListener) {
        try {
            ApplicationInfo appInfo = this.getPackageManager().getApplicationInfo(getPackageName(),
                    PackageManager.GET_META_DATA);
            String auth=appInfo.metaData.getString("LABRADOR_AUTH");
            String appId=appInfo.metaData.getString("LABRADOR_APPID");
            LabradorsApiService.getInstance().createApiService(UpdateApi.class)
                    .checkUpdate(appId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(appBean -> {
                        if (appInfo().version.equals(appBean.getVersion().getVersionName())){
                            managerListener.onNoUpdateAvailable();
                        }else {
                            managerListener.onUpdateAvailable(appBean);
                        }
                    }, new ErrorAction() {
                        @Override
                        public void message(Message message) {
                            Timber.e(message.getMessage());
                        }
                    });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }
}
