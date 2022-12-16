package com.example.dma12;

public interface Callback<R>{
    void runResultOnUiThread(R result);
}
