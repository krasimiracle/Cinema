package android.example.com.cinema.movies;

import android.example.com.cinema.BasePresenter;
import android.example.com.cinema.BaseView;
import android.example.com.cinema.data.Movie;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by B3f0r on 12-Nov-16.
 */

/**
 * Specifies the contract between the view and the presenter
 */

public interface MoviesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMovies(List<Movie> movies);

        void showMovieDetailsUI(String movieID);
    }

    interface Presenter extends BasePresenter {

        @Override
        void start();

        void loadMovies(boolean forceUpdate);

        void openMovieDetails(@NonNull Movie requestedMovie);

        void setFiltering(MoviesFilterType requestType);
    }
}
