package com.masbon.worldcupeditor.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.models.Sticker;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StickersAdapter extends RecyclerView.Adapter<StickersAdapter.ViewHolder> {
    private Context mContext;

    private List<Sticker> mStickersList;

    private OnStickerClickListener mOnStickerClickListener;

    public interface OnStickerClickListener {
        void onClick(Sticker sticker);
    }

    public void setOnStickerClickListener(OnStickerClickListener onStickerClickListener) {
        mOnStickerClickListener = onStickerClickListener;
    }

    public StickersAdapter(List<Sticker> stickers) {
        mStickersList = stickers;
    }

    @Override
    public StickersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sticker, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StickersAdapter.ViewHolder holder, int position) {
        final Sticker sticker = mStickersList.get(position);

        Picasso.with(mContext)
                .load(sticker.getImage())
                .fit()
                .centerCrop()
                .noPlaceholder()
                .into(holder.sticker);

        holder.sticker.setOnClickListener(view -> mOnStickerClickListener.onClick(sticker));
    }

    @Override
    public int getItemCount() {
        return mStickersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_sticker)
        ImageView sticker;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}