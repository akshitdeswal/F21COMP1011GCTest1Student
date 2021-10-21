package com.example.f21comp1011gctest1student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetflixShow {

    private String showId, type, title, rating, director, cast;

    public NetflixShow(String showId, String type, String title, String rating, String director, String cast) {
        setShowId(showId);
        setType(type);
        setTitle(title);
        setRating(rating);
        setDirector(director);
        setCast(cast);
    }

    public void setShowId(String showId) {
        if(showId.matches("[s][0-9]*")) {
            this.showId = showId;
        }
        else
            throw new IllegalArgumentException("format for showID is not valid");
    }

    public void setType(String type) {
        if(type.equals("Movie") || type.equals("TV Show")) {
            this.type = type;
        }
        else
            throw new IllegalArgumentException(String.format("%s is not valid type please choose from 'Movie' or 'TV Show'", type));
    }

    public void setTitle(String title) {
        if(title.length() >= 2) {
            this.title = title;
        }
        else
            throw new IllegalArgumentException("title should at least contain 2 characters");
    }
    List<String> validRating = Arrays.asList("PG-13","R","TV-14","TV-G","TV-MA","TV-Y","TV-Y7");

    public void setRating(String rating) {
        if(validRating.contains(rating)) {
            this.rating = rating;
        }
        else
            throw new IllegalArgumentException(rating +" rating is not valid please choose from  "+ validRating);
    }

    public void setDirector(String director) {
        if(title.length() >= 2) {
            this.director = director;
        }
        else
            throw new IllegalArgumentException("Director should at least contain 2 characters");
    }

    public void setCast(String cast) {
        if(cast.length() >= 5) {
            this.cast = cast;
        }
        else
            throw new IllegalArgumentException("Cast should at least contain 5 characters");
    }

    public String getShowId() {
        return showId;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    public String getCast() {
        return cast;
    }


}
