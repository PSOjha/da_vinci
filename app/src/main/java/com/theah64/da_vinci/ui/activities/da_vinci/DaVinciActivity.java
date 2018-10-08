package com.theah64.da_vinci.ui.activities.da_vinci;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

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
import com.theah64.da_vinci.widgets.DaVinciLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import yuku.ambilwarna.AmbilWarnaDialog;

public class DaVinciActivity extends BaseProgressManActivity implements RequestListener<Bitmap>, ActionsAdapter.Callback {

    private static final String TAG = DaVinciActivity.class.getSimpleName();

    @BindView(R.id.dvl)
    DaVinciLayout dvl;

    @BindView(R.id.rvActions)
    RecyclerView rvActions;

    @BindView(R.id.sbRotate)
    SeekBar sbRotate;

    @BindView(R.id.llRotateControl)
    LinearLayout llRotateControl;

    private static final String KEY_SHAPE = "shape";
    private ActionsAdapter actionsAdapter;
    private List<Action> actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_da_vinci);

        final GetShapesResponse.Shape defaultShape = new GetShapesResponse.Shape("1", "http://theapache64.com:8090/mock_api_data/1538879652615_zjodI3eZ3u.png");
        final GetShapesResponse.Shape defaultShape2 = new GetShapesResponse.Shape("1", "http://theapache64.com:8090/mock_api_data/1538879648096_PmHwp3duAX.png");

        final GetShapesResponse.Shape shape = (GetShapesResponse.Shape) getSerializableExtra(KEY_SHAPE, defaultShape);

        // Building shape list
        final List<GetShapesResponse.Shape> shapes = new ArrayList<>();
        shapes.add(defaultShape);
        shapes.add(defaultShape2);


        showLoading("Loading shape...");

        for (GetShapesResponse.Shape s : shapes) {
            Glide.with(this)
                    .asBitmap()
                    .load(s.getImageUrl())
                    .addListener(this)
                    .submit(300, 300);
        }

        // Building actions
        this.actions = new ArrayList<>();
        actions.add(new Action("{ion-ios-loop}", R.string.Rotate));
        actions.add(new Action("{ion-ios-color-filter-outline}", R.string.Color));

        actions.add(new Action("{ion-ios-loop}", R.string.Rotate));
        actions.add(new Action("{ion-ios-color-filter-outline}", R.string.Color));


        actions.add(new Action("{ion-ios-loop}", R.string.Rotate));
        actions.add(new Action("{ion-ios-color-filter-outline}", R.string.Color));

        this.actionsAdapter = new ActionsAdapter(this, actions, this);
        rvActions.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvActions.setAdapter(actionsAdapter);

        // Seek bar setup
        sbRotate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                // TODO: Rotate selected ImageView here
                dvl.setActiveShapeRotation(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.miSave:
                saveImage(false);
                break;

            case R.id.miPreview:
                saveImage(true);
                break;
        }
        return true;
    }

    private void saveImage(boolean preview) {

        dvl.setDrawingCacheEnabled(true);
        dvl.buildDrawingCache();
        Bitmap bmp = dvl.getDrawingCache();
        bmp = getclip(bmp);

        String filename = System.currentTimeMillis() + "_sample.png";
        File sd = Environment.getExternalStorageDirectory();
        File dest = new File(sd, filename);

        try {
            FileOutputStream out = new FileOutputStream(dest);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

            if (preview) {
                final Intent i = new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.fromFile(dest), "image/png");
                startActivity(Intent.createChooser(i, "Choose"));
            }

            Toast.makeText(this, "File saved to " + dest.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            dvl.setDrawingCacheEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private static Bitmap getclip(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
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
        dvl.addBitmap(resource);
        return true;
    }


    @Override
    public void onActionPressed(int position) {

        actionsAdapter.setActiveActionPosition(position);
        actionsAdapter.notifyDataSetChanged();

        final Action selectedAction = actions.get(position);

        switch (selectedAction.getTitle()) {

            // Rotation
            case R.string.Rotate:
                llRotateControl.setVisibility(View.VISIBLE);
                return;

            // Color
            case R.string.Color:
                llRotateControl.setVisibility(View.GONE);

                new AmbilWarnaDialog(this, Color.RED, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {

                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        dvl.setActiveShapeColor(color);
                    }
                }).show();
        }

    }
}
