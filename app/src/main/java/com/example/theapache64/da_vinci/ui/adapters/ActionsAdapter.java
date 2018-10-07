package com.example.theapache64.da_vinci.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theapache64.da_vinci.R;
import com.example.theapache64.da_vinci.pojos.Action;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActionsAdapter extends BaseRecyclerViewAdapter<ActionsAdapter.ActionViewHolder> {

    private final List<Action> actions;

    protected ActionsAdapter(Context context, List<Action> actions) {
        super(context);
        this.actions = actions;
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
    }
}
