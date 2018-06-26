package word.labradors.android.droid.util;

import android.os.Build;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2018/4/2 0002
 */
public class DevicesUtils {

  /**
   * 获取设备信息
   */
  public static String getDeviceInfo() {
    StringBuffer sb = new StringBuffer();
    sb.append("主板：").append(Build.BOARD);
    sb.append("系统启动程序版本号：").append(Build.BOOTLOADER);
    sb.append("系统定制商：").append(Build.BRAND);
    sb.append("cpu指令集：").append(Build.CPU_ABI);
    sb.append("cpu指令集2:").append(Build.CPU_ABI2);
    sb.append("设置参数：").append(Build.DEVICE);
    sb.append("显示屏参数：").append(Build.DISPLAY);
    sb.append("www .2 cto.com无线电固件版本：").append(Build.getRadioVersion());
    sb.append("硬件识别码：").append(Build.FINGERPRINT);
    sb.append("硬件名称：").append(Build.HARDWARE);
    sb.append("HOST:").append(Build.HOST);
    sb.append("修订版本列表：").append(Build.ID);
    sb.append("硬件制造商：").append(Build.MANUFACTURER);
    sb.append("版本：").append(Build.MODEL);
    sb.append("硬件序列号：").append(Build.SERIAL);
    sb.append("手机制造商：").append(Build.PRODUCT);
    sb.append("描述Build的标签：").append(Build.TAGS);
    sb.append("TIME:").append(Build.TIME);
    sb.append("builder类型：").append(Build.TYPE);
    sb.append("USER:").append(Build.USER);
    return sb.toString();
  }

  public static String getManufacturer(){
    return Build.MANUFACTURER;
  }
}
