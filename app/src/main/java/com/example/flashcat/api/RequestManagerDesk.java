package com.example.flashcat.api;

import android.content.Context;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;

import com.example.flashcat.Model.Desk;
import com.example.flashcat.Model.Dictionary.WordItem;
import com.example.flashcat.Model.Flashcard;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class RequestManagerDesk {
    private Context context;
    private Retrofit retrofit;



    public RequestManagerDesk(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.7:5000/")
                .addConverterFactory(GsonConverterFactory.create(OnFetchDataListener.gson))
                .build();
        this.context = context;
    }

    public void getDeskById(OnFetchDataListener listener, int id) {
        String baseUrl = retrofit.baseUrl().toString();
        String fullUrl = baseUrl + "fc/getbyIdDesk/" + id;

        if (!URLUtil.isHttpUrl(fullUrl) && !URLUtil.isHttpsUrl(fullUrl)) {
            Toast.makeText(context, "Invalid URL!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("link", "getDeskById: " + fullUrl);
        CallDesk callDesk = retrofit.create(CallDesk.class);
        Call<Desk> call = callDesk.getDeskById(id);

        call.enqueue(new Callback<Desk>() {
            @Override
            public void onResponse(Call<Desk> call, Response<Desk> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetchData(response.body(), id);
            }

            @Override
            public void onFailure(Call<Desk> call, Throwable t) {
                String errorMessage = "Request Failed!";
                if (t != null && t.getMessage() != null) {
                    errorMessage = t.getMessage();
                }
                listener.onError(errorMessage);
            }
        });
    }

    public void getAllDesks(OnFetchDataListener listener) {
        String baseUrl = retrofit.baseUrl().toString();
        String fullUrl = baseUrl + "fc/getallDesk";

        if (!URLUtil.isHttpUrl(fullUrl) && !URLUtil.isHttpsUrl(fullUrl)) {
            Toast.makeText(context, "Invalid URL!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("link", "getAllDesks: " + fullUrl);
        CallDesk callDesk = retrofit.create(CallDesk.class);
        Call<List<Desk>> call = callDesk.getAllDesks();

        call.enqueue(new Callback<List<Desk>>() {
            @Override
            public void onResponse(Call<List<Desk>> call, Response<List<Desk>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetchDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Desk>> call, Throwable t) {
                String errorMessage = "Request Failed!";
                if (t != null && t.getMessage() != null) {
                    errorMessage = t.getMessage();
                }
                listener.onError(errorMessage);
            }
        });
    }
    public interface CallDesk {


        // Lấy tất cả
        // các desks
        @GET("fc/getallDesk")
        Call<List<Desk>> getAllDesks();

        // Lấy desk theo idDesk
        @GET("fc/getbyIdDesk/{id}")
        Call<Desk> getDeskById(@Path("id") int idDesk);

        // Thêm desk
        @POST("fc/addDesk")
        Call<Void> addDesk(@Body Desk desk);

        // Sửa desk
        @PUT("fc/updateDesk/{id}")
        Call<Void> updateDesk(@Path("id") int idDesk, @Body Desk desk);

        // Xóa desk
        @DELETE("fc/deleteDesk/{id}")
        Call<Void> deleteDesk(@Path("id") int idDesk);
        // Lấy tất cả flashcard theo id desk
        @GET("en/desks/{id}/flashcards")
        Call<List<Flashcard>> getAllFlashcardsByDeskId(@Path("id") int idDesk);

        // Lấy flashcard theo id flashcard
        @GET("fc/getAllFlashcardByIdDesk/{id}")
        Call<Flashcard> getFlashcardById(@Path("id") int idFlashcard);

        // Thêm flashcard cho một desk
        @POST("fc/addFlashcard")
        Call<Void> addFlashcardToDesk(@Body Flashcard flashcard);

        // Sửa flashcard
        @PUT("fc/updateFlashcard/{id}")
        Call<Void> updateFlashcard(@Path("id") int idFlashcard, @Body Flashcard flashcard);

        // Xóa flashcard
        @DELETE("fc/deleteFlashcard/{id}")
        Call<Void> deleteFlashcard(@Path("id") int idFlashcard);

    }

}
