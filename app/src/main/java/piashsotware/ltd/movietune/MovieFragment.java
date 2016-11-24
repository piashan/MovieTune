package piashsotware.ltd.movietune;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import piashsotware.ltd.movietune.adapter.MovieAdapter;
import piashsotware.ltd.movietune.api.ApiClient;
import piashsotware.ltd.movietune.api.ApiMovieInterface;
import piashsotware.ltd.movietune.datamodel.NewReliesePayloadModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private final String TAG = "Test";
    private ApiMovieInterface mApiMovieInterface;
    private RecyclerView mMovieRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mMovieAdapter;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);

        Retrofit retrofit = ApiClient.getInstance(getActivity());
        mApiMovieInterface = retrofit.create(ApiMovieInterface.class);
        mMovieRecyclerView = (RecyclerView)view.findViewById(R.id.movieRecyclerView);

        mLayoutManager =  new GridLayoutManager(getActivity(), 2);
        mMovieRecyclerView.setLayoutManager(mLayoutManager);


        mApiMovieInterface.newRelieseMovieNetworkCall().enqueue(
                new Callback<NewReliesePayloadModel>() {
                    @Override
                    public void onResponse(Call<NewReliesePayloadModel> call, Response<NewReliesePayloadModel> response) {
                        Log.e(TAG, "onResponse: "+response.body().getResults().get(0).getPoster_path() );

                        mMovieAdapter = new MovieAdapter(getActivity(), response.body().getResults());
                        mMovieRecyclerView.setAdapter(mMovieAdapter);
                    }

                    @Override
                    public void onFailure(Call<NewReliesePayloadModel> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.toString() );
                    }
                }
        );

        return view;
    }

}
