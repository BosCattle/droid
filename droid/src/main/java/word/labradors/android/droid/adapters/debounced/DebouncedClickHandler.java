package word.labradors.android.droid.adapters.debounced;

import android.os.SystemClock;
import android.view.View;

/**
 * @author Administrator
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */

public class DebouncedClickHandler {
  final static long MINIMUM_INTERVAL_MILLIS = 300;

  private long previousClickTimestamp;
  private final DebouncedListener debouncedOnClickListener;

  public DebouncedClickHandler(DebouncedListener debouncedOnClickListener) {
    this.debouncedOnClickListener = debouncedOnClickListener;
  }

  public boolean invokeDebouncedClick(int position, View view) {
    long currentTimestamp = SystemClock.uptimeMillis();
    boolean handled = false;
    if (currentTimestamp - previousClickTimestamp > MINIMUM_INTERVAL_MILLIS) {
      handled = debouncedOnClickListener.onDebouncedClick(view, position);
    }
    previousClickTimestamp = currentTimestamp;
    return handled;
  }
}
