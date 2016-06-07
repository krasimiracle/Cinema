package android.example.com.cinema;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);


        // The detail Activity called via intent.  Inspect the intent for data.
        final Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String linkStr = intent.getStringExtra(Intent.EXTRA_TEXT);
            ((TextView) rootView.findViewById(R.id.movie_name))
                    .setText(linkStr);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.poster_detail_image);

            Glide.with(this).load(linkStr).into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent trailerIntent = new Intent();
                    trailerIntent.setAction(Intent.ACTION_VIEW);
                    trailerIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                    trailerIntent.setData(Uri.parse("https://www.youtube.com/watch?v=MVIBXXMx7Lo"));
                    startActivity(trailerIntent);
                }
            });
        }


        return rootView;
    }
}
