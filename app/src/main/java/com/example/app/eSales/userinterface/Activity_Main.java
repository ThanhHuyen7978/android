package com.example.app.eSales.userinterface;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.app.eSales.R;
//import com.example.app.eSales.data.remote.ApiUtils;
//import com.example.app.eSales.data.remote.SOService;

public class Activity_Main extends AppCompatActivity {

    private ActivityMain_ViewHolder holder;
//private SOService mService;//
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_main);
        //mService = ApiUtils.getSOService();//
        holder = new ActivityMain_ViewHolder();

        set_Toolbar();
        add_DefaultFragment();

    }

    @Override
    public void onBackPressed() {

        if (holder.mFragmentManager.getBackStackEntryCount() == 0){
            super.onBackPressed();
        }

    }

    //    Dùng toolbar
    public void set_Toolbar(){
        setSupportActionBar(holder.mToolbar);
        holder.mActionBar = getSupportActionBar();
    }

    //    Phương thức add fragment mặc định
    public void add_DefaultFragment(){
        Fragment_LoginScreen fragment_loginScreen = new Fragment_LoginScreen();
        FragmentTransaction transaction = holder.mFragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(R.id.activity_contain_main, fragment_loginScreen);
        transaction.commit();
    }

//    Class con lưu giữ view
    public class ActivityMain_ViewHolder{
        private Toolbar mToolbar;
        private ActionBar mActionBar;
        private FragmentManager mFragmentManager;
        private Fragment mFragment;

        public ActivityMain_ViewHolder() {

            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            mFragmentManager = getSupportFragmentManager();
            mFragment = new Fragment();
        }
    }
}
