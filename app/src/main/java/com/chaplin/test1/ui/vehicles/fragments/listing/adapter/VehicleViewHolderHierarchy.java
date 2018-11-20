package com.chaplin.test1.ui.vehicles.fragments.listing.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.chaplin.test1.R;

/* package */ class VehicleViewHolderHierarchy {

    @NonNull
    final View rootView;
    @NonNull
    final TextView titleView;
    @NonNull
    final TextView subtitleView;

    VehicleViewHolderHierarchy(@NonNull LayoutInflater inflater,
                                      @LayoutRes int layoutRes,
                                      @NonNull ViewGroup container) {
        rootView = inflater.inflate(layoutRes, container, false);

        titleView = rootView.findViewById(R.id.titleView);
        subtitleView = rootView.findViewById(R.id.subtitleView);
    }
}
