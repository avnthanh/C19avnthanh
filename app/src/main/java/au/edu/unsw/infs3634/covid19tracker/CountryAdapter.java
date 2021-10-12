package au.edu.unsw.infs3634.covid19tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Adapter class that connects data set to the recycler view
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private List<Country>  mCountries;
    private ClickListener mListener;

    //    Initialise the dataset of the Adapter
    CountryAdapter(List<Country> countries, ClickListener listener){
        this.mCountries = countries;
        this.mListener = listener;
    }

    // Allows click events to be caught
    public interface ClickListener {
        void onProductClick(View view, int countryID);
    }

    // Inflate the row layout from xml when needed (just the view, no data)
    @NonNull
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout, parent, false);
        return new MyViewHolder(view, mListener);
    }

    // Bind the data to the TextView elements in each row
    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.MyViewHolder holder, int position) {
        final Country country = mCountries.get(position);
        int countryID = position;
        holder.countryName.setText(country.getCountry());
        holder.newCases.setText(country.getNewConfirmed());
        holder.totalCases.setText(country.getTotalConfirmed());
        holder.itemView.setTag(countryID);
    }

    // Total number of rows in the list
    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    // Create view holder. The view holder has two text view elements
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView countryName, newCases, totalCases;
        private ClickListener listener;

        public MyViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(MyViewHolder.this);
            countryName = itemView.findViewById(R.id.tvCountryName);
            newCases = itemView.findViewById(R.id.tvNewCases);
            totalCases = itemView.findViewById(R.id.tvTotalCases);
        }

        @Override
        public void onClick(View v) {
            listener.onProductClick(v, (Integer) v.getTag());
        }
    }

}

//public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
//    private List<Country> mCountries;
//    private ClickListener mListener;
//
//    CountryAdapter(List<Country> countries, ClickListener listener) {
//        this.mCountries = countries;
//        this.mListener = listener;
//    }
//
//    public interface ClickListener {
//        void onProductClick(View view, int countryID);
//    }
//
//    @NonNull
//    @Override
//    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        //here, we defined a view by the item_row_layout and returned it to the viewholder
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout, parent, false);
//        return new MyViewHolder(view, mListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CountryAdapter.MyViewHolder holder, int position) {
//        //Takes the position of a country in an arraylist and displays it
//        final Country country = mCountries.get(position);
//        int countryID = position;
//        holder.countryName.setText(country.getCountry());
//        holder.newCases.setText("+ " + country.getNewConfirmed());
//        holder.totalCases.setText(country.getTotalConfirmed());
//        holder.itemView.setTag(countryID);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mCountries.size();
//    }
//
//    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        public TextView countryName, newCases, totalCases;
//        private ClickListener listener;
//
//        public MyViewHolder(@NonNull View itemView, ClickListener listener) {
//            super(itemView);
//            this.listener = listener;
//            itemView.setOnClickListener(MyViewHolder.this);
//
//            //the view is passed from onCreateViewHolder and the textviews are linked and instantiated by id
//            countryName.findViewById(R.id.tvCountryName);
//            newCases.findViewById(R.id.tvNewCases);
//            totalCases.findViewById(R.id.tvTotalCases);
//        }
//
//        @Override
//        public void onClick(View view) {
//            listener.onProductClick(view, (Integer) view.getTag());
//        }
//    }
//
//}
