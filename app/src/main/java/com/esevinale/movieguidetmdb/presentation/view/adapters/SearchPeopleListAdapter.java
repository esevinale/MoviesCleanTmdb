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
import com.esevinale.movieguidetmdb.presentation.model.PeopleModel;
import com.esevinale.movieguidetmdb.presentation.view.views.ClickableSearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchPeopleListAdapter extends RecyclerView.Adapter<SearchPeopleListAdapter.ViewHolder> {
    private List<PeopleModel> peopleModelList = new ArrayList<>();
    private ClickableSearchView mClicableView;
    private Context mContext;

    public SearchPeopleListAdapter(ClickableSearchView searchPeopleView) {
        mClicableView = searchPeopleView;
    }

    @NonNull
    @Override
    public SearchPeopleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.model_search, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(holder);
        holder.peopleModel = peopleModelList.get(position);
        holder.name.setText(peopleModelList.get(position).getName());

        if (holder.peopleModel.getProfilePath() == null) {
            holder.poster.setImageResource(R.drawable.noimagefound);
        } else {
            Glide
                    .with(mContext)
                    .asBitmap()
                    .apply(getGlideOptions())
                    .load(ApiConstants.POSTER_TMDB_URL + holder.peopleModel.getProfilePath())
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
        return peopleModelList.size();
    }

    public PeopleModel getItem(int position) {
        return peopleModelList.get(position);
    }

    public void addPeople(List<PeopleModel> tvShowModelList) {
        peopleModelList.addAll(tvShowModelList);
        notifyDataSetChanged();
    }

    public void setPeople(List<PeopleModel> tvShowModelList) {
        clearList();
        addPeople(tvShowModelList);
    }

    private void clearList() {
        peopleModelList.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.search_image)
        ImageView poster;
        @BindView(R.id.title_background)
        View titleBackground;
        @BindView(R.id.search_name)
        TextView name;

        PeopleModel peopleModel;

        ViewHolder(View root) {
            super(root);
            ButterKnife.bind(this, root);
        }

        @Override
        public void onClick(View view) {
            SearchPeopleListAdapter.this.mClicableView.onPeopleClicked(peopleModel, view);
        }
    }
}
