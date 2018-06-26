package word.labradors.android.droid.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import word.labradors.android.droid.provide.AppBean;

public interface UpdateApi {

    @GET("app/{id}")
    Observable<AppBean> checkUpdate(@Header("Authorization") String auth ,@Path("appId") String appId);
}
