package de.prttstft.materialmensa.ui.activities.main;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.prttstft.materialmensa.MyApplication;
import de.prttstft.materialmensa.R;
import de.prttstft.materialmensa.extras.UserSettings;
import de.prttstft.materialmensa.extras.Utilities;
import de.prttstft.materialmensa.ui.activities.about.AboutActivity;
import de.prttstft.materialmensa.ui.activities.main.presenter.MainPresenter;
import de.prttstft.materialmensa.ui.activities.main.presenter.MainPresenterImplementation;
import de.prttstft.materialmensa.ui.activities.main.view.MainView;
import de.prttstft.materialmensa.ui.activities.settings.SettingsActivity;
import de.prttstft.materialmensa.ui.fragments.main.MainFragment;
import de.prttstft.materialmensa.ui.fragments.main.MainFragmentPagerAdapter;

import static de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation.LIFESTYLE_LEVEL_FIVE_VEGAN;
import static de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation.LIFESTYLE_NOT_SET;
import static de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation.LIFESTYLE_VEGAN;
import static de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation.LIFESTYLE_VEGETARIAN;
import static de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation.ROLE_GUEST;
import static de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation.ROLE_STAFF;
import static de.prttstft.materialmensa.ui.fragments.main.interactor.MainFragmentInteractorImplementation.ROLE_STUDENT;


public class MainActivity extends AppCompatActivity implements MainView {
    private static final Drawable DRAWER_HEADER_AVATAR_GUEST = ContextCompat.getDrawable(MyApplication.getAppContext(), R.drawable.ic_guest_black);
    private static final Drawable DRAWER_HEADER_AVATAR_STAFF = ContextCompat.getDrawable(MyApplication.getAppContext(), R.drawable.ic_staff_black);
    private static final Drawable DRAWER_HEADER_AVATAR_STUDENT = ContextCompat.getDrawable(MyApplication.getAppContext(), R.drawable.ic_school_black);
    @SuppressWarnings("WeakerAccess") @Bind(R.id.activity_main_drawer_layout) DrawerLayout drawerLayout;
    @SuppressWarnings("WeakerAccess") @Bind(R.id.activity_main_navigation_view) NavigationView navigationView;
    @SuppressWarnings("WeakerAccess") @Bind(R.id.activity_main_tab_layout) TabLayout tabLayout;
    @SuppressWarnings("WeakerAccess") @Bind(R.id.activity_main_toolbar) Toolbar toolbar;
    @SuppressWarnings("WeakerAccess") @Bind(R.id.activity_main_view_pager) ViewPager viewPager;
    private MainFragmentPagerAdapter adapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenterImplementation(this);

