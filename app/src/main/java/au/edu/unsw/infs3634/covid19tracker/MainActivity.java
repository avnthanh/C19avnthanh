package au.edu.unsw.infs3634.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private List<Country> mCountries;
    private CountryAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountries = new ArrayList<>();
        prepareCountries();

        cAdapter = new CountryAdapter(mCountries);
    }


    // Called when the user taps the Launch Detail Activity button
    private void launchDetailActivity(String message){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.INTENT_MESSAGE, message);
        startActivity(intent);
    }

    private void prepareCountries() {
        Country country = new Country(country);
        mCountries.add(country);
    }
}