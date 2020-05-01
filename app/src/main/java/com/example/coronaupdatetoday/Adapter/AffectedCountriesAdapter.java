package com.example.coronaupdatetoday.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.coronaupdatetoday.Activity.SelectedCountryInformationActivity;
import com.example.coronaupdatetoday.AffectedCountriesDataModel.AffectedCountriesResponce;
import com.example.coronaupdatetoday.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class AffectedCountriesAdapter extends RecyclerView.Adapter<AffectedCountriesAdapter.MyViewHolder> {

    private Context mContext;
    private List <AffectedCountriesResponce> postList;

    //String Tag = "AdPostRecylerViewAdapter";

    public AffectedCountriesAdapter(Context mContext, List<AffectedCountriesResponce> postList) {
        this.mContext = mContext;
        this.postList = postList;
    }

    @Override
    public AffectedCountriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        final LayoutInflater inflater = LayoutInflater.from( mContext );
        view = inflater.inflate( R.layout.countrys_data_item,parent,false);
        final AffectedCountriesAdapter.MyViewHolder viewHolder = new AffectedCountriesAdapter.MyViewHolder( view );
        viewHolder.item.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent( mContext, SelectedCountryInformationActivity.class );
                intent.putExtra( "Country",postList.get( viewHolder.getAdapterPosition()).getCountry());
                intent.putExtra( "Flag",postList.get( viewHolder.getAdapterPosition()).getCountryInfo().getFlag());
                intent.putExtra( "Cases",postList.get( viewHolder.getAdapterPosition()).getCases());
                intent.putExtra( "TodayCases",postList.get( viewHolder.getAdapterPosition()).getTodayCases());
                intent.putExtra( "TodayDeaths",postList.get( viewHolder.getAdapterPosition()).getTodayDeaths());
                intent.putExtra( "Deaths",postList.get( viewHolder.getAdapterPosition()).getDeaths());
                intent.putExtra( "Active",postList.get( viewHolder.getAdapterPosition()).getActive());
                intent.putExtra( "Recovered",postList.get( viewHolder.getAdapterPosition()).getRecovered());
                mContext.startActivity(intent);
            }
        } );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AffectedCountriesAdapter.MyViewHolder holder, int position) {
        holder.country_name.setText( postList.get( position ).getCountry());

        AffectedCountriesResponce product = postList.get(position);
        Glide.with(mContext)
                .load(product.getCountryInfo().getFlag())
                .into(holder.flag);

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country_name;
        LinearLayout item;
        ImageView flag;

        public MyViewHolder(View itemView) {
            super( itemView );
            item = itemView.findViewById( R.id.item );
            country_name = itemView.findViewById( R.id.country_name );
            flag = itemView.findViewById( R.id.flag );
        }
    }

    public void updateList(List<AffectedCountriesResponce> postList){
        this.postList=postList;
        notifyDataSetChanged();
    }

}
