package com.example.myapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mDestination;
    private final MutableLiveData<String> mTripDate;

    public HomeViewModel() {
        mDestination = new MutableLiveData<>();
        mTripDate = new MutableLiveData<>();
        
        mDestination.setValue("This is home fragment");
    }

    public LiveData<String> getDestination() {
        return mDestination;
    }

    public LiveData<String> getTripDate() {
        return mTripDate;
    }
}