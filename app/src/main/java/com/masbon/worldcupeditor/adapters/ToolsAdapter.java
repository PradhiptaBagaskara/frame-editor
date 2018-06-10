package com.masbon.worldcupeditor.adapters;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.masbon.worldcupeditor.R;
import com.masbon.worldcupeditor.models.Tool;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.ViewHolder> {
    private Context mContext;

    private List<Tool> mToolsList;

    private OnToolsClickListener mOnToolsClickListener;

    public interface OnToolsClickListener {
        void onClick(Tool tool);
    }

    public void setOnToolsClickListener(OnToolsClickListener onToolsClickListener) {
        mOnToolsClickListener = onToolsClickListener;
    }

    public ToolsAdapter(List<Tool> tools) {
        mToolsList = tools;
    }

    @Override
    public ToolsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(R.layout.item_tool, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToolsAdapter.ViewHolder holder, int position) {
        final Tool tool = mToolsList.get(position);

        holder.button.setText(mContext.getResources().getString(tool.getTitle()));

        holder.button.setCompoundDrawablesWithIntrinsicBounds(null,
                ResourcesCompat.getDrawable(mContext.getResources(), tool.getIcon(), null),
                null, null);

        holder.button.setOnClickListener(view -> mOnToolsClickListener.onClick(tool));
    }
    
    @Override
    public int getItemCount() {
        return mToolsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.button_tool)
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}