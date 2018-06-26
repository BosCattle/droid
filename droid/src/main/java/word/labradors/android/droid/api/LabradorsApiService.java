package word.labradors.android.droid.api;

import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import timber.log.Timber;
import word.labradors.android.droid.BuildConfig;
import word.labradors.android.droid.provide.NullOnEmptyConverterFactory;
import word.labradors.android.droid.support.DefaultHeaderInterceptor;

public class LabradorsApiService {

    private volatile static LabradorsApiService INSTANCE;

    private OkHttpClient sOkHttpClient;
    private Retrofit sRetrofit;

    private LabradorsApiService(String baseUrl) {

        sRetrofit = getRetrofit(baseUrl);
    }

    public static LabradorsApiService getInstance() {
        if (INSTANCE == null) {
            synchronized (LabradorsApiService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LabradorsApiService(BuildConfig.BASE_URL);
                }
            }
        }
        return INSTANCE;
    }


    private OkHttpClient getOkHttpClient() {
        Headers.Builder buildHeader = new Headers.Builder();
        if (sOkHttpClient == null) {
            synchronized (this) {
                if (sOkHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    builder.connectTimeout(10, TimeUnit.SECONDS);
                    HttpLoggingInterceptor httpLoggingInterceptor =
                            new HttpLoggingInterceptor(message -> Timber.i(message));
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    builder.addInterceptor(httpLoggingInterceptor);
                    builder.retryOnConnectionFailure(true);
                    sOkHttpClient = builder.build();
                }
            }
        }
        return sOkHttpClient;
    }

    private Retrofit getRetrofit(String baseUrl) {
        if (sRetrofit == null) {
            synchronized (this) {
                if (sRetrofit == null) {
                    sRetrofit = new Retrofit.Builder().client(getOkHttpClient())
                            .addConverterFactory(new NullOnEmptyConverterFactory())
                            .addConverterFactory(JacksonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(baseUrl)
                            .build();
                }
            }
        }
        return sRetrofit;
    }

    public <T> T createApiService(Class<T> apiService) {
        return sRetrofit.create(apiService);
    }
}
