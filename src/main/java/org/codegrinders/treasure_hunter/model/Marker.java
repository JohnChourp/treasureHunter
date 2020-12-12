package org.codegrinders.treasure_hunter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Marker")
public class Marker {

    @Id
    private String id;
    private double longitude;
    private double latitude;
    private String markerName;
    private String puzzleId;

    @PersistenceConstructor
    public Marker(String id, double longitude, double latitude, String markerName, String puzzleId) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.markerName = markerName;
        this.puzzleId = puzzleId;
    }

    public Marker(double longitude, double latitude, String markerName, String puzzleId) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.markerName = markerName;
        this.puzzleId = puzzleId;
    }

    public Marker(){}

    public String getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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
                ", longitude='" + longitude +
                ", latitude='" + latitude +
                ", markerName=" + markerName + '\'' +
                ", puzzleId=" + puzzleId + '\'' +
                '}';
    }
}
