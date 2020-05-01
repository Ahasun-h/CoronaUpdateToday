package com.example.coronaupdatetoday.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.coronaupdatetoday.R;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class SelectedCountryInformationActivity extends AppCompatActivity {

    TextView totalCases,todayCases,todayDeaths,totalDeaths,activeCases,recover,countryName;
    ScrollView scrollState;
    SimpleArcLoader loader;
    PieChart piechart;
    ImageView flag;

    String Country,Flag,Cases,TodayCases,TodayDeaths,Deaths,Active,Recovered;

    String TAG = "SelectedCountryInformationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_selected_country_information );


        totalCases = findViewById( R.id.totalCases);
        todayCases = findViewById( R.id.todayCases);
        todayDeaths = findViewById( R.id.todayDeaths);
        totalDeaths = findViewById( R.id.totalDeaths);
        activeCases = findViewById( R.id.activeCases);
        recover = findViewById( R.id.recover);

        countryName = findViewById( R.id.country_name);
        flag = findViewById( R.id.flag);

        scrollState = findViewById( R.id.scrollState);
        scrollState.setVisibility( View.GONE );

        loader = findViewById( R.id.loader);
        loader.start();
        loader.setVisibility( View.VISIBLE );

        piechart = findViewById( R.id.piechart);

        Country = getIntent().getStringExtra("Country");
        Flag = getIntent().getStringExtra("Flag");
        Cases = getIntent().getStringExtra("Cases");
        TodayCases = getIntent().getStringExtra("TodayCases");
        TodayDeaths = getIntent().getStringExtra("TodayDeaths");
        Deaths = getIntent().getStringExtra("Deaths");
        Active = getIntent().getStringExtra("Active");
        Recovered = getIntent().getStringExtra("Recovered");





        loadData();


    }

    private void loadData() {


        countryName.setText( Country );

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.color.colorBlack).error(R.color.colorBlack);
        Glide.with(this).load(Flag).apply(requestOptions).into(flag);

        totalCases.setText(Cases);
        todayCases.setText( TodayCases);
        todayDeaths.setText( TodayDeaths);
        totalDeaths.setText( Deaths);
        activeCases.setText( Active);
        recover.setText( Recovered);

        piechart.addPieSlice( new PieModel( "Cases",Integer.parseInt(Cases), Color.parseColor( "#BB1C1E" )));
        piechart.addPieSlice( new PieModel( "Deaths",Integer.parseInt(Deaths), Color.parseColor( "#EE6F52" )));
        piechart.addPieSlice( new PieModel( "Active",Integer.parseInt(Active), Color.parseColor( "#02A002" )));
        piechart.addPieSlice( new PieModel( "Recover",Integer.parseInt(Recovered), Color.parseColor( "#4D96D6" )));
        piechart.startAnimation();

        loader.stop();
        loader.setVisibility( View.GONE );
        scrollState.setVisibility( View.VISIBLE );

    }
}
