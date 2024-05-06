package com.ar.enbaldeapp.ui.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ar.enbaldeapp.models.Selection;

import java.util.ArrayList;
import java.util.List;

public class CartViewModel extends ViewModel {

    private final MutableLiveData<List<Selection>> selections;

    public CartViewModel() {
        selections = new MutableLiveData<>();
        selections.setValue(new ArrayList<>());
    }

    public LiveData<List<Selection>> getSelections() {
        return selections;
    }
}