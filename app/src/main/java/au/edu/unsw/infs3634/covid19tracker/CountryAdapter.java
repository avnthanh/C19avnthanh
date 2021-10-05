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
    private ClickListener mListener;

    CountryAdapter(List<Country> country, ClickListener listener) {
        this.mCountries = country;
        mListener = listener;
    }

    public interface ClickListener {
        void onProductClick(View view, int countryID);
    }

    @NonNull
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //here, we defined a view by the item_row_layout and returned it to the viewholder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_layout, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Takes the position of a country in an arraylist and displays it
        final Country country = mCountries.get(position);
        int countryID = position;
        holder.tvCountry2.setText(country.getCountry());
        holder.tvNewCases2.setText("+ " + country.getNewConfirmed());
        holder.tvTotalCases2.setText(country.getTotalConfirmed());
        holder.itemView.setTag(countryID);

    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvCountry2, tvNewCases2, tvTotalCases2;
        private ClickListener listener;

        public MyViewHolder(@NonNull View itemView, ClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(MyViewHolder.this);

            //the view is passed from onCreateViewHolder and the textviews are linked and instantiated by id
            tvCountry2.findViewById(R.id.tvCountry2);
            tvNewCases2.findViewById(R.id.tvNewCases2);
            tvTotalCases2.findViewById(R.id.tvTotalCases2);
        }

        @Override
        public void onClick(View view) {
            listener.onProductClick(view, (Integer) view.getTag());
        }
    }

}
