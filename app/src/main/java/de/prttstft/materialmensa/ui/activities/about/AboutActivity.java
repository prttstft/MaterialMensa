package de.prttstft.materialmensa.ui.activities.about;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.prttstft.materialmensa.BuildConfig;
import de.prttstft.materialmensa.R;
import de.prttstft.materialmensa.items.LibraryItem;
import de.prttstft.materialmensa.ui.activities.about.presenter.AboutPresenter;
import de.prttstft.materialmensa.ui.activities.about.presenter.AboutPresenterImplementation;
import de.prttstft.materialmensa.ui.activities.about.view.AboutView;

public class AboutActivity extends AppCompatActivity implements AboutView {
    @SuppressWarnings("SpellCheckingInspection") private static final String CHROME_PACKAGE = "com.android.chrome";
    @SuppressWarnings("SpellCheckingInspection") private static final Uri EMAIL = Uri.parse("mailto:prttstft@gmail.com");
    @SuppressWarnings("SpellCheckingInspection") private static final Uri GIT_HUB_REPO_URL = Uri.parse("https://github.com/prttstft/MaterialMensa");
    @SuppressWarnings("SpellCheckingInspection") private static final Uri PLAY_STORE_URL = Uri.parse("https://github.com/prttstft/MaterialMensa");
    @SuppressWarnings("SpellCheckingInspection") private static final Uri WISH_LIST_URL = Uri.parse("https://www.amazon.de/registry/wishlist/2RQNQ7DEKD9WF");
    @Bind(R.id.activity_about_button_git_hub) AppCompatButton buttonGitHub;
    @Bind(R.id.activity_about_button_libraries) AppCompatButton buttonLibraries;
    @Bind(R.id.activity_about_button_rate) AppCompatButton buttonRate;
    @Bind(R.id.activity_about_button_wish_list) AppCompatButton buttonWishList;
    @Bind(R.id.activity_about_credits) TextView credits;
    @Bind(R.id.activity_about_toolbar) Toolbar toolbar;
    @Bind(R.id.activity_about_version) TextView version;
    AboutPresenter presenter;
    FastItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        version.setText(BuildConfig.VERSION_NAME);

        presenter = new AboutPresenterImplementation(this);

        setUpOnClickListeners();
    }

    private void setUpOnClickListeners() {
        buttonGitHub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomTab(GIT_HUB_REPO_URL);
            }
        });

        buttonRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomTab(PLAY_STORE_URL);
            }
        });

        buttonWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCustomTab(WISH_LIST_URL);
            }
        });

        buttonLibraries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLibrariesActivityIntent = new LibsBuilder()
                        .withActivityTheme(R.style.AboutLibraries)
                        .withActivityTitle(getString(R.string.activity_about_libraries_title))
                        .withFields(R.string.class.getFields())
                        .withLicenseDialog(true)
                        .withLicenseShown(true)
                        .intent(AboutActivity.this);
                startActivity(startLibrariesActivityIntent);
            }
        });

        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendEmailIntent = new Intent(Intent.ACTION_SENDTO, EMAIL);
                sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.activity_about_email_subject));
                startActivity(sendEmailIntent);
            }
        });
    }

    @Override
    public void addLibrary(LibraryItem libraryItem) {
        //noinspection unchecked
        adapter.add(libraryItem);
    }

    private void startCustomTab(Uri url) {
        CustomTabsIntent.Builder startCustomTabIntent = new CustomTabsIntent.Builder();
        startCustomTabIntent.setToolbarColor(ContextCompat.getColor(AboutActivity.this, R.color.color_primary));
        startCustomTabIntent.setShowTitle(true);
        startCustomTabIntent.setSecondaryToolbarColor(ContextCompat.getColor(AboutActivity.this, R.color.color_accent));

        if (chromeInstalled()) {
            startCustomTabIntent.build().intent.setPackage(CHROME_PACKAGE);
        }

        startCustomTabIntent.build().launchUrl(AboutActivity.this, url);
    }

    private boolean chromeInstalled() {
        try {
            getPackageManager().getPackageInfo(CHROME_PACKAGE, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}