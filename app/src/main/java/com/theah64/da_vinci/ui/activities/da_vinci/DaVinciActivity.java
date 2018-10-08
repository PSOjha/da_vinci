package com.theah64.da_vinci.ui.activities.da_vinci;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.theah64.da_vinci.R;
import com.theah64.da_vinci.api.responses.GetShapesResponse;
import com.theah64.da_vinci.pojos.Action;
import com.theah64.da_vinci.ui.activities.base.BaseProgressManActivity;
import com.theah64.da_vinci.ui.adapters.ActionsAdapter;
import com.theah64.da_vinci.widgets.DaVinciCanvas;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DaVinciActivity extends BaseProgressManActivity implements RequestListener<Bitmap> {

    @BindView(R.id.dvc)
    DaVinciCanvas dvc;

    @BindView(R.id.rvActions)
    RecyclerView rvActions;

    private static final String KEY_SHAPE = "shape";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_vinci);

        final GetShapesResponse.Shape defaultShape = new GetShapesResponse.Shape("1", "http://theapache64.com:8090/mock_api_data/1538879648096_PmHwp3duAX.png");
        final GetShapesResponse.Shape shape = (GetShapesResponse.Shape) getSerializableExtra(KEY_SHAPE, defaultShape);

        showLoading("Loading shape...");

        Glide.with(this)
                .asBitmap()
                .load(shape.getImageUrl())
                .addListener(this)
                .submit(300, 300);

        // Building actions
        final List<Action> actions = new ArrayList<>();
        actions.add(new Action("{ion-ios-loop}", R.string.Rotate));
        actions.add(new Action("{ion-ios-search}", R.string.Zoom));
        actions.add(new Action("{ion-ios-color-filter-outline}", R.string.Color));

        actions.add(new Action("{ion-ios-loop}", R.string.Rotate));
        actions.add(new Action("{ion-ios-search}", R.string.Zoom));
        actions.add(new Action("{ion-ios-color-filter-outline}", R.string.Color));


        actions.add(new Action("{ion-ios-loop}", R.string.Rotate));
        actions.add(new Action("{ion-ios-search}", R.string.Zoom));
        actions.add(new Action("{ion-ios-color-filter-outline}", R.string.Color));

        final ActionsAdapter actionsAdapter = new ActionsAdapter(this, actions);
        rvActions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvActions.setAdapter(actionsAdapter);
    }


    public static void start(Context context, GetShapesResponse.Shape shape) {
        final Intent intent = new Intent(context, DaVinciActivity.class);
        intent.putExtra(KEY_SHAPE, shape);
        context.startActivity(intent);
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
        hideLoading();
        showToast("Failed");
        finish();
        return true;
    }

    @Override
    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
        hideLoading();
        showToast("Ready!");
        dvc.setBitmap(resource);
        return true;
    }
}
