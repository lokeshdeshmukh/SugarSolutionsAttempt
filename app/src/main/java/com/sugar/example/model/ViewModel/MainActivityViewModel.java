package com.sugar.example.model.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sugar.example.model.pojo.TypeOfProducts;
import com.sugar.example.rest.ApiClient;
import com.sugar.example.rest.ApiInterface;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityViewModel extends ViewModel {

    private CompositeDisposable disposable = new CompositeDisposable();
    private String TAG = MainActivityViewModel.class.getSimpleName();
    private MutableLiveData<ArrayList<Map<String, ArrayList<String>>>> mainResponse;

    public MutableLiveData<ArrayList<Map<String, ArrayList<String>>>> getMainResponse() {
        if (mainResponse == null) {
            mainResponse = new MutableLiveData<>();
            loadSugarProducts();
        }
        return mainResponse;
    }

    private void loadSugarProducts() {
        // do async operation to fetch users
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<TypeOfProducts> call = apiService.getCategories();

        call.enqueue(new Callback<TypeOfProducts>() {
            @Override
            public void onResponse(Call<TypeOfProducts> call, Response<TypeOfProducts> response) {
                int statusCode = response.code();
                ArrayList<Map<String, ArrayList<String>>> movies = response.body().getCategories();
                mainResponse.setValue(movies);

            }

            @Override
            public void onFailure(Call<TypeOfProducts> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
                mainResponse.setValue(null);
            }
        });

    }

    private void getSpecificCategoryData(String type, String id) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<TypeOfProducts> call = apiService.getCategories();

        call.enqueue(new Callback<TypeOfProducts>() {
            @Override
            public void onResponse(Call<TypeOfProducts> call, Response<TypeOfProducts> response) {
                int statusCode = response.code();
                ArrayList<Map<String, ArrayList<String>>> movies = response.body().getCategories();
                mainResponse.setValue(movies);
            }

            @Override
            public void onFailure(Call<TypeOfProducts> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }
}
