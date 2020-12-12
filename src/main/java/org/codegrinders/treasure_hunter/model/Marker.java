package org.codegrinders.treasure_hunter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Marker")
public class Marker {

    @Id
    private String id;
    private double latitude;
    private double longitude;
    private String markerName;
    private String puzzleId;

    @PersistenceConstructor
    public Marker(String id, double latitude, double longitude, String markerName, String puzzleId) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.markerName = markerName;
        this.puzzleId = puzzleId;
    }

    public Marker(double latitude, double longitude, String markerName, String puzzleId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.markerName = markerName;
        this.puzzleId = puzzleId;
    }

    public Marker(){}

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMarkerName() {
        return markerName;
    }

    public void setMarkerName(String markerName) {
        this.markerName = markerName;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "id='" + id + '\'' +
                ", latitude='" + latitude +
                ", longitude='" + longitude +
                ", markerName=" + markerName + '\'' +
                ", puzzleId=" + puzzleId + '\'' +
                '}';
    }
}
