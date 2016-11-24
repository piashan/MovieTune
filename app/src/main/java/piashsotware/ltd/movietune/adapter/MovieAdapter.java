package piashsotware.ltd.movietune.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import piashsotware.ltd.movietune.R;

/**
 * Created by piash on 11/24/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{
    private Context mContex;
    private List<String> mStringList;

    public MovieAdapter(Context mContex, List<String> mStringList) {
        this.mContex = mContex;
        this.mStringList = mStringList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movie_adapter, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        holder.mTextviewName.setText(mStringList.get(position));
    }

    @Override
    public int getItemCount() {
        return mStringList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextviewName;
        public MovieViewHolder(View itemView) {
            super(itemView);
            mTextviewName = (TextView)itemView.findViewById(R.id.textViewName);
        }
    }
}
