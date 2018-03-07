package word.labradors.android.droid.support;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import com.google.common.base.Strings;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */
public class AuthPreferences {
  private static final String KEY_USER = "user";
  private static final String KEY_TOKEN = "token";
  private static final String KEY_USER_DATA = "user_data";

  private SharedPreferences preferences;

  public AuthPreferences(Context context) {
    preferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
  }

  public void setUser(String user) {
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(KEY_USER, user);
    SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
  }

  public void setToken(String token) {
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(KEY_TOKEN, token);
    SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
  }

  public void setUserData(String userData) {
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(KEY_USER_DATA, userData);
    SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
  }

  public String getUser() {
    return preferences.getString(KEY_USER, null);
  }

  public String getToken() {
    return preferences.getString(KEY_TOKEN, null);
  }

  public String getUserData() {
    return preferences.getString(KEY_USER_DATA, null);
  }

  public void clear() {
    SharedPreferences.Editor editor = preferences.edit().clear();
    SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
  }

  public boolean isLogin() {
    return !Strings.isNullOrEmpty(getToken());
  }

  public void setModel(String key, String value) {
    SharedPreferences.Editor editor = preferences.edit();
    editor.putString(key, value);
    SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
  }

  public String getModel(String key) {
    return preferences.getString(key, "1");
  }
}
