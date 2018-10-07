package com.example.theapache64.da_vinci.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.theapache64.da_vinci.R;
import com.example.theapache64.da_vinci.api.responses.GetShapesResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShapesAdapter extends BaseRecyclerViewAdapter<ShapesAdapter.ShapeHolder> {

    private final List<GetShapesResponse.Shape> shapes;
    private final Context context;
    private final Callback callback;

    public ShapesAdapter(Context context, List<GetShapesResponse.Shape> shapes, Callback callback) {
        super(context);
        this.context = context;
        this.shapes = shapes;
        this.callback = callback;
    }

    @NonNull
    @Override
    public ShapeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = getInflater().inflate(R.layout.shape_layout, parent, false);
        return new ShapeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShapeHolder shapeHolder, int position) {
        final GetShapesResponse.Shape shape = shapes.get(position);

        Glide.with(context)
                .load(shape.getImageUrl())
                .into(shapeHolder.ivShape);
    }

    @Override
    public int getItemCount() {
        return shapes.size();
    }

    public class ShapeHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivShape)
        ImageView ivShape;

        ShapeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.ivShape)
        public void onShapeClicked() {
            final int position = getLayoutPosition();
            final GetShapesResponse.Shape shape = shapes.get(position);
            callback.onItemClicked(position, shape);
        }
    }

    public interface Callback {
        void onItemClicked(int position, GetShapesResponse.Shape shape);
    }


}
