package com.chaplin.test1.ui.vehicles.fragments.listing.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.chaplin.test1.ui.adapters.binders.CompositeViewBinder;
import com.chaplin.test1.ui.vehicles.fragments.listing.adapter.binder.VehicleSubtitleViewBinder;
import com.chaplin.test1.ui.vehicles.fragments.listing.adapter.binder.VehicleTitleViewBinder;

public class VehicleViewHolderCreator {

    @LayoutRes
    private final int mLayoutRes;

    public VehicleViewHolderCreator(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
    }

    VehicleViewHolder createViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,
                                       @Nullable VehicleViewHolder.OnSelectItemListener listener) {
        VehicleViewHolderHierarchy hierarchy = new VehicleViewHolderHierarchy(inflater, mLayoutRes, container);

        CompositeViewBinder<VehicleViewBinderItem> binder = new CompositeViewBinder<>(
                new VehicleTitleViewBinder(hierarchy.titleView),
                new VehicleSubtitleViewBinder(hierarchy.subtitleView)
        );

        return new VehicleViewHolder(hierarchy.rootView, binder, listener);
    }
}
