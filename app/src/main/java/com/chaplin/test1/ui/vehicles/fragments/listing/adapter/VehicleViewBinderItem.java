package com.chaplin.test1.ui.vehicles.fragments.listing.adapter;

import androidx.annotation.NonNull;
import com.chaplin.test1.core.model.VehicleModel;
import com.chaplin.test1.ui.adapters.binders.BaseViewBinderItem;

public class VehicleViewBinderItem extends BaseViewBinderItem<VehicleModel> {

    @NonNull
    private final VehicleModel mEntity;

    public VehicleViewBinderItem(@NonNull VehicleModel entity) {
        mEntity = entity;
    }

    @Override
    public VehicleModel getEntity() {
        return mEntity;
    }
}
