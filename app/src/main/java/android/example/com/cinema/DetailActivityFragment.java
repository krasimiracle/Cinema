package android.example.com.cinema;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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

    // TODO: 07-Jun-16 Change dummy youtube link with trailer's link

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView posterImage = (ImageView) rootView.findViewById(R.id.poster_image);
        ImageView backgroundImage = (ImageView) rootView.findViewById(R.id.background_image);


        // The detail Activity called via intent.  Inspect the intent for data.
        final Intent intent = getActivity().getIntent();

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {

            // Load image string from intent. Used for poster image.

            String linkStr = intent.getStringExtra(Intent.EXTRA_TEXT);

            // Load poster image

            Glide
                    .with(getContext())
                    .load(linkStr)
                    .centerCrop()
                    .crossFade(1)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(posterImage);

            // Load background image

            Glide
                    .with(this)
                    .load(linkStr)
                    .into(backgroundImage);

            backgroundImage.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent trailerIntent = new Intent();
                    trailerIntent.setAction(Intent.ACTION_VIEW);
                    trailerIntent.setComponent(new ComponentName("com.google.android.youtube","com.google.android.youtube.PlayerActivity"));
                    trailerIntent.addCategory(Intent.CATEGORY_BROWSABLE);
                    trailerIntent.setData(Uri.parse("https://www.youtube.com/watch?v=MVIBXXMx7Lo"));
                    startActivity(trailerIntent);
                }
            });
        }


        return rootView;
    }
}
