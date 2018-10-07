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

public class ShapesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GetShapesResponse.Shape> shapes;
    private final LayoutInflater inflater;
    private final Context context;

    public ShapesAdapter(Context context, List<GetShapesResponse.Shape> shapes) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.shapes = shapes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.shapes_layout, parent, false);
        return new ShapeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ShapeHolder shapeHolder = (ShapeHolder) holder;
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
    }


}
