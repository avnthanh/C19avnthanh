package au.edu.unsw.infs3634.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    public static final String INTENT_MESSAGE = "au.edu.unsw.infs3634.covid19tracker.intent_message";
    private TextView mCountry, mNewCases, mTotalCases, mNewDeaths, mTotalDeaths, mNewRecovered, mTotalRecovered;
    private ImageView mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        if (intent.hasExtra(INTENT_MESSAGE)) {
            Log.d(TAG, "INTENT_MESSAGE = " + intent.getStringExtra(INTENT_MESSAGE) );
            TextView detailMessage = findViewById(R.id.tvMessage);
            detailMessage.setText(intent.getStringExtra(INTENT_MESSAGE));
        }
        Button button = findViewById(R.id.btnShowVideo);
        // Implement onClickListener for the button
        button.setOnClickListener(new View.OnClickListener() {

            //this method calls the showVideo method which instantiates the intent for URL directory
            @Override
            public void onClick(View v) {
                showVideo("https://www.youtube.com/watch?v=BtN-goy9VOY");
            }
        });

        //initialise data cells & assign values
        mCountry = findViewById(R.id.tvCountry);
        mNewCases = findViewById(R.id.tvNewCases);
        mTotalCases = findViewById(R.id.tvTotalCases);
        mNewDeaths = findViewById(R.id.tvNewDeaths);
        mTotalDeaths = findViewById(R.id.tvTotalDeaths);
        mNewRecovered = findViewById(R.id.tvTotalRecovered);

        //Intent to extract country code string
        Intent intent2 = getIntent();   //need to retrieve intent to initialise
        Bundle bundle = intent.getExtras();     //instantiates and assigns bundle value using the getExtras method to extract intent message from intent?

        if (intent.hasExtra(INTENT_MESSAGE)) {
            Log.d(TAG, "INTENT_MESSAGE " + bundle.getStringArrayList(INTENT_MESSAGE));
            String countryCode = intent.getStringExtra(INTENT_MESSAGE);     //uses getStringExtra to retrieve intent message and assigns to countryCode
            ArrayList<Country> countries = Country.getCountries();  //instantiates and assigns arraylist as country's array list

            for (final Country country : countries) {   //ensures country data matches up
                if (country.getCountryCode().equals(countryCode)) {
                    setTitle(country.getCountryCode());     //sets title of activity (??)
                    mCountry.setText(country.getCountryCode()); //assign values
                    mNewCases.setText(country.getNewConfirmed());
                    mTotalCases.setText(country.getTotalConfirmed());
                    mNewDeaths.setText(country.getNewDeaths());
                    mTotalDeaths.setText(country.getTotalDeaths());
                    mNewRecovered.setText(country.getNewRecovered());
                    mTotalRecovered.setText(country.getTotalRecovered());

                    //intent that google searches "Covid 19" + country name
                    mSearch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.au/search?q=covid " + country.getCountry()));
                            startActivity(intent);
                        }
                    });
                    }
                }
            }
        }


    // Called when the user taps the Show Video button
    private void showVideo(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

}