package com.chaplin.test1.ui.vehicles.fragments.listing.adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.chaplin.test1.ui.adapters.binders.BaseViewBinder;
import com.chaplin.test1.ui.adapters.viewholders.BaseViewHolder;

/* package */ class VehicleViewHolder
        extends BaseViewHolder<VehicleViewBinderItem, BaseViewBinder<VehicleViewBinderItem>> {

    interface OnSelectItemListener {
        void onSelectItem(int position);
    }

    VehicleViewHolder(@NonNull View itemView, @NonNull BaseViewBinder<VehicleViewBinderItem> viewBinder,
                      @Nullable OnSelectItemListener listener) {
        super(itemView, viewBinder);

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSelectItem(getAdapterPosition());
            }
        });
    }
}