        setUpToolbar();
        setUpDrawerLayout();
        setUpDrawerHeader();
        setUpTabs();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.getRestaurantStatus();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.restaurant_mensa_academica_paderborn));
        }
    }

    private void setUpDrawerLayout() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (MainFragment.actionMode != null) {
                    MainFragment.actionMode.finish();
                }
                //tabLayout.getTabAt(position).setIcon(Utilities.getRestaurantIcon(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menu_main_drawer_0:
                        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 0));
                        adapter.notifyDataSetChanged();
                        toolbar.setTitle(getString(R.string.restaurant_mensa_academica_paderborn));
                        menuItem.setChecked(true);
                        setUpTabs();
                        break;
                    case R.id.menu_main_drawer_1:
                        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 1));
                        adapter.notifyDataSetChanged();
                        toolbar.setTitle(getString(R.string.restaurant_mensa_forum_paderborn));
                        menuItem.setChecked(true);
                        setUpTabs();
                        break;
                    case R.id.menu_main_drawer_2:
                        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 2));
                        adapter.notifyDataSetChanged();
                        toolbar.setTitle(getString(R.string.restaurant_cafete));
                        menuItem.setChecked(true);
                        setUpTabs();
                        break;
                    case R.id.menu_main_drawer_3:
                        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 3));
                        adapter.notifyDataSetChanged();
                        toolbar.setTitle(getString(R.string.restaurant_mensula));
                        menuItem.setChecked(true);
                        setUpTabs();
                        break;
                    case R.id.menu_main_drawer_4:
                        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 4));
                        adapter.notifyDataSetChanged();
                        toolbar.setTitle(getString(R.string.restaurant_one_way_snack));
                        menuItem.setChecked(true);
                        setUpTabs();
                        break;
                    case R.id.menu_main_drawer_5:
                        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 5));
                        adapter.notifyDataSetChanged();
                        toolbar.setTitle(getString(R.string.restaurant_grill_cafe));
                        menuItem.setChecked(true);
                        setUpTabs();
                        break;
                    case R.id.menu_main_drawer_6:
                        viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager(), 6));
                        adapter.notifyDataSetChanged();
                        toolbar.setTitle(getString(R.string.restaurant_grill_cafe));
                        menuItem.setChecked(true);
                        setUpTabs();
                        break;
                    case R.id.menu_main_drawer_settings:
                        Intent startSettingsActivityIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                        startActivity(startSettingsActivityIntent);
                        break;
                    case R.id.menu_main_drawer_about:
                        Intent startAboutActivityIntent = new Intent(getApplicationContext(), AboutActivity.class);
                        startActivity(startAboutActivityIntent);
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    private void setUpDrawerHeader() {
        String role = UserSettings.getRole();
        String lifestyle = UserSettings.getLifestyle();

        RelativeLayout header = (RelativeLayout) navigationView.getHeaderView(0);
        FrameLayout frameLayout = (FrameLayout) header.getChildAt(0);
        ImageView avatar = (ImageView) frameLayout.getChildAt(1);
        TextView roleTextView = (TextView) header.getChildAt(1);
        TextView lifestyleTextView = (TextView) header.getChildAt(2);

        switch (role) {
            case ROLE_GUEST:
                roleTextView.setText(getString(R.string.activity_settings_preferences_role_guest));
                avatar.setImageDrawable(DRAWER_HEADER_AVATAR_GUEST);
                avatar.setScaleX(1.5f);
                avatar.setScaleY(1.5f);
                break;
            case ROLE_STAFF:
                roleTextView.setText(getString(R.string.activity_settings_preferences_role_staff));
                avatar.setImageDrawable(DRAWER_HEADER_AVATAR_STAFF);
                avatar.setScaleX(1.5f);
                avatar.setScaleY(1.5f);
                break;
            case ROLE_STUDENT:
                roleTextView.setText(getString(R.string.activity_settings_preferences_role_student));
                avatar.setImageDrawable(DRAWER_HEADER_AVATAR_STUDENT);
                avatar.setScaleX(1f);
                avatar.setScaleY(1f);
                break;
        }

        switch (lifestyle) {
            case LIFESTYLE_VEGAN:
                lifestyleTextView.setText(getString(R.string.activity_settings_preferences_lifestyle_vegan));
                break;
            case LIFESTYLE_VEGETARIAN:
                lifestyleTextView.setText(getString(R.string.activity_settings_preferences_lifestyle_vegetarian));
                break;
            case LIFESTYLE_NOT_SET:
                lifestyleTextView.setText(getString(R.string.activity_settings_preferences_lifestyle_meat));
                break;
            case LIFESTYLE_LEVEL_FIVE_VEGAN:
                lifestyleTextView.setText(R.string.activity_settings_preferences_lifestyle_level_five_vegan_drawer);
                break;
        }
    }

    private void setUpTabs() {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            tabLayout.getTabAt(i).setIcon(Utilities.getRestaurantIcon(i));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void restaurantClosed(int restaurant, String openingTime) {
        ImageView circle = (ImageView) ((LinearLayoutCompat) navigationView.getMenu().getItem(restaurant).getActionView()).getChildAt(0);
        TextView statusTextView = (TextView) ((LinearLayoutCompat) navigationView.getMenu().getItem(restaurant).getActionView()).getChildAt(1);

        if (openingTime != null) {
            statusTextView.setText(openingTime);
            statusTextView.setTextColor(ContextCompat.getColor(this, R.color.colorNegative));
            circle.setVisibility(View.GONE);
            statusTextView.setVisibility(View.VISIBLE);
        } else {
            Drawable circleColor = circle.getDrawable();

            if (circleColor instanceof ShapeDrawable) {
                ((ShapeDrawable) circleColor).getPaint().setColor(ContextCompat.getColor(this, R.color.colorNegative));
                circle.setVisibility(View.VISIBLE);
                statusTextView.setVisibility(View.GONE);
            } else if (circleColor instanceof GradientDrawable) {
                ((GradientDrawable) circleColor).setColor(ContextCompat.getColor(this, R.color.colorNegative));
                circle.setVisibility(View.VISIBLE);
                statusTextView.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void restaurantOpen(int restaurant, String closingTime) {
        ImageView circle = (ImageView) ((LinearLayoutCompat) navigationView.getMenu().getItem(restaurant).getActionView()).getChildAt(0);
        TextView statusTextView = (TextView) ((LinearLayoutCompat) navigationView.getMenu().getItem(restaurant).getActionView()).getChildAt(1);

        if (closingTime != null) {
            statusTextView.setText(closingTime);
            statusTextView.setTextColor(ContextCompat.getColor(this, R.color.colorPositive));

            circle.setVisibility(View.GONE);
            statusTextView.setVisibility(View.VISIBLE);
        } else {
            Drawable circleColor = circle.getDrawable();

            if (circleColor instanceof ShapeDrawable) {
                ((ShapeDrawable) circleColor).getPaint().setColor(ContextCompat.getColor(this, R.color.colorPositive));

                circle.setVisibility(View.VISIBLE);
                statusTextView.setVisibility(View.GONE);
            } else if (circleColor instanceof GradientDrawable) {
                ((GradientDrawable) circleColor).setColor(ContextCompat.getColor(this, R.color.colorPositive));

                circle.setVisibility(View.VISIBLE);
                statusTextView.setVisibility(View.GONE);
            }
        }
    }
}