package com.example.flashcat.api;

import com.example.flashcat.Model.Dictionary.WordItem;

public interface OnFetchDataListener {
    void onFetchData(WordItem wordItem,String message );
    void onError(String message);

}
