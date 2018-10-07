package com.theah64.da_vinci.api.responses;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Generated using MockAPI (https://github.com/theapache64/Mock-API) : Sun Oct 07 02:49:17 UTC 2018
 */
public class GetShapesResponse {


    @SerializedName("categories")
    private final List<Category> categories;


    public GetShapesResponse(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public static class Category {

        @SerializedName("id")
        private final String id;

        @SerializedName("name")
        private final String name;

        @SerializedName("shapes")
        private final ArrayList<Shape> shapes;


        public Category(String id, String name, ArrayList<Shape> shapes) {
            this.id = id;
            this.name = name;
            this.shapes = shapes;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public ArrayList<Shape> getShapes() {
            return shapes;
        }

    }

    public static class Shape implements Serializable {

        @SerializedName("id")
        private final String id;

        @SerializedName("image_url")
        private final String imageUrl;


        public Shape(String id, String imageUrl) {
            this.id = id;
            this.imageUrl = imageUrl;
        }

        public String getId() {
            return id;
        }

        public String getImageUrl() {
            return imageUrl;
        }

    }


}