package com.example.xipproject;

public class AssetFb {

    private static String TAG = Asset.class.getSimpleName();

    private String assetName;
    private String assetId;
    private boolean status;

    public AssetFb() {
    }

    public AssetFb(String assetName , String assetId , boolean status) {
        this.assetName = assetName;
        this.assetId = assetId;
        this.status = status;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
