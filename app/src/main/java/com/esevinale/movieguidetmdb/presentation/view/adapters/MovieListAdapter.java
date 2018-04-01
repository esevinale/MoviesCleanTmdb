package com.esevinale.movieguidetmdb.presentation.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.presentation.model.MovieModel;
import com.esevinale.movieguidetmdb.presentation.view.MovieListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<MovieModel> movies = new ArrayList<>();
    private MovieListView view;
    private Context context;

    public MovieListAdapter(MovieListView moviesView) {
        view = moviesView;
    }

    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.movie = movies.get(position);
        holder.name.setText(movies.get(position).getTitle());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);

        if (holder.movie.getPosterPath() == null) {
            holder.poster.setImageResource(R.drawable.noimagefound);
        } else {
            Glide
                    .with(context)
                    .asBitmap()
                    .apply(options)
                    .load(ApiConstants.POSTER_TMDB_URL + holder.movie.getPosterPath())
                    .into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public MovieModel getItem(int position) {
        return movies.get(position);
    }

    public void addMovies(List<MovieModel> movieItemList) {
        movies.addAll(movieItemList);
        notifyDataSetChanged();
    }

    public void setMovies(List<MovieModel> movieItemList) {
        clearList();
        addMovies(movieItemList);
    }

    private void clearList() {
        movies.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_poster)
        ImageView poster;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.movie_name)
        TextView name;

        MovieModel movie;

        ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            MovieListAdapter.this.view.viewMovie(movie);
        }
    }
}