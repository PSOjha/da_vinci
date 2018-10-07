package com.example.theapache64.da_vinci.ui.activities.categories;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.theapache64.da_vinci.R;
import com.example.theapache64.da_vinci.api.APIInterface;
import com.example.theapache64.da_vinci.api.responses.BaseAPIResponse;
import com.example.theapache64.da_vinci.api.responses.GetShapesResponse;
import com.example.theapache64.da_vinci.ui.activities.base.BaseNetworkActivity;
import com.example.theapache64.da_vinci.ui.adapters.CategoriesPagerAdapter;

import java.util.List;

import butterknife.BindView;
import retrofit2.Call;

public class CategoriesActivity extends BaseNetworkActivity<GetShapesResponse> implements CategoriesView {


    @BindView(R.id.tlCategories)
    TabLayout tlCategories;

    @BindView(R.id.vpCategories)
    ViewPager vpShapes;

    private CategoriesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = new CategoriesPresenterImpl(this);

        load();
    }

    @Override
    protected Call<BaseAPIResponse<GetShapesResponse>> getAPICall(APIInterface apiInterface) {
        return apiInterface.getShapes();
    }

    @Override
    protected void onResponseLoaded(GetShapesResponse response) {
        final List<GetShapesResponse.Category> categories = response.getCategories();
        final CategoriesPagerAdapter adapter = new CategoriesPagerAdapter(getSupportFragmentManager(), categories);
        vpShapes.setAdapter(adapter);
        tlCategories.setupWithViewPager(vpShapes);
    }
}
