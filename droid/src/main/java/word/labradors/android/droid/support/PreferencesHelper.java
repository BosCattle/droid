package word.labradors.android.droid.support;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/8
 */

public enum  PreferencesHelper {
  INSTANCE;

  private SharedPreferences preferences;

  PreferencesHelper() {
    preferences = PreferenceManager.getDefaultSharedPreferences(SupportApp.getInstance());
  }

  public int getVersionCode() {
    return preferences.getInt("version_code", -1);
  }

  public void setVersionCode(int versionCode) {
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt("version_code", versionCode);
    SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
  }
}
