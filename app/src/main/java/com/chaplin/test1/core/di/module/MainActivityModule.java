package com.chaplin.test1.core.di.module;

import com.chaplin.test1.core.di.scope.FragmentScope;
import com.chaplin.test1.ui.vehicles.fragments.listing.ListingFragment;
import com.chaplin.test1.ui.vehicles.fragments.map.MapFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract ListingFragment contributesListingFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract MapFragment contributesMapFragment();
}
