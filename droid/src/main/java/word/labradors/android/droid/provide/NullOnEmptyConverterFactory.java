package word.labradors.android.droid.provide;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class NullOnEmptyConverterFactory extends Converter.Factory {
  @Override
  public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
      Retrofit retrofit) {
    final Converter<ResponseBody, ?> delegate =
        retrofit.nextResponseBodyConverter(this, type, annotations);
    return (Converter<ResponseBody, Object>) body -> {
      if (body.contentLength() == 0) return new Message(200, "服务器没有返回值...");
      return delegate.convert(body);
    };
  }
}
