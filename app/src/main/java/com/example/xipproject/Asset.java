package com.example.xipproject;

public class Asset {

    private int asset_image_id;
    private String asset_name;

    public Asset(int asset_image_id, String asset_name)
    {
        this.asset_image_id = asset_image_id;
        this.asset_name = asset_name;
    }



    public int getAsset_image_id() {
        return asset_image_id;
    }

    public String getAsset_name() {
        return asset_name;
    }
}
