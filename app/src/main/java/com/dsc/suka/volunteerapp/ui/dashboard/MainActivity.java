package com.dsc.suka.volunteerapp.ui.dashboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dsc.suka.volunteerapp.R;
import com.dsc.suka.volunteerapp.model.UserData;
import com.dsc.suka.volunteerapp.ui.authentication.login.LoginActivity;
import com.dsc.suka.volunteerapp.ui.dashboard.home.HomeFragment;
import com.dsc.suka.volunteerapp.ui.dashboard.mycontribution.MyContributionFragment;
import com.dsc.suka.volunteerapp.ui.dashboard.tunanetra.TunaNetraFragment;
import com.dsc.suka.volunteerapp.ui.dashboard.tunarungu.TunaRunguFragment;
import com.dsc.suka.volunteerapp.utils.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private UserData userData;
    private SessionManager mSessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mSessionManager = new SessionManager(this);
        userData = mSessionManager.getUserData();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        TextView tvHeaderUsername = (TextView)header.findViewById(R.id.nav_tv_name);
        TextView tvHeaderEmail = (TextView)header.findViewById(R.id.nav_tv_email);
        CircleImageView imgAvatar = (CircleImageView)header.findViewById(R.id.nav_img_ava);

        String displayName = userData.getUserName();
        String email = userData.getUserEmail();
        String imgUrl = userData.getUserPhotoUrl();

        tvHeaderUsername.setText(displayName);
        tvHeaderEmail.setText(email);

        RequestOptions requestOptions = new RequestOptions().centerCrop();
        Glide.with(this)
                .load(imgUrl)
                .apply(requestOptions)
                .into(imgAvatar);

        Button btnLogout = navigationView.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(MainActivity.this);
            }
        });


        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_content, new HomeFragment());
            fragmentTransaction.commit();
        }
    }

    private void showDialog(Activity activity) {
        final Dialog dialog = new Dialog(activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_logout);

        RelativeLayout dimBackground = (RelativeLayout) dialog.findViewById(R.id.dim_background_logout);
        dimBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        Button btnYes = (Button) dialog.findViewById(R.id.btn_logout_dialog_yes);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                logout();
            }
        });

        Button btnNo = (Button) dialog.findViewById(R.id.btn_logout_dialog_no);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void logout() {
        mSessionManager.deleteSession();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_tuna_netra:
                fragment = new TunaNetraFragment();
                break;
            case R.id.nav_tuna_rungu:
                fragment = new TunaRunguFragment();
                break;
            case R.id.nav_my_contributions:
                fragment = new MyContributionFragment();
                break;
        }

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_content, fragment)
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


