package ro.csie.en.g1084s04;

import java.util.Date;

public class Movie {

    private String title;
    private Genre genre;
    private Date release;
    private float budget;

    public Movie() {
    }

    public Movie(String title, Genre genre, Date release, float budget) {
        this.title = title;
        this.genre = genre;
        this.release = release;
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getRelease() {
        return release;
    }

    public void setRelease(Date release) {
        this.release = release;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", release=" + release +
                ", budget=" + budget +
                '}';
    }
}
