package com.masbon.worldcupeditor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.models.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {
    private Context mContext;

    private List<Image> mImageList;

    private OnImageClickListener mListener;

    public void setOnImageClickListener(OnImageClickListener listener) {
        mListener = listener;
    }

    public interface OnImageClickListener {
        void onClick(Image image);
    }

    public ImagesAdapter(List<Image> imageList) {
        mImageList = imageList;
    }

    @Override
    public ImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.item_image, parent, false);

        return new ImagesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesAdapter.ViewHolder holder, int position) {
        Image image = mImageList.get(position);

        Picasso.with(mContext)
                .load("file://" + image.getPath())
                .resize(250, 250)
                .centerCrop()
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });

        holder.image.setOnClickListener(v -> mListener.onClick(image));
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_gallery_image)
        ImageView image;

        @BindView(R.id.progress_bar_gallery_image)
        ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}