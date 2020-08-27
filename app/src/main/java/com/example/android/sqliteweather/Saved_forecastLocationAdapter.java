package com.example.android.sqliteweather;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import com.example.android.sqliteweather.data.Saved_forecastLocation;

import java.util.List;

public class Saved_forecastLocationAdapter extends RecyclerView.Adapter<Saved_forecastLocationAdapter.SavedForecastLocationViewHolder> {
    private List<Saved_forecastLocation> mSavedForecastLocation;
    private OnSaveForecastLocationClickListener mSavedForecastLocationClickListener;

    interface OnSaveForecastLocationClickListener{
        void onSavedForecastLocationClick(Saved_forecastLocation saved_forecastLocation);
    }
    public Saved_forecastLocationAdapter(OnSaveForecastLocationClickListener clickListener){
        mSavedForecastLocationClickListener = clickListener;
    }
    public void updateSavedForecastLocations(List<Saved_forecastLocation> saved_forecastLocations){
        mSavedForecastLocation = saved_forecastLocations;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount(){
        if(mSavedForecastLocation != null){
            return mSavedForecastLocation.size();
        }else {
            return 0;
        }
    }
//    @NonNull
    @Override
    public SavedForecastLocationViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        View itemView = inflater.inflate(R.layout.saved_location_list, parent, false);
        return new SavedForecastLocationViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(SavedForecastLocationViewHolder holder, int position){
        holder.bind(mSavedForecastLocation.get(position));
    }
    class SavedForecastLocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mLocationTV;

        SavedForecastLocationViewHolder(View itemView){
            super(itemView);
            mLocationTV = itemView.findViewById(R.id.save_location_tv);
            itemView.setOnClickListener(this);
        }
        public void bind(Saved_forecastLocation saved_forecastLocation){
            mLocationTV.setText(saved_forecastLocation.name);
        }
        @Override
        public void onClick(View view){
            Saved_forecastLocation saved_forecastLocation = mSavedForecastLocation.get(getAdapterPosition());
            mSavedForecastLocationClickListener.onSavedForecastLocationClick(saved_forecastLocation);
        }
    }

}
