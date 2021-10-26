package au.edu.unsw.infs3634.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private List<Country> mCountries = new ArrayList<>();
    private CountryAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this passes the data from Country class to mCountries, which then is passed to Adapter
        mCountries = Country.getCountries();

        // Instantiate Recyclerview
        mRecyclerView = findViewById(R.id.recycleView);
        // Set setHasFixedSize true if contents of the adapter does not change it's height or the width
        mRecyclerView.setHasFixedSize(true);

        // Specify linear layout manager for RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        CountryAdapter.ClickListener listener = new CountryAdapter.ClickListener() {
            @Override
            public void onProductClick(View view, int countryID) {
                Country country = mCountries.get(countryID);
                // Display details for the selected RecyclerView item (product on the list)
                Toast.makeText(getApplicationContext(), country.getCountry()+"\nNew Cases = + "+country.getNewConfirmed(), Toast.LENGTH_SHORT).show();
            }
        };

        // Instantiate adapter
        mAdapter = new CountryAdapter(mCountries, listener);
        // Set the adapter for the recycler view
        mRecyclerView.setAdapter(mAdapter);
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