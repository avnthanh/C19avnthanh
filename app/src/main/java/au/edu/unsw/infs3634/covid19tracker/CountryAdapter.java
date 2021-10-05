package au.edu.unsw.infs3634.covid19tracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private List<Country> mCountries;

    CountryAdapter(List<Country> country) {
        this.mCountries = country;
    }

    @NonNull
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //here, we defined a view by the item_row_layout and returned it to the viewholder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Takes the position of a country in an arraylist and displays it
        final Country country = mCountries.get(position);
        holder.tvCountry2.setText(country.getCountry());
        holder.tvNewCases2.setText("+ " + country.getNewConfirmed());
        holder.tvTotalCases2.setText(country.getTotalConfirmed());

    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCountry2, tvNewCases2, tvTotalCases2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //the view is passed from onCreateViewHolder and the textviews are linked and instantiated by id
            tvCountry2.findViewById(R.id.tvCountry2);
            tvNewCases2.findViewById(R.id.tvNewCases2);
            tvTotalCases2.findViewById(R.id.tvTotalCases2);
        }
    }

}
