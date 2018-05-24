package com.esevinale.movieguidetmdb.presentation.view.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.esevinale.movieguidetmdb.R;
import com.esevinale.movieguidetmdb.data.net.ApiConstants;
import com.esevinale.movieguidetmdb.presentation.model.details.CastModel;
import com.esevinale.movieguidetmdb.presentation.view.views.MovieDetailsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CastListAdapter extends RecyclerView.Adapter<CastListAdapter.ViewHolder> {
    private List<CastModel> mCasts = new ArrayList<>();
    private MovieDetailsView mClicableView;
    private Context mContext;

    public CastListAdapter(MovieDetailsView castView) {
        mClicableView = castView;
    }

    @NonNull
    @Override
    public CastListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.model_cast, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.cast = mCasts.get(position);
        holder.name.setText(mCasts.get(position).getName());
        holder.character.setText(mCasts.get(position).getCharacter());

        if (holder.cast.getProfilePath() == null) {
            holder.poster.setImageResource(R.drawable.noimagefound);
        } else {
            Glide
                    .with(mContext)
                    .asBitmap()
                    .apply(getGlideOptions())
                    .load(ApiConstants.POSTER_TMDB_URL + holder.cast.getProfilePath())
                    .into(new BitmapImageViewTarget(holder.poster) {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            super.onResourceReady(resource, transition);
                            Palette.from(resource).generate(palette -> {
                                setBackgroundColor(palette, holder);
                                setTextDecor(palette, holder);
                            });
                        }
                    });
        }
    }

    private RequestOptions getGlideOptions() {
        return new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH);
    }

    private void setBackgroundColor(Palette palette, ViewHolder holder) {
        if (palette == null) return;
        holder.background.setBackgroundColor(palette.getDarkVibrantColor(mContext
                .getResources().getColor(R.color.colorOpacity)));
    }

    private void setTextDecor(Palette palette, ViewHolder holder) {
        holder.character.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-LightItalic.ttf"));
        holder.name.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSans-LightItalic.ttf"));
        if (palette == null) return;
        Palette.Swatch swatch = palette.getDarkVibrantSwatch();
        if (swatch != null) {
            holder.name.setTextColor(swatch.getTitleTextColor());
            holder.character.setTextColor(swatch.getTitleTextColor());
        }
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    public void setCast(List<CastModel> castItemList) {
        mCasts.addAll(castItemList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.cast_background)
        LinearLayout background;
        @BindView(R.id.cast_image)
        ImageView poster;
        @BindView(R.id.cast_name)
        TextView name;
        @BindView(R.id.cast_character)
        TextView character;

        CastModel cast;

        ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            CastListAdapter.this.mClicableView.onCastClicked(cast, view);
        }
    }
}
