package android.example.com.cinema.movies;

import android.example.com.cinema.data.Movie;
import android.support.annotation.NonNull;

/**
 * Created by B3f0r on 02-Dec-16.
 */


/**
 * Listens to user actions from the UI ({@link MoviesFragment}), retrieves the data and updates the
 * UI as required.
 */
public class MoviePresenter implements MoviesContract.Presenter {

    public MoviePresenter() {
    }

    @Override
    public void start() {
        loadMovies(false);
    }

    @Override
    public void loadMovies(boolean forceUpdate) {
    }

    @Override
    public void openMovieDetails(@NonNull Movie requestedMovie) {

    }

    @Override
    public void setFiltering(MoviesFilterType requestType) {

    }
}
