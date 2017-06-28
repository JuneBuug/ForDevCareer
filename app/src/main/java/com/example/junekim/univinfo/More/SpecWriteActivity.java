package com.example.junekim.univinfo.More;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junekim.univinfo.Model.Credit;
import com.example.junekim.univinfo.Model.Spec;
import com.example.junekim.univinfo.R;
import com.example.junekim.univinfo.WebViewActivity_;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EActivity(R.layout.activity_spec_write)
public class SpecWriteActivity extends Activity {


    @ViewById
    TextView page_title,period,write_done;

    @ViewById
    ImageView ic_cancel;

    @ViewById
    EditText spec_name,job_desc,difficulty,learning;


    private DatabaseReference mDataReference;


    @Click
    void period(){
        //TODO 달력 dialog 연결해서 시작 , 끝 날짜 입력 받고 개월 수 계산
    }


    @Click
    void write_done(){
        //TODO 입력받은 것 검사해서 하나라도 없으면 돌려보내고 + Toast , 되면 데이터베이스 UUID 생성해서 에 넣기

        writeSpec();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataReference = FirebaseDatabase.getInstance().getReference();

    }

    @Click
    void ic_cancel(){
        finish();
    }

    @AfterViews
    protected void afterViews(){


    }


    private void writeSpec(){

        String spec_title = spec_name.getText().toString();
        String job = job_desc.getText().toString();
        String difficulty_point = difficulty.getText().toString();
        String learning_point = learning.getText().toString();


        if( spec_title.length() == 0 || spec_title == null || spec_title.isEmpty()){
            Toast.makeText(this,"스펙 이름을 입력해주세요.",Toast.LENGTH_SHORT);
            return;
        }


        if( job.length() == 0 || job == null || job.isEmpty()){
            Toast.makeText(this,"업무 내용을 입력해주세요.",Toast.LENGTH_SHORT);
            return;
        }


        if( difficulty_point.length() == 0 || difficulty_point == null || difficulty_point.isEmpty()){
            Toast.makeText(this,"어려웠던 점을 입력해주세요.",Toast.LENGTH_SHORT);
            return;
        }



        if( learning_point.length() == 0 || learning_point == null || learning_point.isEmpty()){
            Toast.makeText(this,"배운 점을 입력해주세요.",Toast.LENGTH_SHORT);
            return;
        }

        Spec spec = new Spec(spec_title,"4개월",job,difficulty_point,learning_point);

        String uuid = UUID.randomUUID().toString();
        mDataReference.child("spec").child(uuid).setValue(spec);

        finish();
    }

}
