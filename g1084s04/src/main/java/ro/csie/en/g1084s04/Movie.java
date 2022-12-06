package ro.csie.en.g1084s04;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "movie")
public class Movie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long movieId;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "genre")
    private Genre genre;

    @ColumnInfo(name = "release")
    @TypeConverters(DateConverter.class)
    private Date release;

    @ColumnInfo(name = "duration")
    private Integer duration;

    @Ignore
    private String poster;

    public Movie() {
    }

    protected Movie(Parcel in) {
        title = in.readString();
        release = new Date(in.readLong());
        genre = Genre.valueOf(in.readString());
        if (in.readByte() == 0) {
            duration = null;
        } else {
            duration = in.readInt();
        }
        poster = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeLong(release.getTime());
        dest.writeString(genre.toString());
        if (duration == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(duration);
        }
        dest.writeString(poster);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Movie(String title, Genre genre, Date release, Integer duration) {
        this.title = title;
        this.genre = genre;
        this.release = release;
        this.duration = duration;
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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", release=" + release +
                ", duration=" + duration +
                ", poster='" + poster + '\'' +
                '}';
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }
}

