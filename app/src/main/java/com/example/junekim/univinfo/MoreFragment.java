package com.example.junekim.univinfo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.junekim.univinfo.Model.Internship;
import com.example.junekim.univinfo.More.CreditsActivity;
import com.example.junekim.univinfo.More.CreditsActivity_;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by JuneKim on 2017. 6. 12..
 */

@EFragment(R.layout.fragment_more)
public class MoreFragment extends Fragment {

    private static final String TAG = "MoreFragment" ;


    @ViewById
    ImageView profile_pic;

    @ViewById
    RelativeLayout more_option_1, more_option_2, more_option_3, more_option_4;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(getResources().getString(R.string.UNIVINFO_FB_STORAGE_BASE_URL));
        StorageReference pathReference = storageRef.child("images/1.jpg");


        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // FB storage에서 이미지 받아오는게 성공했을 경우

                if(uri != null){
                    Glide.with(MoreFragment.this)
                            .load(uri)
                            .into(profile_pic);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });

        return inflater.inflate(R.layout.fragment_more, null);
    }



    @Override
    public void onStart(){
        super.onStart();

    }


    @Override
    public void onDestroy(){
        super.onDestroy();
    }



    @Click
    void more_option_4(){
        CreditsActivity_.intent(this).start();
    }
}
