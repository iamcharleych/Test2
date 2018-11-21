package com.chaplin.test2.ui.vehicles.viewmodel;

import com.chaplin.test1.domain.model.Vehicle;
import com.chaplin.test1.domain.usecase.GetVehiclesUseCase;
import com.chaplin.test2.core.model.VehicleModel;
import com.chaplin.test2.core.model.mapper.VehicleModelMapper;

import org.reactivestreams.Subscription;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.disposables.CompositeDisposable;

public class VehiclesViewModel extends ViewModel {

    @NonNull
    private final GetVehiclesUseCase mGetVehiclesUseCase;
    @NonNull
    private final VehicleModelMapper mMapper;

    @NonNull
    private final MutableLiveData<VehiclesViewState> mVehiclesLiveData = new MutableLiveData<>();
    @NonNull
    private final MutableLiveData<VehicleModel> mSelectedVehicleLiveData = new MutableLiveData<>();
    @NonNull
    private final CompositeDisposable mCompositeDisposable;

    public VehiclesViewModel(@NonNull GetVehiclesUseCase getVehiclesUseCase, @NonNull VehicleModelMapper mapper) {
        mGetVehiclesUseCase = getVehiclesUseCase;
        mMapper = mapper;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        disposeSubscriptions();
    }

    private void disposeSubscriptions() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public LiveData<VehiclesViewState> getVehiclesLiveData() {
        return mVehiclesLiveData;
    }

    public LiveData<VehicleModel> getSelectedVehicleLiveData() {
        return mSelectedVehicleLiveData;
    }

    public void getVehicles() {
        mGetVehiclesUseCase.execute(null, new VehiclesSubscriber());
    }

    public void selectVehicle(VehicleModel vehicle) {
        mSelectedVehicleLiveData.setValue(vehicle);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        @NonNull
        private final GetVehiclesUseCase mGetVehiclesUseCase;
        @NonNull
        private final VehicleModelMapper mMapper;

        public Factory(@NonNull GetVehiclesUseCase getVehiclesUseCase, @NonNull VehicleModelMapper mapper) {
            mGetVehiclesUseCase = getVehiclesUseCase;
            mMapper = mapper;
        }

        @SuppressWarnings("unchecked")
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new VehiclesViewModel(mGetVehiclesUseCase, mMapper);
        }
    }

    private class VehiclesSubscriber implements org.reactivestreams.Subscriber<List<Vehicle>> {

        @Override
        public void onSubscribe(Subscription s) {
            mVehiclesLiveData.postValue(VehiclesViewState.loading());
        }

        @Override
        public void onNext(List<Vehicle> vehicles) {
            mVehiclesLiveData.setValue(VehiclesViewState.success(mMapper.map(vehicles)));
        }

        @Override
        public void onError(Throwable t) {
            mVehiclesLiveData.postValue(VehiclesViewState.error(t));
            mVehiclesLiveData.postValue(VehiclesViewState.idle());
        }

        @Override
        public void onComplete() {
            // no-op
        }
    }
}
