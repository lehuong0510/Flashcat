package com.example.flashcat.api;

import com.example.flashcat.Model.Desk;
import com.example.flashcat.Model.Dictionary.WordItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.threeten.bp.LocalDateTime;

import java.util.List;

public interface OnFetchDataListener {
    void onFetchData(WordItem wordItem,String message );
    void onFetchData(Desk desk, int message );
    void onError(String message);
    void onFetchDataList(List<Desk> listDesk);
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .create();

}
