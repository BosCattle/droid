package word.labradors.android.droid.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import word.labradors.android.droid.provide.AppBean;

public interface UpdateApi {

    @GET("app/{id}")
    Observable<AppBean> checkUpdate(@Path("appId") String appId);
}
