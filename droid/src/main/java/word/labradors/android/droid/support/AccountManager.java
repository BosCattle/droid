package word.labradors.android.droid.support;

import android.content.Context;
import android.text.TextUtils;
import word.labradors.android.droid.DroidApp;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */
public enum AccountManager {
  INSTANCE;

  private final AuthPreferences authPreferences;
  private Account mCurrentAccount;
  private Context mContext;
  private boolean mIsLogin;

  AccountManager() {
    mContext = DroidApp.appContext();
    authPreferences = new AuthPreferences(mContext);
  }

  public boolean isLogin() {
    return authPreferences.isLogin();
  }

  public void logout() {
    mCurrentAccount = null;
    authPreferences.clear();
  }

  public void storeAccount(Account account) {
    mCurrentAccount = account;
    authPreferences.setToken(account.token());
    authPreferences.setUser(account.name());
    authPreferences.setUserData(account.toJson());
  }

  public void storeUser(Account account) {
    authPreferences.setUserData(account.toJson());
  }

  public <T extends Account> T getUser() {
    String accountJson = authPreferences.getUserData();
    if (!TextUtils.isEmpty(accountJson) && mContext instanceof AccountProvider) {
      return (T) ((AccountProvider) mContext).provideAccount(accountJson);
    }
    return null;
  }

  public String token() {
    return authPreferences.getToken();
  }

  public String user() {
    return authPreferences.getUser();
  }

  @SuppressWarnings("unchecked") public <T extends Account> T getCurrentAccount() {
    if (mCurrentAccount == null) {
      String accountJson = authPreferences.getUserData();
      if (!TextUtils.isEmpty(accountJson) && mContext instanceof AccountProvider) {
        mCurrentAccount = ((AccountProvider) mContext).provideAccount(accountJson);
      }
    }
    return (T) mCurrentAccount;
  }
}
