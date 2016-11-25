package piashsotware.ltd.movietune.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import piashsotware.ltd.movietune.R;
import piashsotware.ltd.movietune.api.ApiClient;
import piashsotware.ltd.movietune.api.ApiMovieInterface;
import piashsotware.ltd.movietune.datamodel.MovieDetailPayloadModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {

    private final String TAG = "MovieDetail";
    private ApiMovieInterface mApiMovieInterface;
    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        Retrofit retrofit = ApiClient.getInstance(getActivity());
        mApiMovieInterface = retrofit.create(ApiMovieInterface.class);

        mApiMovieInterface.movieDetailNetworkCall().enqueue(
                new Callback<MovieDetailPayloadModel>() {
                    @Override
                    public void onResponse(Call<MovieDetailPayloadModel> call, Response<MovieDetailPayloadModel> response) {
                        Log.e(TAG, "onResponse: "+response.body().getBackdrop_path() );
                    }

                    @Override
                    public void onFailure(Call<MovieDetailPayloadModel> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.toString() );
                    }
                }
        );
        return view;
    }

}
