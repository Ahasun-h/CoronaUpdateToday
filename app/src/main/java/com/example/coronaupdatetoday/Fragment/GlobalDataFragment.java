package com.example.coronaupdatetoday.Fragment;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronaupdatetoday.GlobalDataModel.GlobalDataResponce;
import com.example.coronaupdatetoday.Network.RetrofitClient;
import com.example.coronaupdatetoday.OurCountryDataModel.OurCountryResponce;
import com.example.coronaupdatetoday.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalDataFragment extends Fragment {


    TextView totalCases,todayCases,todayDeaths,totalDeaths,activeCases,recover;
    ScrollView scrollState;
    SimpleArcLoader loader;
    PieChart piechart;

    String TAG = "GlobalDataFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_global_data, container, false );


        totalCases = view.findViewById( R.id.totalCases);
        todayCases = view.findViewById( R.id.todayCases);
        todayDeaths = view.findViewById( R.id.todayDeaths);
        totalDeaths = view.findViewById( R.id.totalDeaths);
        activeCases = view.findViewById( R.id.activeCases);
        recover = view.findViewById( R.id.recover);

        scrollState = view.findViewById( R.id.scrollState);
        scrollState.setVisibility( View.GONE );

        loader = view.findViewById( R.id.loader);
        loader.start();
        loader.setVisibility( View.VISIBLE );

        piechart = view.findViewById( R.id.piechart);

        fetchGlobalData();

        return view;
    }

    private void fetchGlobalData() {

        Call <GlobalDataResponce> call = RetrofitClient.getInstance().getApiInterface().GlobalDataResponce();
        call.enqueue(new Callback <GlobalDataResponce>() {
            @Override
            public void onResponse(Call<GlobalDataResponce> call, Response <GlobalDataResponce> response) {
                if (response.isSuccessful()) {
                    GlobalDataResponce globalDataResponce = response.body();

                    totalCases.setText( globalDataResponce.getCases().toString() );
                    todayCases.setText( globalDataResponce.getTodayCases().toString() );
                    todayDeaths.setText( globalDataResponce.getTodayDeaths().toString() );
                    totalDeaths.setText( globalDataResponce.getDeaths().toString() );
                    activeCases.setText( globalDataResponce.getActive().toString() );
                    recover.setText( globalDataResponce.getRecovered().toString() );


                    piechart.addPieSlice( new PieModel( "Cases",Integer.parseInt( globalDataResponce.getCases()), Color.parseColor( "#BB1C1E" )));
                    piechart.addPieSlice( new PieModel( "Deaths",Integer.parseInt( globalDataResponce.getDeaths()), Color.parseColor( "#EE6F52" )));
                    piechart.addPieSlice( new PieModel( "Active",Integer.parseInt( globalDataResponce.getActive()), Color.parseColor( "#02A002" )));
                    piechart.addPieSlice( new PieModel( "Recover",Integer.parseInt( globalDataResponce.getRecovered()), Color.parseColor( "#4D96D6" )));
                    piechart.startAnimation();

                    loader.stop();
                    loader.setVisibility( View.GONE );
                    scrollState.setVisibility( View.VISIBLE );

                } else {
                    Toast.makeText( getContext() ,"Error", Toast.LENGTH_SHORT ).show();
                    Log.e( TAG, "onResponse: "+response.body().toString() );
                }
            }

            @Override
            public void onFailure(Call<GlobalDataResponce> call, Throwable t) {
                Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e( TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

}
