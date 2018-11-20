package com.chaplin.test1.ui.vehicles.fragments.listing.adapter.binder;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.chaplin.test1.core.model.VehicleModel;
import com.chaplin.test1.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test1.ui.vehicles.fragments.listing.adapter.VehicleViewBinderItem;

public class VehicleSubtitleViewBinder extends BaseViewBinder<VehicleViewBinderItem> {

    @NonNull
    private final TextView mSubtitleView;

    public VehicleSubtitleViewBinder(@NonNull TextView subtitleView) {
        mSubtitleView = subtitleView;
    }

    @Override
    public void bind(VehicleViewBinderItem item) {
        final VehicleModel vehicle = item.getEntity();

        mSubtitleView.setText(vehicle.getAddress());
    }
}
