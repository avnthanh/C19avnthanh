package au.edu.unsw.infs3634.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnLaunchActivity);
        // Implement onClickListener for btnLaunchActivity button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "On Click on the Launch Detail Activity button");
                launchDetailActivity("This message came from MainActivity");
            }
        });
    }

    //Create an intent that sends country code to detailactivity
// call the get country method from country. but when do we want this to happen e.g. button press
    //intent that sends country code to

    // Called when the user taps the Launch Detail Activity button
    private void launchDetailActivity(String message){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.INTENT_MESSAGE, message);
        startActivity(intent);
    }
}