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
    private String title;
    private String snippet;
    private String puzzleId;
    private boolean visibility;

    @PersistenceConstructor
    public Marker(String id, double latitude, double longitude, String title, String snippet, String puzzleId, boolean visibility) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.snippet = snippet;
        this.puzzleId = puzzleId;
        this.visibility = visibility;
    }

    public Marker(double latitude, double longitude, String title, String snippet, String puzzleId, boolean visibility) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.snippet = snippet;
        this.puzzleId = puzzleId;
        this.visibility = visibility;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", title=" + title +
                ", snippet=" + snippet +
                ", puzzleId=" + puzzleId +
                ", visibility=" + visibility +
                '}';
    }
}
