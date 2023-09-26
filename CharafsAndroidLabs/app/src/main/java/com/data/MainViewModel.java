package com.data;

import android.widget.ImageButton;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public MutableLiveData<String> editString = new MutableLiveData<>();
    public MutableLiveData<Boolean> isChosen = new MutableLiveData<>();




}
