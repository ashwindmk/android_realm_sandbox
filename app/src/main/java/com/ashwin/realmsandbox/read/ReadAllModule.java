package com.ashwin.realmsandbox.read;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;

import com.ashwin.realmsandbox.RecordRepository;

public class ReadAllModule extends AbstractSavedStateViewModelFactory {
    public static final String ALL_RECORDS = "all_records";

    private final RecordRepository recordRepository;

    /**
     * Constructs this factory.
     *
     * @param owner       {@link SavedStateRegistryOwner} that will provide restored state for created
     *                    {@link ViewModel ViewModels}
     * @param defaultArgs values from this {@code Bundle} will be used as defaults by
     *                    {@link SavedStateHandle} passed in {@link ViewModel ViewModels}
     *                    if there is no previously saved state
     */
    public ReadAllModule(@NonNull SavedStateRegistryOwner owner, @Nullable Bundle defaultArgs, RecordRepository recordRepository) {
        super(owner, defaultArgs);
        this.recordRepository = recordRepository;
    }

    @NonNull
    @Override
    protected <T extends ViewModel> T create(@NonNull String key, @NonNull Class<T> modelClass, @NonNull SavedStateHandle handle) {
        if (modelClass.isAssignableFrom(ReadAllViewModel.class)) {
            return (T) new ReadAllViewModel(handle, recordRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
