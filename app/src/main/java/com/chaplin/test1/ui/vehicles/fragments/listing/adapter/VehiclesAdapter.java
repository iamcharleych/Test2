package com.chaplin.test1.ui.vehicles.fragments.listing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.chaplin.test1.core.model.VehicleModel;

import java.util.List;

public class VehiclesAdapter extends RecyclerView.Adapter<VehicleViewHolder> implements VehicleViewHolder.OnSelectItemListener {

    @NonNull
    private final VehicleViewHolderCreator mViewHolderCreator;
    @Nullable
    private OnSelectVehicleListener mOnSelectVehicleListener;

    private LayoutInflater mLayoutInflater;

    private List<VehicleModel> mItems;

    public VehiclesAdapter(@NonNull VehicleViewHolderCreator viewHolderCreator) {
        mViewHolderCreator = viewHolderCreator;
    }

    public void setOnSelectVehicleListener(@Nullable OnSelectVehicleListener onSelectVehicleListener) {
        mOnSelectVehicleListener = onSelectVehicleListener;
    }

    @NonNull
    @Override
    public VehicleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mViewHolderCreator.createViewHolder(getLayoutInflater(parent.getContext()), parent, this);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleViewHolder holder, int position) {
        holder.bind(getBinderItem(position));
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    @Override
    public void onSelectItem(int position) {
        if (mOnSelectVehicleListener != null) {
            mOnSelectVehicleListener.onSelectVehicle(mItems.get(position));
        }
    }

    public void updateItems(List<VehicleModel> items) {
        mItems = items;
    }

    private VehicleViewBinderItem getBinderItem(int position) {
        return new VehicleViewBinderItem(mItems.get(position));
    }

    private LayoutInflater getLayoutInflater(Context context) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(context);
        }

        return mLayoutInflater;
    }

}
