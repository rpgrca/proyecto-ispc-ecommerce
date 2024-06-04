package com.ar.enbaldeapp.models;

import com.google.gson.annotations.SerializedName;

public class Configuration {
    @SerializedName("nombre")
    private final String name;
    @SerializedName("valor")
    private final String value;

    public Configuration(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
