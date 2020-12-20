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
    private String markerTile;
    private String snippet;
    private String puzzleId;
    private boolean isVisible;

    @PersistenceConstructor
    public Marker(String id, double latitude, double longitude, String markerTile, String snippet, String puzzleId, boolean isVisible) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.markerTile = markerTile;
        this.snippet = snippet;
        this.puzzleId = puzzleId;
        this.isVisible = isVisible;
    }

    public Marker(double latitude, double longitude, String markerTile, String snippet, String puzzleId, boolean isVisible) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.markerTile = markerTile;
        this.snippet = snippet;
        this.puzzleId = puzzleId;
        this.isVisible = isVisible;
    }

    public Marker() {
    }

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

    public String getMarkerTile() {
        return markerTile;
    }

    public void setMarkerTile(String markerTile) {
        this.markerTile = markerTile;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", markerTile=" + markerTile +
                ", snippet=" + snippet +
                ", puzzleId=" + puzzleId +
                ", isVisible=" + isVisible +
                '}';
    }
}
