package com.example.junekim.univinfo;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.junekim.univinfo.Model.Internship;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import static com.google.android.gms.internal.zzs.TAG;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity{

    @ViewById
    ViewPager pager;

    @ViewById
    TabLayout main_tabs;

    private Fragment fragment1,fragment2,fragment3;

    private TabPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @AfterViews
    protected void afterViews(){


        fragment1 = new InternshipFragment_();
        fragment2 = new InternshipFragment_();
        fragment3 = new InternshipFragment_();


        main_tabs.addTab(main_tabs.newTab().setText("").setIcon(R.drawable.ic_internship));
        main_tabs.addTab(main_tabs.newTab().setText("").setIcon(R.drawable.ic_scholarship));
        main_tabs.addTab(main_tabs.newTab().setText("").setIcon(R.drawable.ic_myinfo));

        mAdapter = new TabPagerAdapter(this.getSupportFragmentManager(),main_tabs.getTabCount());

        pager.setAdapter(mAdapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(main_tabs));

        main_tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = db.getReference("message");
//
//        myRef.setValue("안녕 나는 준");
//
//
//        myRef.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG,"Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d(TAG,"값을 읽는데 실패했습니다.");
//            }
//        });
    }


    public class TabPagerAdapter extends FragmentPagerAdapter{

        private int tabCount;

        public TabPagerAdapter(FragmentManager fm,int tabCount) {
            super(fm);
            this.tabCount = tabCount;
        }


        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return fragment1;
                case 1:
                    return fragment2;
                case 2:
                    return fragment3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return tabCount;
        }
    }




}
