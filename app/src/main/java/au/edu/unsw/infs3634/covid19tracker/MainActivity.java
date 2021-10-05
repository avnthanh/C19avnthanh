package au.edu.unsw.infs3634.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountries = new ArrayList<>();
        cAdapter = new CountryAdapter(mCountries, listener);
        mRecyclerView = findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter((cAdapter));
    }

    CountryAdapter.ClickListener listener = new CountryAdapter.ClickListener() {
        @Override
        public void onProductClick(View view, int countryID) {
            final Country country = mCountries.get(countryID);
//            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//            setContentView(R.layout.activity_detail);
        }
    };

//    // Called when the user taps the Launch Detail Activity button
//    private void launchDetailActivity(String message) {
//        Intent intent = new Intent(this, DetailActivity.class);
//        intent.putExtra(DetailActivity.INTENT_MESSAGE, message);
//        startActivity(intent);

}