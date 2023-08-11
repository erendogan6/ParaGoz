package com.example.paragoz.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class liste {
    @SerializedName("result")
    private List<doviz> result;
    public List<doviz> getDataList() {
        return result;
    }
}
