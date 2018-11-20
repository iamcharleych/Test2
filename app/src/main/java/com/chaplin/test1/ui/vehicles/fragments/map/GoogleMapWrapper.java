package com.chaplin.test1.ui.vehicles.fragments.map;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import com.chaplin.test1.R;
import com.chaplin.test1.core.model.VehicleModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.ClusterRenderer;

import java.util.HashSet;
import java.util.List;

class GoogleMapWrapper implements ClusterManager.OnClusterClickListener<VehicleModel>, GoogleMap.OnMarkerClickListener {

    private static final int SELECTED_CAR_ZOOM_LEVEL = 17;
    private static final LatLng DEFAULT_COORDS = new LatLng(53.499307,10.008840);
    private static final int DEFAULT_ZOOM_LEVEL = 9;

    @NonNull
    private GoogleMap mGoogleMap;
    @NonNull
    private final ClusterManager<VehicleModel> mClusterManager;
    private final int mMapPadding;

    GoogleMapWrapper(@NonNull GoogleMap googleMap, Resources resources, @NonNull ClusterManager<VehicleModel> clusterManager) {
        mGoogleMap = googleMap;
        mMapPadding = resources.getDimensionPixelSize(R.dimen.map_padding);
        mClusterManager = clusterManager;

        mClusterManager.setOnClusterClickListener(this);

        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(false);
        uiSettings.setMapToolbarEnabled(false);
        uiSettings.setZoomControlsEnabled(false);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(this);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_COORDS, DEFAULT_ZOOM_LEVEL));
    }

    void enableClustering(boolean enable) {
        ClusterRenderer<VehicleModel> renderer = mClusterManager.getRenderer();

        if (renderer instanceof VehiclesClusterRenderer) {
            ((VehiclesClusterRenderer) renderer).setClusteringEnabled(enable);
        }
    }

    void showVehicles(List<VehicleModel> vehicles) {
        mGoogleMap.clear();

        if (vehicles == null || vehicles.size() == 0) {
            return; // early exit
        }

        mClusterManager.clearItems();
        mClusterManager.addItems(vehicles);

        zoomToVehicles(vehicles);
    }

    void showSelectedVehicle(VehicleModel vehicle) {
        if (vehicle == null || vehicle.getLocation() == null) {
            return; // early exit
        }

        zoomToLocation(vehicle.getLocation());
    }

    private void zoomToVehicles(List<VehicleModel> vehicles) {
        HashSet<LatLng> locationSet = new HashSet<>();
        for (VehicleModel vehicle : vehicles) {
            if (vehicle.getLocation() != null) {
                locationSet.add(vehicle.getLocation());
            }
        }

        final int count = locationSet.size();

        if (count < 1) {
            return; // early exit
        }

        if (count == 1) {
            zoomToLocation(locationSet.iterator().next());
        } else {
            LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();
            for (LatLng loc : locationSet) {
                boundsBuilder.include(loc);
            }

            zoomToBounds(boundsBuilder.build());
        }
    }

    private void zoomToLocation(LatLng location) {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, SELECTED_CAR_ZOOM_LEVEL));
    }

    private void zoomToBounds(LatLngBounds latLngBounds) {
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, mMapPadding));
    }

    @Override
    public boolean onClusterClick(Cluster<VehicleModel> cluster) {
        LatLngBounds.Builder builder = LatLngBounds.builder();

        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }

        zoomToBounds(builder.build());

        return true;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
