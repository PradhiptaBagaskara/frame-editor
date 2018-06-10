package com.masbon.worldcupeditor.adapters;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.graphics.ColorCircleDrawable;
import com.masbon.worldcupeditor.models.EditorColor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ViewHolder> {
    private int mSelectedColorPosition = 0;

    private Context mContext;

    private List<EditorColor> mEditorColorsList;

    private OnColorClickListener mOnColorClickListener;

    public interface OnColorClickListener {
        void onClick(EditorColor editorColor);
    }

    public void setOnColorClickListener(OnColorClickListener onColorClickListener) {
        mOnColorClickListener = onColorClickListener;
    }

    public ColorAdapter(List<EditorColor> editorColors) {
        mEditorColorsList = editorColors;
    }

    @Override
    public ColorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_color, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ColorAdapter.ViewHolder holder, int position) {
        final EditorColor color = mEditorColorsList.get(position);

        holder.imageView.setImageDrawable(new ColorCircleDrawable(
                ResourcesCompat.getColor(mContext.getResources(), color.getColor(), null))
        );

        if (mSelectedColorPosition == holder.getAdapterPosition()) {
            mOnColorClickListener.onClick(color);
            ((ColorCircleDrawable) holder.imageView.getDrawable()).setSelected(true);
        } else {
            ((ColorCircleDrawable) holder.imageView.getDrawable()).setSelected(false);
        }

        holder.imageView.setOnClickListener(view -> {
            notifyItemChanged(mSelectedColorPosition);
            mSelectedColorPosition = holder.getAdapterPosition();
            notifyItemChanged(holder.getAdapterPosition());

        });
    }

    @Override
    public int getItemCount() {
        return mEditorColorsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_color)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}