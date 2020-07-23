package com.madhusmita.final__ois.EntityClasses;

public class SpinnerModel {
    public String getGrv_id() {
        return grv_id;
    }

    private String grv_id;


    public void setGrv_id(String grv_id) {
        this.grv_id = grv_id;
    }

    private String CategoryName;

    public SpinnerModel(String grv_id, String CategoryName) {
        this.CategoryName=CategoryName;
    }


    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
