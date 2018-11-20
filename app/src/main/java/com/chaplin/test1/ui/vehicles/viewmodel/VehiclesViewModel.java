package com.chaplin.test1.ui.vehicles.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.chaplin.test1.core.model.VehicleModel;
import com.chaplin.test1.core.model.mapper.VehicleModelMapper;
import com.chaplin.test1.domain.model.Vehicle;
import com.chaplin.test1.domain.usecase.GetVehiclesUseCase;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.List;

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
        final Disposable subscription = Flowable.fromCallable(() -> {
            List<Vehicle> vehicles = mGetVehiclesUseCase.execute(null);
            return mMapper.map(vehicles);
        })
        .doOnSubscribe(s -> mVehiclesLiveData.postValue(VehiclesViewState.loading()))
        .doOnError(throwable -> {
            mVehiclesLiveData.postValue(VehiclesViewState.error(throwable));
            mVehiclesLiveData.postValue(VehiclesViewState.idle());
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                vehiclesList -> {
                    disposeSubscriptions();
                    mVehiclesLiveData.setValue(VehiclesViewState.success(vehiclesList));
                },
                throwable -> disposeSubscriptions()
        );

        mCompositeDisposable.add(subscription);
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
}
