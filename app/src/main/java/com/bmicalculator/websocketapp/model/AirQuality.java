package com.bmicalculator.websocketapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AirQuality implements Serializable {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("aqi")
    @Expose
    private Float aqi;
    private final static long serialVersionUID = -8739245873451213663L;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getAqi() {
        return aqi;
    }

    public void setAqi(Float aqi) {
        this.aqi = aqi;
    }
}
