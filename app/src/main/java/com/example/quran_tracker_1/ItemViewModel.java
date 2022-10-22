package com.example.quran_tracker_1;

import android.content.ClipData;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<Bundle> selectedItem = new MutableLiveData<Bundle>();

    public void selectItem(Bundle item) {
        selectedItem.setValue(item);
    }

    public LiveData<Bundle> getSelectedItem() {
        return selectedItem;
    }

}
