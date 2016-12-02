package android.example.com.cinema.movies;

import android.content.Intent;
import android.example.com.cinema.BuildConfig;
import android.example.com.cinema.R;
import android.example.com.cinema.moviesdetail.DetailActivity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesFragment extends Fragment {

    private MoviesAdapter moviesAdapter;
    public ArrayList<String> posterImages;
    private String[] resultStr = new String[20];

    public MoviesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.moviesfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            FetchMoviesData moviesData = new FetchMoviesData();
            moviesData.execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        moviesAdapter = new MoviesAdapter(getActivity(), new ArrayList<String>(Arrays.asList(resultStr)));
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_movies);

        gridView.setAdapter(moviesAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String linkAddress = (String) moviesAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class).putExtra(Intent.EXTRA_TEXT, linkAddress);
                startActivity(intent);
            }
        });
        FetchMoviesData moviesData = new FetchMoviesData();
        moviesData.execute();

        return rootView;
    }

    public class FetchMoviesData extends AsyncTask<String, Void, String[]> {

        public String[] getMoviePostersFromJSON(String moviesJsonStr) throws JSONException {
            final String TMD_RESULTS = "results";
            final String TMD_POSTERS = "poster_path";

            JSONObject moviesJson = new JSONObject(moviesJsonStr);
            JSONArray resultsArray = moviesJson.getJSONArray(TMD_RESULTS);

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject moviePoster = resultsArray.getJSONObject(i);
                resultStr[i] = "http://image.tmdb.org/t/p/w185/" + moviePoster.getString(TMD_POSTERS);
            }

            return resultStr;
        }

        @Override
        protected String[] doInBackground(String... params) {
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String moviesJsonStr;

            String sortByPopularityDesc = "popularity.desc";
            String sortByTopRated = "vote_average.desc";

            try {
                // Construct the URL for the moviedb query
                // Possible parameters are avaiable at themoviedb API page, at
                // http://docs.themoviedb.apiary.io/#
                final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
                final String SORT_BY_PARAM = "sort_by";
                final String API_KEY_PARAM = "api_key";

                Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                        .appendQueryParameter(SORT_BY_PARAM, sortByPopularityDesc)
                        .appendQueryParameter(API_KEY_PARAM, BuildConfig.THE_MOVIE_DB_API_KEY)
                        .build();

                URL url = new URL(builtUri.toString());

                // Create the request to themovieadb, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    builder.append(line).append("\n");
                }

                if (builder.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                moviesJsonStr = builder.toString();

            } catch (IOException e) {
                Log.e("PlaceholderFragment", "Error ", e);
                // If the code didn't successfully get the movies data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("PlaceholderFragment", "Error closing stream", e);
                    }
                }
            }
            try {
                return getMoviePostersFromJSON(moviesJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {
                moviesAdapter.clear();
                for (String moviesString : result) {
                    moviesAdapter.add(moviesString);
                }
            }
        }
    }
}
