package com.theah64.da_vinci.ui.activities.da_vinci;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.theah64.da_vinci.R;
import com.theah64.da_vinci.api.responses.GetShapesResponse;
import com.theah64.da_vinci.ui.activities.base.BaseAppCompatActivity;
import com.theah64.da_vinci.widgets.DaVinciCanvas;

import butterknife.BindView;

public class DaVinciActivity extends BaseAppCompatActivity {

    @BindView(R.id.dvc)
    DaVinciCanvas dvc;

    private static final String KEY_SHAPE = "shape";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_vinci);

        final GetShapesResponse.Shape defaultShape = new GetShapesResponse.Shape("1", "http://theapache64.com:8090/mock_api_data/1538879648096_PmHwp3duAX.png");
        final GetShapesResponse.Shape shape = (GetShapesResponse.Shape) getSerializableExtra(KEY_SHAPE, defaultShape);

    }


    public static void start(Context context, GetShapesResponse.Shape shape) {
        final Intent intent = new Intent(context, DaVinciActivity.class);
        intent.putExtra(KEY_SHAPE, shape);
        context.startActivity(intent);
    }
}
