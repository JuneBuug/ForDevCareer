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

@EActivity(R.layout.activity_spec_write)
public class SpecWriteActivity extends Activity {


    @ViewById
    TextView page_title,period,write_done;

    @ViewById
    ImageView ic_cancel;

    @ViewById
    EditText spec_name,job_desc,difficulty,learning;


    private DatabaseReference myRef;


    @Click
    void period(){
        //TODO 달력 dialog 연결해서 시작 , 끝 날짜 입력 받고 개월 수 계산
    }


    @Click
    void write_done(){
        //TODO 입력받은 것 검사해서 하나라도 없으면 돌려보내고 + Toast , 되면 데이터베이스에 넣기

        String spec_title = spec_name.getText().toString();

        if( spec_title.length() == 0 || spec_title == null || spec_title.isEmpty()){
            Toast.makeText(this,"스펙 이름을 입력해주세요.",Toast.LENGTH_SHORT);
            return;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myRef = db.getReference("credit");
    }

    @Click
    void ic_cancel(){
        finish();
    }

    @AfterViews
    protected void afterViews(){


    }


}
