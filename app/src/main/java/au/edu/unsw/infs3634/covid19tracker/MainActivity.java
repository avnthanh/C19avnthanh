package au.edu.unsw.infs3634.covid19tracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private CountryAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this passes the data from Country class to mCountries, which then is passed to Adapter
//        mCountries = Country.getCountries();

        // Instantiate Recyclerview
        mRecyclerView = findViewById(R.id.recycleView);
        // Set setHasFixedSize true if contents of the adapter does not change it's height or the width
        mRecyclerView.setHasFixedSize(true);

        // Specify linear layout manager for RecyclerView
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        CountryAdapter.ClickListener listener = new CountryAdapter.ClickListener() {
            @Override
            public void onCountryClick(View view, String countryCode) {
                launchDetailActivity(countryCode);
            }
        };

        //gson Library
        Gson gson = new Gson();
        Response response = gson.fromJson(Response.json, Response.class);

        // Instantiate adapter
        mAdapter = new CountryAdapter(response.getCountries(), listener);
        // Set the adapter for the recycler view
        mRecyclerView.setAdapter(mAdapter);
    }

    private void launchDetailActivity(String message) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.INTENT_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu, instantiate and assign the resource and menu instance
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //instantiate searchview object and set it to layout id
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //using an instance of MenuItem and assigning its value depending on what the user clicked
        switch (item.getItemId()) {
            case R.id.sortName:
                //assigns the value of sortMethod and passes it to sort method in CountryAdapter
                mAdapter.sort(1);
                return true;
            case R.id.sortNewCases:
                mAdapter.sort(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}