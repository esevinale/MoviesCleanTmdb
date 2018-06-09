package com.esevinale.movieguidetmdb.presentation.view.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.presentation.model.TvShowModel;
import com.esevinale.movieguidetmdb.presentation.view.views.ClickableSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchTvShowListAdapter extends RecyclerView.Adapter<SearchTvShowListAdapter.ViewHolder> {
    private List<TvShowModel> mTvShows = new ArrayList<>();
    private ClickableSearchView mClicableView;
    private Context mContext;

    public SearchTvShowListAdapter(ClickableSearchView searchTvView) {
        mClicableView = searchTvView;
    }

    @NonNull
    @Override
    public SearchTvShowListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.model_search, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.tvShowModel = mTvShows.get(position);
        holder.name.setText(mTvShows.get(position).getName());

        if (holder.tvShowModel.getPosterPath() == null) {
            holder.poster.setImageResource(R.drawable.noimagefound);
        } else {
            Glide
                    .with(mContext)
                    .asBitmap()
                    .apply(getGlideOptions())
                    .load(ApiConstants.POSTER_TMDB_URL + holder.tvShowModel.getPosterPath())
                    .into(new BitmapImageViewTarget(holder.poster) {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            super.onResourceReady(resource, transition);
                            Palette.from(resource).generate(palette -> setTitleColor(palette, holder));
                        }
                    });
        }
    }

    private RequestOptions getGlideOptions() {
        return new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.img_loading)
                .error(R.drawable.noimagefound)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);
    }

    private void setTitleColor(Palette palette, ViewHolder holder) {
        holder.titleBackground.setBackgroundColor(palette.getVibrantColor(mContext
                .getResources().getColor(R.color.colorOpacity)));
    }

    @Override
    public int getItemCount() {
        return mTvShows.size();
    }

    public TvShowModel getItem(int position) {
        return mTvShows.get(position);
    }

    public void addTvShows(List<TvShowModel> tvShowModelList) {
        mTvShows.addAll(tvShowModelList);
        notifyDataSetChanged();
    }

    public void setTvShows(List<TvShowModel> tvShowModelList) {
        clearList();
        addTvShows(tvShowModelList);
    }

    private void clearList() {
        mTvShows.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.search_image)
        ImageView poster;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.search_name)
        TextView name;

        TvShowModel tvShowModel;

        ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            SearchTvShowListAdapter.this.mClicableView.onTvClicked(tvShowModel, view);
        }
    }
}
