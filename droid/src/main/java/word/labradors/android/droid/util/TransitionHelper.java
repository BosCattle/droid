package word.labradors.android.droid.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.transition.Slide;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */

public class TransitionHelper {
  public static Pair<View, String>[] createSafeTransitionParticipants(@NonNull Activity activity,
      boolean includeStatusBar, @Nullable Pair... otherParticipants) {
    View decor = activity.getWindow().getDecorView();
    View statusBar = null;
    if (includeStatusBar) {
      statusBar = decor.findViewById(android.R.id.statusBarBackground);
    }
    View navBar = decor.findViewById(android.R.id.navigationBarBackground);
    List<Pair> participants = new ArrayList<>(3);
    addNonNullViewToTransitionParticipants(statusBar, participants);
    addNonNullViewToTransitionParticipants(navBar, participants);
    if (otherParticipants != null && !(otherParticipants.length == 1
        && otherParticipants[0] == null)) {
      participants.addAll(Arrays.asList(otherParticipants));
    }
    return participants.toArray(new Pair[participants.size()]);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  private static void addNonNullViewToTransitionParticipants(View view, List<Pair> participants) {
    if (view == null) {
      return;
    }
    participants.add(new Pair<>(view, view.getTransitionName()));
  }

  /**
   * @param context Context
   * @param time long
   * @param location int
   */
  @TargetApi(Build.VERSION_CODES.LOLLIPOP) public static void setupWindowAnimations(
      @NonNull Context context, @NonNull long time, @NonNull int location) {
    Activity activity = (Activity) context;
    Slide slideTransition = new Slide();
    slideTransition.setSlideEdge(location);
    slideTransition.setDuration(time);
    activity.getWindow().setReenterTransition(slideTransition);
    activity.getWindow().setExitTransition(slideTransition);
  }

  /**
   *
   * @param target
   * @param activity
   */
  public static void transitionToActivity(Class target, Activity activity) {
    final Pair<View, String>[] pairs =
        TransitionHelper.createSafeTransitionParticipants(activity, true);
    startActivity(target, pairs, activity);
  }

  /**
   *
   * @param target
   * @param pairs
   * @param activity
   */
  @TargetApi(Build.VERSION_CODES.JELLY_BEAN) private static void startActivity(Class target,
      Pair<View, String>[] pairs, Activity activity) {
    Intent i = new Intent(activity, target);
    ActivityOptionsCompat transitionActivityOptions =
        ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairs);
    activity.startActivity(i, transitionActivityOptions.toBundle());
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public static void transitionToActivity(Class target, View view, Activity activity) {
    final Pair<View, String>[] pairs =
        TransitionHelper.createSafeTransitionParticipants(activity, false,
            new Pair<>(view, "share"));
    startActivity(target, pairs, activity);
  }
}
