package piashsotware.ltd.movietune.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import piashsotware.ltd.movietune.MainActivity;
import piashsotware.ltd.movietune.R;
import piashsotware.ltd.movietune.adapter.SimilarMovieAdapter;
import piashsotware.ltd.movietune.api.ApiClient;
import piashsotware.ltd.movietune.api.ApiMovieInterface;
import piashsotware.ltd.movietune.datamodel.MovieDetailPayloadModel;
import piashsotware.ltd.movietune.datamodel.NewReliesePayloadModel;
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
    private RecyclerView mSimilarMovieRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SimilarMovieAdapter mSimilarMovieAdapter;
    private TextView mTextViewOverView;
    private TextView mTextViewMovieTitle;
    private TextView mTextViewMoviName;
    private TextView mTextViewVoteAvg;
    private TextView mTextViewPopularity;
    private ImageView mImageViewDetailPoster;
    private TextView mTextViewReleaseYear;
    private TextView mTextViewMovieCategory;
    private TextView mTextViewPcompany;
    private TextView mtextViewPCountry;
    private TextView mTextViewBudget;
    private TextView mTextViewLanguage;
    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        mSimilarMovieRecyclerView = (RecyclerView)view.findViewById(R.id.similarMovieRecyclerView);
        mTextViewOverView = (TextView)view.findViewById(R.id.textViewOverView);
        mTextViewMovieTitle = (TextView)view.findViewById(R.id.textViewMovieTitle);
        mTextViewMoviName = (TextView)view.findViewById(R.id.textViewMovieName);
        mImageViewDetailPoster = (ImageView)view.findViewById(R.id.detailImage);
        mTextViewVoteAvg = (TextView)view.findViewById(R.id.textViewVoteAvg);
        mTextViewPopularity = (TextView)view.findViewById(R.id.textViewPopularity);
        mTextViewReleaseYear = (TextView)view.findViewById(R.id.textViewReleaseYear);
        mTextViewMovieCategory = (TextView)view.findViewById(R.id.textViewMoiveCategory);
        mTextViewPcompany = (TextView)view.findViewById(R.id.textViewPCompanyName);
        mtextViewPCountry = (TextView)view.findViewById(R.id.textViewPCountry);
        mTextViewBudget = (TextView)view.findViewById(R.id.textViewBudget);
        mTextViewLanguage = (TextView)view.findViewById(R.id.textViewLanguage);

        Toolbar toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        Retrofit retrofit = ApiClient.getInstance(getActivity());
        mApiMovieInterface = retrofit.create(ApiMovieInterface.class);
        detailInformation();
        similarMovie();

        return view;
    }

    private void detailInformation(){
        mApiMovieInterface.movieDetailNetworkCall().enqueue(
                new Callback<MovieDetailPayloadModel>() {
                    @Override
                    public void onResponse(Call<MovieDetailPayloadModel> call, Response<MovieDetailPayloadModel> response) {
                        if (response.isSuccessful()){
                            mTextViewOverView.setText(response.body().getOverview());
                            mTextViewMovieTitle.setText(response.body().getOriginal_title());
                            mTextViewMoviName.setText(response.body().getTitle());
                            mTextViewVoteAvg.setText(String.format("%.1f", response.body().getVote_average()));
                            mTextViewPopularity.setText(String.format("%.0f", response.body().getPopularity())+"%");
                            mTextViewPcompany.setText(response.body().getProduction_companies().get(0).getName());
                            mtextViewPCountry.setText(response.body().getProduction_countries().get(0).getName());
                            mTextViewBudget.setText("$ "+String.valueOf(response.body().getBudget()));
                            mTextViewLanguage.setText(response.body().getOriginal_language());
                            String year[] = response.body().getRelease_date().split("-");
                            mTextViewReleaseYear.setText("("+year[0]+")");

                            for (int i = 0; i < response.body().getGenres().size(); i++) {

                                if (i <response.body().getGenres().size() -1 ){
                                    mTextViewMovieCategory.append(response.body().getGenres().get(i).getName()+", ");
                                }else {
                                    mTextViewMovieCategory.append(response.body().getGenres().get(i).getName());

                                }
                            }
                            Glide.with(getActivity())
                                    .load("http://image.tmdb.org/t/p/w500"+ response.body().getPoster_path())
                                    .thumbnail(0.1f)
                                    .into(mImageViewDetailPoster);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDetailPayloadModel> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.toString() );
                    }
                }
        );
    }

    private void similarMovie(){

        mSimilarMovieRecyclerView.setHasFixedSize(true);
        mLayoutManager =  new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mSimilarMovieRecyclerView.setLayoutManager(mLayoutManager);
        mApiMovieInterface.similarMovieNetworkCall().enqueue(
                new Callback<NewReliesePayloadModel>() {
                    @Override
                    public void onResponse(Call<NewReliesePayloadModel> call, Response<NewReliesePayloadModel> response) {

                        if (response.isSuccessful()){

                            mSimilarMovieAdapter = new SimilarMovieAdapter(getActivity(), response.body().getResults());
                            mSimilarMovieRecyclerView.setAdapter(mSimilarMovieAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<NewReliesePayloadModel> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.toString() );
                    }
                }
        );

    }

}
