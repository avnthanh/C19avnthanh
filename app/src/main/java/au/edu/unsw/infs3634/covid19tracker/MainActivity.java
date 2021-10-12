package au.edu.unsw.infs3634.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Country> mCountries = new ArrayList<>();
    private CountryAdapter cAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        CountryAdapter.ClickListener listener = new CountryAdapter.ClickListener() {
            @Override
            public void onProductClick(View view, int countryID) {
                final Country country = mCountries.get(countryID);
                Toast.makeText(getApplicationContext(), country.getCountry() + "\nNew Cases = " +country.getNewConfirmed(), Toast.LENGTH_SHORT).show();
            }
        };

        cAdapter = new CountryAdapter(mCountries, listener);
        mRecyclerView.setAdapter(cAdapter);
    }

}