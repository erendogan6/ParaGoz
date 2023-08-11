package com.example.paragoz.view;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.paragoz.adaptor.adaptor;
import com.example.paragoz.databinding.ActivityMainBinding;
import com.example.paragoz.model.liste;
import com.example.paragoz.servis.dovizAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.Objects;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private ActivityMainBinding activityMainBinding;
    private CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        String BASE_URL = "https://api.collectapi.com/";
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        veriCek();
    }
    private void veriCek(){
            final dovizAPI API = retrofit.create(dovizAPI.class);

            compositeDisposable = new CompositeDisposable();

            compositeDisposable.add(API.getData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handle));
            /*
            Call<JsonObject> call=API.getData();
            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if (response.isSuccessful()) {
                        list = new Gson().fromJson(response.body(), liste.class);
                        adaptor Adaptor = new adaptor(list);
                        activityMainBinding.recycle.setAdapter(Adaptor);
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    // İstek hatası durumunda hata işleme yapın
                    System.out.println(t.getMessage());
                    System.out.println(t.toString());
                }
            });

    */
    }
    // RxJava için Handle methodu
    private void handle(JsonObject jsonObject) {
        liste list = new Gson().fromJson(jsonObject, liste.class);

        activityMainBinding.recycle.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adaptor Adaptor = new adaptor(list);
        activityMainBinding.recycle.setAdapter(Adaptor);
    }
    protected void onDestroy (){
        // Kalıntıların bellekten temizlenmesi için
        super.onDestroy();
        compositeDisposable.clear();
    }

    }