package word.labradors.android.droid.adapters.debounced;

import android.view.View;
import word.labradors.android.droid.adapters.EasyViewHolder;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */

public abstract class DebouncedOnLongClickListener implements DebouncedListener, EasyViewHolder.OnItemLongClickListener{
  private final DebouncedClickHandler debouncedClickHandler;
  protected DebouncedOnLongClickListener() {
    this.debouncedClickHandler = new DebouncedClickHandler(this);
  }
  @Override public boolean onLongItemClicked(int position, View view) {
    return debouncedClickHandler.invokeDebouncedClick(position, view);
  }
}
