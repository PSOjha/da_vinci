package com.example.theapache64.da_vinci.ui.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.theapache64.da_vinci.api.responses.GetShapesResponse;
import com.example.theapache64.da_vinci.ui.fragments.ShapesFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPagerAdapter extends FragmentPagerAdapter {

    private final List<GetShapesResponse.Category> categories;

    public CategoriesPagerAdapter(FragmentManager fm, List<GetShapesResponse.Category> categories) {
        super(fm);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {
        final ArrayList<GetShapesResponse.Shape> shapes = categories.get(position).getShapes();
        return ShapesFragment.newInstance(shapes);
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return categories.get(position).getName();
    }
}
