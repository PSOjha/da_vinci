package com.theah64.da_vinci.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theah64.da_vinci.R;
import com.theah64.da_vinci.pojos.Action;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionsAdapter extends BaseRecyclerViewAdapter<ActionsAdapter.ActionViewHolder> {

    private final List<Action> actions;
    private final Callback callback;
    private int activeActionPosition = -1;
    private final int accentColor;

    public ActionsAdapter(Context context, List<Action> actions, Callback callback) {
        super(context);
        this.actions = actions;
        this.callback = callback;
        this.accentColor = ContextCompat.getColor(context, R.color.colorAccent);
    }

    public void setActiveActionPosition(int activeActionPosition) {
        this.activeActionPosition = activeActionPosition;
    }

    @NonNull
    @Override
    public ActionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = getInflater().inflate(R.layout.action_layout, parent, false);
        return new ActionViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionViewHolder holder, int position) {
        final Action action = actions.get(position);
        holder.itvActionIcon.setText(action.getIcon());
        holder.tvActionTitle.setText(action.getTitle());

        if (activeActionPosition == position) {
            holder.itvActionIcon.setTextColor(accentColor);
            holder.tvActionTitle.setTextColor(accentColor);
        } else {
            holder.itvActionIcon.setTextColor(Color.GRAY);
            holder.tvActionTitle.setTextColor(Color.GRAY);
        }
    }

    @Override
    public int getItemCount() {
        return actions.size();
    }

    class ActionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itvActionIcon)
        IconTextView itvActionIcon;

        @BindView(R.id.tvActionTitle)
        TextView tvActionTitle;

        ActionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.llActionLayout)
        public void onActionLayoutPressed() {
            callback.onActionPressed(getLayoutPosition());
        }
    }

    public interface Callback {
        void onActionPressed(int position);
    }
}
