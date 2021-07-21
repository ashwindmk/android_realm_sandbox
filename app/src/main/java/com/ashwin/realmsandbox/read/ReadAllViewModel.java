package com.ashwin.realmsandbox.read;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.ashwin.realmsandbox.Constant;
import com.ashwin.realmsandbox.RecordRepository;
import com.ashwin.realmsandbox.model.Record;
import com.ashwin.realmsandbox.model.RecordModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ReadAllViewModel extends ViewModel {
    private MutableLiveData<List<Record>> recordListState;

    private RecordRepository recordRepository;

    public ReadAllViewModel(SavedStateHandle savedStateHandle, RecordRepository recordRepository) {
        Log.d(Constant.TAG, "ReadAllViewModel constructor");
//        recordListState = new MutableLiveData<>();
        recordListState = savedStateHandle.getLiveData(ReadAllModule.ALL_RECORDS);
        this.recordRepository = recordRepository;
    }

    public LiveData<List<Record>> getRecordListState() {
        return recordListState;
    }

    public void fetchAll() {
        RealmResults<RecordModel> results = recordRepository.getAll();
        Log.d(Constant.TAG, "ReadAllViewModel: fetchAll: result.size: " + results.size());
        handleRecords(results);
        results.addChangeListener(new RealmChangeListener<RealmResults<RecordModel>>() {
            @Override
            public void onChange(RealmResults<RecordModel> records) {
                Log.d(Constant.TAG, "ReadAllViewModel.onChange: " + records);
                handleRecords(records);
            }
        });
    }

    private void handleRecords(RealmResults<RecordModel> records) {
        List<Record> list = new ArrayList<Record>();
        for (RecordModel model : records) {
            list.add(model.toObject());
        }
        recordListState.postValue(list);
    }

    public void updateOne() {
        RecordModel record = new RecordModel(1L, "Alice");
        int r = new Random().nextInt(9);
        record.email = "alice" + r + "@email.com";
        recordRepository.updateRecord(record);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(Constant.TAG, "ReadAllViewModel: onCleared");
    }
}
