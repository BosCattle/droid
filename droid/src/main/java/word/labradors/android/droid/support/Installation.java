package word.labradors.android.droid.support;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import com.google.common.base.Strings;
import java.util.UUID;

import static android.os.Build.BOARD;
import static android.os.Build.BRAND;
import static android.os.Build.DEVICE;
import static android.os.Build.DISPLAY;
import static android.os.Build.HOST;
import static android.os.Build.ID;
import static android.os.Build.MANUFACTURER;
import static android.os.Build.MODEL;
import static android.os.Build.PRODUCT;
import static android.os.Build.TAGS;
import static android.os.Build.TYPE;
import static android.os.Build.USER;
import static android.provider.Settings.Global.getString;
import static android.provider.Settings.Secure.ANDROID_ID;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/8
 */
public class Installation {
  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) public static String genInstallationId() {
    StringBuilder sb =
        new StringBuilder(getPackageName()).append(getAndroidId()).append(getPseudoUniqueID());

    final String imei = getIMEI();
    final String macAddress = getMacAddress();

    sb.append(macAddress);
    sb.append(imei);

    if (Strings.isNullOrEmpty(macAddress) && Strings.isNullOrEmpty(imei)) {
      sb.append(UUID.randomUUID().toString());
    }

    return Md5Util.md5(sb.toString());
  }

  private static String getPackageName() {
    return SupportApp.appContext().getPackageName();
  }

  private static String getPseudoUniqueID() {
    return "35"
        + BOARD.length() % 10
        + BRAND.length() % 10
        + DEVICE.length() % 10
        + DISPLAY.length() % 10
        + HOST.length() % 10
        + ID.length() % 10
        + MANUFACTURER.length() % 10
        + MODEL.length() % 10
        + PRODUCT.length() % 10
        + TAGS.length() % 10
        + TYPE.length() % 10
        + USER.length() % 10;
  }

  @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1) private static String getAndroidId() {
    return getString(SupportApp.appContext().getContentResolver(), ANDROID_ID);
  }

  @SuppressLint({ "MissingPermission", "HardwareIds" }) private static String getIMEI() {
    try {
      TelephonyManager telephonyManager =
          (TelephonyManager) SupportApp.appContext().getSystemService(Context.TELEPHONY_SERVICE);
      assert telephonyManager != null;
      return telephonyManager.getDeviceId();
    } catch (Exception exception) {
      // Nothing to do
    }
    return null;
  }

  private static String getMacAddress() {
    try {
      WifiManager e = (WifiManager) SupportApp.appContext()
          .getApplicationContext()
          .getSystemService(Context.WIFI_SERVICE);
      WifiInfo wInfo = e != null ? e.getConnectionInfo() : null;
      if (wInfo != null) {
        return wInfo.getMacAddress();
      }
    } catch (Exception exception) {
      // Nothing to do
    }
    return null;
  }
}
