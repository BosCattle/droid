package word.labradors.android.droid.provide;

import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * @author Labradors
 * @version 1.0
 * @description RxJava2 Error Action
 * @date 2017/12/11
 */

public abstract class ErrorAction implements Consumer<Throwable> {

  @Override public void accept(Throwable throwable) throws Exception {
    Timber.e(throwable.toString());
    message(Message.handle(throwable));
  }

  public abstract void message(Message message);
}
