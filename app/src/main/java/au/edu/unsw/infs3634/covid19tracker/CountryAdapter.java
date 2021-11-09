package au.edu.unsw.infs3634.covid19tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Adapter class that connects data set to the recycler view
public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> implements Filterable{
    private List<Country>  mCountries;
    private List<Country> mCountriesFiltered;
    private ClickListener mListener;

    //    Initialise the dataset of the Adapter
    CountryAdapter(List<Country> countries, ClickListener listener){
        this.mCountries = countries;
        this.mCountriesFiltered = countries;
        this.mListener = listener;
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            //Hamid's CharSequence was called constraint for some reason, and his FilterResults as results
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                //convert charSequence to String
                String charString = charSequence.toString();
                if(charString.isEmpty()) {
                    mCountriesFiltered = mCountries;
                } else {
                    ArrayList<Country> filteredList = new ArrayList<>();
                    for(Country country : mCountries) {
                        if(country.getCountry().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(country);
                        }
                    }
                    mCountriesFiltered = filteredList;
                }

                //instantiate an instance of FilterResults. Note that this is different from publishResults parameter.
                FilterResults filterResults = new FilterResults();
                filterResults.values = mCountriesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mCountriesFiltered = (List<Country>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    // Allows click events to be caught
    public interface ClickListener {
        void onCountryClick(View view, String countryCode);
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
//        final Country country = mCountries.get(position);
        Country country = mCountriesFiltered.get(position);
        DecimalFormat df = new DecimalFormat( "#,###,###,###" );

        //converting Integer value of Country methods to String so setText can work
        int newConfirmedInt = country.getNewConfirmed();
        String newConfirmedString = String.valueOf(newConfirmedInt);
        int totalConfirmedInt = country.getTotalConfirmed();
        String totalConfirmedString = String.valueOf(totalConfirmedInt);

        //binding the data to textviews
        holder.countryName.setText(country.getCountry());
        holder.newCases.setText("+ " + newConfirmedString);
        holder.totalCases.setText(totalConfirmedString);
//        holder.newCases.setText(country.getNewConfirmed());
//        holder.totalCases.setText(country.getTotalConfirmed());
        holder.itemView.setTag(country.getCountryCode());
    }

    // Total number of rows in the list
    @Override
    public int getItemCount() {
        return mCountriesFiltered.size();
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
            listener.onCountryClick(v, (String) v.getTag());
        }
    }

    //Sorting method
    public void sort(final int sortMethod) {
        if (mCountriesFiltered.size() > 0) {
            Collections.sort(mCountriesFiltered, new Comparator<Country>() {
                @Override
                public int compare(Country t1, Country t2) {
                    if(sortMethod == 1) {
                        //compare the country names of t1 and t2
                        return t1.getCountry().compareTo(t2.getCountry());
                    } else if (sortMethod == 2)
                        //compare the new cases value of t1 and t2
                        return String.valueOf(t1.getNewConfirmed()).compareTo(String.valueOf(t2.getNewConfirmed()));
                    //sort by name if no other options
                    return t1.getCountry().compareTo(t2.getCountry());
                }
            });
        }
        notifyDataSetChanged();
    }

}

