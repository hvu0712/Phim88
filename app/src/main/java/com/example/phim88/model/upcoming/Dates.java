package com.example.phim88.model.upcoming;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dates {
    @SerializedName("maximum")
    @Expose
    public String maximum;
    @SerializedName("minimum")
    @Expose
    public String minimum;

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }
}
