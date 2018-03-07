package word.labradors.android.droid.support;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.util.Locale;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/8
 */

public class AppInfo {

  public String os;
  public String deviceName;
  public String deviceId;
  public String version;
  public int versionCode;
  public String channel;
  public int screenWidth;
  public int screenHeight;
  public String languageCode;

  private void initLanguageCode() {
    Locale locale = Locale.getDefault();
    String language = locale.getLanguage();
    if ("zh".equals(language)) {
      language = language + "-" + locale.getCountry();
    }
    this.languageCode = language;
  }

  private void initOs() {
    this.os = Build.MODEL + "," + Build.VERSION.SDK_INT + "," + Build.VERSION.RELEASE;
  }

  private void initMetrics() {
    this.screenWidth = ScreenUtils.getScreenWidth();
    this.screenHeight = ScreenUtils.getScreenHeight();
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) private void initDeviceId() {
    this.deviceId = Installation.genInstallationId();
  }

  private void initVersion(Context context) {
    PackageManager packageManager = context.getPackageManager();
    PackageInfo packInfo = null;
    try {
      packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }

    String version = "";
    int code = 0;
    if (packInfo != null) {
      version = packInfo.versionName;
      code = packInfo.versionCode;
    }
    this.version = version;
    this.versionCode = code;
  }

  private void initUmengChannel(Context context) {
    this.channel = AndroidUtilities.getMetaData(context, "UMENG_CHANNEL");
  }

  private void initDeviceName() {
    this.deviceName = Build.DEVICE;
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) public AppInfo(Context context) {
    initLanguageCode();
    initDeviceId();
    initVersion(context);
    initUmengChannel(context);
    initOs();
    initDeviceName();
    initMetrics();
  }
}
