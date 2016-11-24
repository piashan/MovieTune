package piashsotware.ltd.movietune;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import piashsotware.ltd.movietune.adapter.MovieAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView mMovieRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter mMovieAdapter;
    private List<String> mDataList;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);
        mMovieRecyclerView = (RecyclerView)view.findViewById(R.id.movieRecyclerView);

        mDataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDataList.add("testing"+i);
        }
        mLayoutManager =  new GridLayoutManager(getActivity(), 2);
        mMovieRecyclerView.setLayoutManager(mLayoutManager);
        mMovieAdapter = new MovieAdapter(getActivity(), mDataList);
        mMovieRecyclerView.setAdapter(mMovieAdapter);
        return view;
    }

}
