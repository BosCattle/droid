package word.labradors.android.droid.support;

import com.google.common.base.Strings;
import java.io.IOException;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Labradors
 * @version 1.0
 * @description class description
 * @date 2017/12/11
 */
public class DefaultHeaderInterceptor implements Interceptor {
  private Headers.Builder builder;

  public DefaultHeaderInterceptor(Headers.Builder builder) {
    this.builder = builder;
  }

  @Override public Response intercept(Chain chain) throws IOException {
    Request originalRequest = chain.request();
    if (builder == null) {
      builder = new Headers.Builder();
    }

    final String token = AccountManager.INSTANCE.token();
    if (!Strings.isNullOrEmpty(token) && Strings.isNullOrEmpty(builder.get("Authorization"))) {
      builder.add("Authorization", "Bearer " + token);
    }
    Request compressedRequest = originalRequest.newBuilder().headers(builder.build()).build();
    return chain.proceed(compressedRequest);
  }
}
