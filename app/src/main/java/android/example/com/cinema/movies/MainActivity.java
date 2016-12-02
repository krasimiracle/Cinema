package android.example.com.cinema.movies;

import android.content.Intent;
import android.example.com.cinema.R;
import android.example.com.cinema.settings.SettingsActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivityFragment())
                    .commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handles action bar item clicks. The action bar will
        // automatically handle clicks of the Home/Up button, as long
        // as you specify a parent activity in the AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Open Settings menu
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
