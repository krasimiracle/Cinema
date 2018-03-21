package android.example.com.cinema.data;

/**
 * Created by B3f0r on 12-Nov-16.
 */


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Immutable model class for a Movie.
 */
@Entity(tableName = "movies")
public final class Movie {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryId")
    private final String id;

    @NonNull
    @ColumnInfo(name = "title")
    private final String title;

    @NonNull
    @ColumnInfo(name = "description")
    private final String description;

    @NonNull
    @ColumnInfo(name = "posterPath")
    private final String posterPath;

    @Nullable
    @ColumnInfo(name = "videoUrl")
    private final String videoUrl;

    public Movie(@NonNull String id, @NonNull String title, @NonNull String description, @NonNull String posterPath, @Nullable String videoUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterPath = posterPath;
        this.videoUrl = videoUrl;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getPosterPath() {
        return posterPath;
    }

    @Nullable
    public String getVideoUrl() {
        return videoUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!getId().equals(movie.getId())) return false;
        if (!getTitle().equals(movie.getTitle())) return false;
        if (!getDescription().equals(movie.getDescription())) return false;
        if (!getPosterPath().equals(movie.getPosterPath())) return false;
        return getVideoUrl() != null ? getVideoUrl().equals(movie.getVideoUrl()) : movie.getVideoUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getTitle().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPosterPath().hashCode();
        result = 31 * result + (getVideoUrl() != null ? getVideoUrl().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie with title{" +
                "title='" + title + '\'' +
                '}';
    }
}
