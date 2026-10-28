package com.washeed.tanimart.data.viewModel;

import androidx.lifecycle.ViewModel;
import java.util.HashSet;
import java.util.Set;

public class CartSelectionViewModel extends ViewModel {

    private final Set<Integer> selectedCartIDs = new HashSet<>();
    public Set<Integer> getSelectedCartIDs() {
        return selectedCartIDs;
    }
}