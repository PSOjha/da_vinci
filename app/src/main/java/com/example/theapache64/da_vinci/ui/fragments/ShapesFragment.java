package com.example.theapache64.da_vinci.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.theapache64.da_vinci.R;
import com.example.theapache64.da_vinci.api.responses.GetShapesResponse;
import com.example.theapache64.da_vinci.ui.adapters.ShapesAdapter;
import com.example.theapache64.da_vinci.ui.fragments.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShapesFragment extends BaseFragment {


    private static final String KEY_SHAPES = "shapes";

    @BindView(R.id.rvShapes)
    RecyclerView rvShapes;

    public ShapesFragment() {
        // Required empty public constructor
    }

    public static ShapesFragment newInstance(final ArrayList<GetShapesResponse.Shape> shapes) {
        final ShapesFragment shapesFragment = new ShapesFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_SHAPES, shapes);
        shapesFragment.setArguments(args);
        return shapesFragment;
    }


    @Override
    protected void onLayout(View layout) {
        assert getArguments() != null;
        @SuppressWarnings("unchecked") final ArrayList<GetShapesResponse.Shape> shapes = (ArrayList<GetShapesResponse.Shape>) getArguments().getSerializable(KEY_SHAPES);
        final ShapesAdapter shapeAdapter = new ShapesAdapter(getActivity(), shapes, new ShapesAdapter.Callback() {
            @Override
            public void onItemClicked(int position, GetShapesResponse.Shape shape) {
                Toast.makeText(getActivity(), shape.getImageUrl(), Toast.LENGTH_SHORT).show();
            }
        });
        rvShapes.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvShapes.setAdapter(shapeAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shapes;
    }
}
