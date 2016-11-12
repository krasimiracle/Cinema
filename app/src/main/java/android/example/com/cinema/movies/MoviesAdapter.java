package android.example.com.cinema.movies;

import android.content.Context;
import android.example.com.cinema.R;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by B3f0r on 03-May-16.
 */
public class MoviesAdapter extends ArrayAdapter {

    private Context context;
    private LayoutInflater inflater;

    private ArrayList<String> imageUrls;
    private ArrayList<String> movieTitles;

    @Override
    public void clear() {
        super.clear();
    }

    MoviesAdapter(Context context, ArrayList<String> imageUrls) {
        super(context, R.layout.list_item_movie_posters, imageUrls);
        this.context = context;
        this.imageUrls = imageUrls;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.list_item_movie_posters, parent, false);
        }
        Glide
                .with(context)
                .load(imageUrls.get(position))
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into((ImageView) convertView);

        return convertView;
    }
}
