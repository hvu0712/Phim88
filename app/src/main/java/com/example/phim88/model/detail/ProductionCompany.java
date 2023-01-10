package com.example.phim88.model.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductionCompany {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("logo_path")
    @Expose
    public Object logoPath;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("origin_country")
    @Expose
    public String originCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(Object logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
}
