package com.example.apiconnection;

public class id {

    public String id;
    public String chartRating;

    public id(com.example.apiconnection.id responseItem) {
    }

    public String getChartRating() {
        return chartRating;
    }

    public id(String idd, String chartRating) {
        String[] parts = idd.split("/");
        String titleId = parts[2];
        this.id = titleId;
        this.chartRating = chartRating;
    }

    public String getId() {
        return id;
    }

}
