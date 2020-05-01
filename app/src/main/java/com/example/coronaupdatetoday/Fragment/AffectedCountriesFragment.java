package com.example.coronaupdatetoday.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coronaupdatetoday.Adapter.AffectedCountriesAdapter;
import com.example.coronaupdatetoday.AffectedCountriesDataModel.AffectedCountriesResponce;
import com.example.coronaupdatetoday.Network.RetrofitClient;
import com.example.coronaupdatetoday.R;
import com.leo.simplearcloader.SimpleArcLoader;

import java.util.ArrayList;
import java.util.List;


public class AffectedCountriesFragment extends Fragment {

    RecyclerView recylerView;
    EditText search;
    LinearLayout searchContent;
    SimpleArcLoader loader;

    AffectedCountriesAdapter affectedCountriesAdapter;

    private List <AffectedCountriesResponce> postList = new ArrayList <>();

    AffectedCountriesResponce affectedCountriesResponce;

    String TAG = "AffectedCountriesFragment";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_efficient_countries, container, false );

        recylerView = view.findViewById( R.id.recylerView );
        affectedCountriesAdapter = new AffectedCountriesAdapter(getContext(), postList );
        recylerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recylerView.setAdapter(affectedCountriesAdapter);

        search = view.findViewById( R.id.search );

        search.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        } );

        searchContent = view.findViewById( R.id.searchContent );
        searchContent.setVisibility( View.GONE );

        loader = view.findViewById( R.id.loader );
        loader.start();
        loader.setVisibility( View.VISIBLE );


        fetchRecylerViewData();

        return view;
    }

    private void fetchRecylerViewData() {
        Call <List<AffectedCountriesResponce>> call = RetrofitClient.getInstance().getApiInterface().AffectedCountriesResponce();
        call.enqueue( new Callback<List <AffectedCountriesResponce>>() {
            @Override
            public void onResponse(Call<List<AffectedCountriesResponce>> call, Response <List<AffectedCountriesResponce>> response) {
                Log.e( TAG, "onResponseBB: "+response.body().toString()  );
                if (response.isSuccessful())
                {
                    postList = response.body();

                    affectedCountriesAdapter.updateList( postList );
                    loader.stop();
                    loader.setVisibility( View.GONE );
                    searchContent.setVisibility( View.VISIBLE );

                }else
                {
                    Toast.makeText( getContext(), "Fail", Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<List<AffectedCountriesResponce>> call, Throwable t) {
                Toast.makeText( getContext(), "onFailure", Toast.LENGTH_SHORT ).show();
                Log.e( TAG, "onFailure: " + t.getLocalizedMessage());
            }
        } );
    }


    private void filter(String text) {
        ArrayList<AffectedCountriesResponce> newList = new ArrayList <>(  );
        for (AffectedCountriesResponce itemList : postList)
        {
            if (itemList.getCountry().toLowerCase().contains(text.toLowerCase())) {
                newList.add(itemList);
            }
        }
        affectedCountriesAdapter.updateList(newList);
    }


}
