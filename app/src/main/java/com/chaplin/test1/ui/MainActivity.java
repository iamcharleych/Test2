package com.chaplin.test1.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.chaplin.test1.R;
import com.chaplin.test1.ui.vehicles.fragments.listing.ListingFragment;
import com.chaplin.test1.ui.view.TintableButton;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private TextView mButtonTitleView;
    private TintableButton mButtonIconView;

    private ListingFragment mListingFragment;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
    }

    private void initLayout() {
        mButtonTitleView = findViewById(R.id.title);
        mButtonIconView = findViewById(R.id.icon);
        mListingFragment = (ListingFragment) getSupportFragmentManager().findFragmentById(R.id.listingFragment);

        findViewById(R.id.fragmentSwitcherButton).setOnClickListener(v -> {
            toggleDisplayMode();
        });
    }

    private void toggleDisplayMode() {
        boolean isHidden = mListingFragment.isHiddenDown();

        mButtonTitleView.setText(getString(!isHidden ? R.string.vehicles : R.string.map));
        mButtonIconView.setImageResource(!isHidden ? R.drawable.ic_vehicle : R.drawable.ic_map);

        if (isHidden) {
            mListingFragment.show();
        } else {
            mListingFragment.hide();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
