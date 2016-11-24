package piashsotware.ltd.movietune.api;


import java.util.List;

import piashsotware.ltd.movietune.datamodel.NewReliesePayloadModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by piash on 11/24/16.
 */

public interface ApiMovieInterface {

    @GET("now_playing?api_key=c37d3b40004717511adb2c1fbb15eda4& page=1")
    Call<NewReliesePayloadModel> newRelieseMovieNetworkCall();
}
