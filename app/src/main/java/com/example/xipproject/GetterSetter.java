package com.example.xipproject;

public class GetterSetter  {

   String id;
    String name;
    String assetName,date,startTime,endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public GetterSetter(String id , String name , String assetName , String date , String startTime , String endTime) {
        this.id = id;
        this.name = name;
        this.assetName = assetName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
