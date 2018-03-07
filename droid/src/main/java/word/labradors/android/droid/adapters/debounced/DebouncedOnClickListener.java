package word.labradors.android.droid.adapters.debounced;

import android.view.View;
import word.labradors.android.droid.adapters.EasyViewHolder;

/**
 * @author Administrator
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */

public abstract class DebouncedOnClickListener implements EasyViewHolder.OnItemClickListener, DebouncedListener {
  private final DebouncedClickHandler debouncedClickHandler;

  protected DebouncedOnClickListener() {
    this.debouncedClickHandler = new DebouncedClickHandler(this);
  }

  @Override public void onItemClick(int position, View view) {
    debouncedClickHandler.invokeDebouncedClick(position, view);
  }
}
