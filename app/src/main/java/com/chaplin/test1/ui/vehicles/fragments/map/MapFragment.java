package com.chaplin.test1.ui.vehicles.fragments.map;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.*;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.chaplin.test1.R;
import com.chaplin.test1.core.model.VehicleModel;
import com.chaplin.test1.ui.vehicles.viewmodel.VehiclesViewModel;
import com.chaplin.test1.ui.vehicles.viewmodel.VehiclesViewState;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.maps.android.clustering.ClusterManager;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback, HasSupportFragmentInjector {

    private MapView mViewMap;
    private GoogleMapWrapper mGoogleMap;

    @Inject
    DispatchingAndroidInjector<Fragment> mChildFragmentInjector;
    @Inject
    VehiclesViewModel.Factory mVehiclesViewModelFactory;

    @Nullable
    VehiclesViewModel mViewModel;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewMap.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mViewMap.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mViewMap.onDestroy();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        initMapView((ViewGroup) view, savedInstanceState);

        mViewMap.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final Context context = getContext();
        final Resources resources = getResources();

        if (context == null) {
            return; // early exit
        }

        ClusterManager<VehicleModel> clusterManager = new ClusterManager<>(context, googleMap);
        VehiclesClusterRenderer clusterRenderer = new VehiclesClusterRenderer(context, googleMap, clusterManager,
                MapHelper.getBitmap(resources, R.drawable.ic_car_circle_cluster),
                MapHelper.getBitmap(resources, R.drawable.ic_car_circle_marker));
        clusterManager.setRenderer(clusterRenderer);
        mGoogleMap = new GoogleMapWrapper(googleMap, resources, clusterManager);
        mGoogleMap.enableClustering(true);

        setupViewModel();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_clustering) {
            if (mGoogleMap == null) {
                return true;
            }

            final boolean isChecked = item.isChecked();
            item.setChecked(!isChecked);
            mGoogleMap.enableClustering(!isChecked);

            VehiclesViewState state;
            if (mViewModel != null && (state = mViewModel.getVehiclesLiveData().getValue()) != null) {
                renderVehicles(state.getVehicles());
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initMapView(ViewGroup container, Bundle savedInstanceState) {
        final Context context = container.getContext();

        MapsInitializer.initialize(context);

        mViewMap = new MapView(context);
        mViewMap.onCreate(savedInstanceState);

        container.addView(mViewMap, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

    }

    private void setupViewModel() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return; // early exit
        }

        mViewModel = ViewModelProviders.of(getActivity(), mVehiclesViewModelFactory)
                .get(VehiclesViewModel.class);

        mViewModel.getVehiclesLiveData().observe(getViewLifecycleOwner(), viewState -> {
            if (viewState == null
                    || viewState.isIdle()
                    || viewState.isLoading()
                    || viewState.getError() != null) {
                return; // early exit
            }

            renderVehicles(viewState.getVehicles());
        });
        mViewModel.getSelectedVehicleLiveData().observe(getViewLifecycleOwner(), this::showSelectedVehicle);
    }

    private void renderVehicles(List<VehicleModel> vehicles) {
        if (mGoogleMap == null) {
            return; // early exit
        }

        mGoogleMap.showVehicles(vehicles);
    }

    private void showSelectedVehicle(VehicleModel vehicle) {
        if (mGoogleMap == null || vehicle == null) {
            return; // early exit
        }

        mGoogleMap.showSelectedVehicle(vehicle);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mChildFragmentInjector;
    }
}
