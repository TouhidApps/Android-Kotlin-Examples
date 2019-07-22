package com.touhidapps.livedataexample;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private String TAG = this.getClass().getSimpleName();

    // MARK - use MutableLiveData with data type
    public MutableLiveData<Integer> myNumber = new MutableLiveData<>();

//    // MARK - Use return type as MutableLiveData
//    public MutableLiveData<Integer> getMyNumber() {
//        Log.d(TAG, "getMyNumber: " + myNumber);
//
//        return myNumber;
//    }
//
//    public void setMyNumber(Integer myNumber) {
//        Log.d(TAG, "setMyNumber: " + myNumber);
//
//        // MARK - Set value to live data using setValue() method
//        this.myNumber.setValue(myNumber);
//    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared: ");
    }

}
