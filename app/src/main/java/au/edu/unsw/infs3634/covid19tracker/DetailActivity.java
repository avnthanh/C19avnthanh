package au.edu.unsw.infs3634.covid19tracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    public static final String INTENT_MESSAGE = "au.edu.unsw.infs3634.covid19tracker.intent_message";
    private TextView mCountry, mNewCases, mTotalCases, mNewDeaths, mTotalDeaths, mNewRecovered, mTotalRecovered;
    private ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        mCountry = findViewById(R.id.tvCountry);
        mNewCases = findViewById(R.id.tvNewCases);
        mTotalCases = findViewById(R.id.tvTotalCases);
        mNewDeaths = findViewById(R.id.tvNewDeaths);
        mTotalDeaths = findViewById(R.id.tvTotalDeaths);
        mNewRecovered = findViewById(R.id.tvNewRecovered);
        mTotalRecovered = findViewById(R.id.tvTotalRecovered);
        mSearch = findViewById(R.id.ivSearch);

        //get Intent that started this activity (from main) and extract string
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (intent.hasExtra(INTENT_MESSAGE)) {
            Log.d(TAG, "INTENT_MESSAGE = " + bundle.getStringArrayList(INTENT_MESSAGE) );
            String countryCode = intent.getStringExtra(INTENT_MESSAGE);

            //Implement GSON Library
             Gson gson = new Gson();
             Response response = gson.fromJson(Response.json, Response.class);
             List<Country> countries = response.getCountries();

             for (final Country country: countries) {
                 if (country.getCountryCode().equals(countryCode)) {
                     DecimalFormat df = new DecimalFormat( "#,###,###,###" );

                     //setting title of activity
                      setTitle(country.getCountryCode());

                      //setting country name
                      mCountry.setText(country.getCountry());

                      //setting value for other textviews
                      mNewCases.setText(df.format(country.getNewConfirmed()));
                      mTotalCases.setText(df.format(country.getTotalConfirmed()));
                      mNewDeaths.setText(df.format(country.getNewDeaths()));
                      mTotalDeaths.setText(df.format(country.getTotalDeaths()));
                      mNewRecovered.setText(df.format(country.getNewRecovered()));
                      mTotalRecovered.setText(df.format(country.getTotalRecovered()));

                      //intent to open google search for "COVID19 + country name"
                      mSearch.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.au/sedarch?q=covid " + country.getCountry()));
                              startActivity(intent);
                          }
                      });
                 }
             }
        }
    }
}
