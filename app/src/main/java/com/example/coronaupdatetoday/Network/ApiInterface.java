package com.example.coronaupdatetoday.Network;

import com.example.coronaupdatetoday.AffectedCountriesDataModel.AffectedCountriesResponce;
import com.example.coronaupdatetoday.GlobalDataModel.GlobalDataResponce;
import com.example.coronaupdatetoday.OurCountryDataModel.OurCountryResponce;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("v2/countries/bangladesh?yesterday=false&strict=false")
    Call <OurCountryResponce> OurCountryResponce();

    @GET("v2/all")
    Call <GlobalDataResponce> GlobalDataResponce();

    @GET("v2/countries")
    Call <List <AffectedCountriesResponce>> AffectedCountriesResponce();

}
