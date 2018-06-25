package word.labradors.android.droid.provide;

/**
 * App更新回调
 */
public interface UpdateManagerListener {

    void onUpdateAvailable(AppBean bean);

    void onNoUpdateAvailable();
}
