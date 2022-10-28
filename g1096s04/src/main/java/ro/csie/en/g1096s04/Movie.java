package ro.csie.en.g1096s04;

import java.util.Date;

public class Movie {

    private String title;
    private Genre genre;
    private Date release;
    private Integer duration;
    private Boolean recommended;

    public Movie() {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Movie(String title, Genre genre, Date release, Integer duration) {
        this.title = title;
        this.genre = genre;
        this.release = release;
        this.duration = duration;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", release=" + release +
                ", duration=" + duration +
                ", recommended=" + recommended +
                '}';
    }
}
