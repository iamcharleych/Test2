package com.chaplin.test1.ui.vehicles.fragments.map;

import android.content.Context;
import android.graphics.Bitmap;
import com.chaplin.test1.core.model.VehicleModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

public class VehiclesClusterRenderer extends DefaultClusterRenderer<VehicleModel> {

    private boolean mIsClusteringEnabled;
    private final Bitmap mClusterBitmap;
    private final Bitmap mClusterItemBitmap;

    public VehiclesClusterRenderer(Context context, GoogleMap map, ClusterManager<VehicleModel> clusterManager,
                                   Bitmap clusterBitmap, Bitmap clusterItemBitmap) {
        super(context, map, clusterManager);
        mClusterBitmap = clusterBitmap;
        mClusterItemBitmap = clusterItemBitmap;

        setMinClusterSize(1);
    }

    public void setClusteringEnabled(boolean clusteringEnabled) {
        mIsClusteringEnabled = clusteringEnabled;
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<VehicleModel> cluster) {
        return mIsClusteringEnabled && super.shouldRenderAsCluster(cluster);
    }

    @Override
    protected void onBeforeClusterItemRendered(VehicleModel item, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(mClusterItemBitmap));
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<VehicleModel> cluster, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(mClusterBitmap));
    }
}
