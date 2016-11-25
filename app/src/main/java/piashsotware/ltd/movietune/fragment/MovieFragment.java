package piashsotware.ltd.movietune.fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import piashsotware.ltd.movietune.R;
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

    private final String TAG = "MovieFragment";
    private ApiMovieInterface mApiMovieInterface;
    private RecyclerView mMovieRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mMovieAdapter;
    private ProgressBar mProgressBar;
    private int mAdapterPosition;

    public MovieFragment() {
        // Required empty public constructor
    }
    public MovieFragment newInstance(int position) {
        // Required empty public constructor
        MovieFragment fragment = new MovieFragment();
        fragment.mAdapterPosition = position;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);

        Retrofit retrofit = ApiClient.getInstance(getActivity());
        mApiMovieInterface = retrofit.create(ApiMovieInterface.class);
        mMovieRecyclerView = (RecyclerView)view.findViewById(R.id.movieRecyclerView);
        mProgressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        mLayoutManager =  new GridLayoutManager(getActivity(), 2);
        mMovieRecyclerView.setLayoutManager(mLayoutManager);


        if (mAdapterPosition == 0){
            newRelieseMovie();
        }else if (mAdapterPosition == 1){
            topRatedMovie();
        }else if (mAdapterPosition == 2){
            upCommingMovie();
        }

        return view;
    }


    private void newRelieseMovie(){
        mApiMovieInterface.newRelieseMovieNetworkCall().enqueue(
                new Callback<NewReliesePayloadModel>() {
                    @Override
                    public void onResponse(Call<NewReliesePayloadModel> call, Response<NewReliesePayloadModel> response) {
                        if (response.isSuccessful()){
                            mProgressBar.setVisibility(View.GONE);
                            mMovieRecyclerView.setVisibility(View.VISIBLE);
                            mMovieAdapter = new MovieAdapter(getActivity(), response.body().getResults());
                            mMovieRecyclerView.setAdapter(mMovieAdapter);
                            recyclerViewClick();
                        }

                    }

                    @Override
                    public void onFailure(Call<NewReliesePayloadModel> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.toString() );
                    }
                }
        );
    }

    private void topRatedMovie(){
        mApiMovieInterface.topRatedMovieNetworkCall().enqueue(
                new Callback<NewReliesePayloadModel>() {
                    @Override
                    public void onResponse(Call<NewReliesePayloadModel> call, Response<NewReliesePayloadModel> response) {
                        if (response.isSuccessful()){
                            mProgressBar.setVisibility(View.GONE);
                            mMovieRecyclerView.setVisibility(View.VISIBLE);
                            mMovieAdapter = new MovieAdapter(getActivity(), response.body().getResults());
                            mMovieRecyclerView.setAdapter(mMovieAdapter);
                            recyclerViewClick();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewReliesePayloadModel> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.toString() );
                    }
                }
        );
    }

    private void upCommingMovie(){
        mApiMovieInterface.upCommingMovieNetworkCall().enqueue(
                new Callback<NewReliesePayloadModel>() {
                    @Override
                    public void onResponse(Call<NewReliesePayloadModel> call, Response<NewReliesePayloadModel> response) {
                        if (response.isSuccessful()){
                            mProgressBar.setVisibility(View.GONE);
                            mMovieRecyclerView.setVisibility(View.VISIBLE);
                            mMovieAdapter = new MovieAdapter(getActivity(), response.body().getResults());
                            mMovieRecyclerView.setAdapter(mMovieAdapter);
                            recyclerViewClick();
                        }
                    }

                    @Override
                    public void onFailure(Call<NewReliesePayloadModel> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.toString() );
                    }
                }
        );
    }

    private void recyclerViewClick(){
        mMovieAdapter.setOnItemClickListener(
                new MovieAdapter.RVClickListener() {
                    @Override
                    public void onItemClick(int position, View v) {

                        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.activity_main, new MovieDetailFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                }
        );
    }
}
