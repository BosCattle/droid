package word.labradors.android.droid.util;

import android.content.Context;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import word.labradors.android.droid.R;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/12
 */
public class RecyclerViewUtils {
  public static RecyclerView.ItemDecoration buildItemDecoration(Context context) {
    DividerItemDecoration decoration = new DividerItemDecoration(context);
    decoration.setInsets(buildInsets());
    final int dividerRes = buildDivider();
    if (dividerRes > 0) {
      decoration.setDivider(dividerRes);
    }
    return decoration;
  }

  public static @DrawableRes int buildDivider() {
    return 0;
  }

  public static @DimenRes int buildInsets() {
    return R.dimen.divider;
  }
}
