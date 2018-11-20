package com.chaplin.test1.ui.vehicles.fragments.listing;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.chaplin.test1.R;
import com.chaplin.test1.core.model.VehicleModel;
import com.chaplin.test1.core.model.mapper.VehicleModelMapper;
import com.chaplin.test1.ui.vehicles.fragments.listing.adapter.OnSelectVehicleListener;
import com.chaplin.test1.ui.vehicles.fragments.listing.adapter.VehicleViewHolderCreator;
import com.chaplin.test1.ui.vehicles.fragments.listing.adapter.VehiclesAdapter;
import com.chaplin.test1.ui.vehicles.viewmodel.VehiclesViewModel;
import com.chaplin.test1.ui.vehicles.viewmodel.VehiclesViewState;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;
import java.util.List;

public class ListingFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        Observer<VehiclesViewState>, OnSelectVehicleListener, HasSupportFragmentInjector {

    private View mRootView;
    private SwipeRefreshLayout mSwipeRefreshView;

    private VehiclesAdapter mAdapter;
    @Nullable
    private VehiclesViewModel mViewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> mChildFragmentInjector;
    @Inject
    VehiclesViewModel.Factory mVehiclesViewModelFactory;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_vehicles, container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initViews(view);
        setupViewModel();
    }

    private void initViews(View rootView) {
        mSwipeRefreshView = rootView.findViewById(R.id.swipeRefreshView);
        mSwipeRefreshView.setColorSchemeResources(
                R.color.appBlueColor,
                R.color.appOrangeColor,
                R.color.appGreenColor,
                R.color.appRedColor
        );
        mSwipeRefreshView.setOnRefreshListener(this);

        mAdapter = new VehiclesAdapter(new VehicleViewHolderCreator(R.layout.view_vehicle_item));
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(mAdapter);
    }

    private void setupViewModel() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return; // early exit
        }

        mViewModel = ViewModelProviders.of(getActivity(), mVehiclesViewModelFactory).get(VehiclesViewModel.class);

        mViewModel.getVehiclesLiveData().observe(getViewLifecycleOwner(), this);

        mViewModel.getVehicles();
    }

    @Override
    public void onRefresh() {
        if (mViewModel == null) {
            return; // early exit
        }

        mViewModel.getVehicles();
    }

    @Override
    public void onSelectVehicle(VehicleModel vehicle) {
        if (mViewModel == null) {
            return; // early exit
        }

        mViewModel.selectVehicle(vehicle);
    }

    @Override
    public void onChanged(VehiclesViewState vehiclesViewState) {
        if (vehiclesViewState == null) {
            return; // early exit
        }

        if (vehiclesViewState.isIdle()) {
            renderIdling();
            return; // early exit
        }

        if (vehiclesViewState.isLoading()) {
            renderLoading();
            return; // early exit
        }

        if (vehiclesViewState.getError() != null) {
            renderError(vehiclesViewState.getError());
            return; // early exit, handling error state
        }

        renderVehicles(vehiclesViewState.getVehicles());
    }

    public void hide() {
        mRootView.animate()
                .translationY(mRootView.getHeight())
                .setDuration(200);
    }

    public void show() {
        mRootView.animate()
                .translationY(0)
                .setDuration(200);
    }

    public boolean isHiddenDown() {
        return mRootView.getTranslationY() > 0;
    }

    private void renderLoading() {
        showProgress();
    }

    private void renderVehicles(List<VehicleModel> vehicles) {
        hideProgress();

        mAdapter.updateItems(vehicles);
        mAdapter.notifyDataSetChanged();
    }

    private void renderError(Throwable error) {
        hideProgress();

        // TODO: handle error
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
    }

    private void renderIdling() {
        hideProgress();
    }

    private void showProgress() {
        if (!mSwipeRefreshView.isRefreshing()) {
            mSwipeRefreshView.setRefreshing(true);
        }
    }

    private void hideProgress() {
        if (mSwipeRefreshView.isRefreshing()) {
            mSwipeRefreshView.setRefreshing(false);
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mChildFragmentInjector;
    }
}
