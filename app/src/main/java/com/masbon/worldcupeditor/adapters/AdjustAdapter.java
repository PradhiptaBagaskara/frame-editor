package com.masbon.worldcupeditor.adapters;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.models.Adjust;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdjustAdapter extends RecyclerView.Adapter<AdjustAdapter.ViewHolder> {
    private Context mContext;
    
    private List<Adjust> mAdjustList;

    private OnAdjustClickListener mOnAdjustClickListener;

    public interface OnAdjustClickListener {
        void onAdjustClick(Adjust adjust);
    }

    public void setOnAdjustClickListener(OnAdjustClickListener onAdjustClickListener) {
        mOnAdjustClickListener = onAdjustClickListener;
    }

    public AdjustAdapter(List<Adjust> adjustList) {
        mAdjustList = adjustList;
    }

    @Override
    public AdjustAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_adjust, parent, false);

        return new AdjustAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdjustAdapter.ViewHolder holder, int position) {
        final Adjust adjust = mAdjustList.get(position);

        holder.button.setText(mContext.getText(adjust.getTitle()));
        holder.button.setCompoundDrawablesWithIntrinsicBounds(null,
                ResourcesCompat.getDrawable(mContext.getResources(), adjust.getIcon(), null),
                null, null);

        holder.button.setOnClickListener(view -> mOnAdjustClickListener.onAdjustClick(adjust));
    }

    @Override
    public int getItemCount() {
        return mAdjustList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button_adjust)
        Button button;

        ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}