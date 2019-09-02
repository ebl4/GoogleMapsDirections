package com.example.trackingtransport.Model;

public class Route {
    private String startName = "", endName = "", overviewPolyline = "";
    private double startLat;
    private double startLng;
    private double endLat;
    private double endLng;

    public Route(String startName, String endName, double startLat, double startLng,
                 double endLat, double endLng, String overviewPolyline){
        this.startName = startName;
        this.endName = endName;
        this.overviewPolyline = overviewPolyline;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;

    }

    public String getStartName() {
        return startName;
    }

    public String getEndName() {
        return endName;
    }

    public String getOverviewPolyline() {
        return overviewPolyline;
    }

    public double getStartLat() {
        return startLat;
    }

    public double getStartLng() {
        return startLng;
    }

    public double getEndLat() {
        return endLat;
    }

    public double getEndLng() {
        return endLng;
    }
}
