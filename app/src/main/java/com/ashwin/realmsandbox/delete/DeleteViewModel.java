package com.ashwin.realmsandbox.delete;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.ashwin.realmsandbox.Constant;
import com.ashwin.realmsandbox.RecordRepository;

public class DeleteViewModel extends ViewModel {
    private RecordRepository recordRepository;

    public DeleteViewModel(SavedStateHandle savedStateHandle, RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void deleteAll() {
        Log.d(Constant.TAG, "DeleteViewModel.deleteAll");
        recordRepository.deleteAllRecords();
    }
}
