package com.example.junekim.univinfo.More;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;
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
import java.util.Calendar;
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

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final Context context = new ContextThemeWrapper(this, android.R.style.Theme_Material_Dialog);

        final DatePickerDialog.OnDateSetListener seconddateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    period.setText(period.getText()+"~"+year+"."+(monthOfYear+1)+"."+dayOfMonth);
            }
        };


        DatePickerDialog.OnDateSetListener firstdateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                period.setText(year+"."+(monthOfYear+1)+"."+dayOfMonth);

                DatePickerDialog datePickerDialog2 = new DatePickerDialog(context, seconddateSetListener,  calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog2.show();
            }
        };




        DatePickerDialog datePickerDialog = new DatePickerDialog(context, firstdateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();


    }




    @Click
    void write_done(){
        //TODO 입력받은 것 검사해서 하나라도 없으면 돌려보내고 + Toast안뜸

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
        String period_str = period.getText().toString();


        if( spec_title.length() == 0 || spec_title == null || spec_title.isEmpty()){
            Toast.makeText(this,"스펙 이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        if( period_str.length() == 0 || period_str == null || period_str.isEmpty()){
            Toast.makeText(this,"기간을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        if( job.length() == 0 || job == null || job.isEmpty()){
            Toast.makeText(this,"업무 내용을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }


        if( difficulty_point.length() == 0 || difficulty_point == null || difficulty_point.isEmpty()){
            Toast.makeText(this,"어려웠던 점을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }



        if( learning_point.length() == 0 || learning_point == null || learning_point.isEmpty()){
            Toast.makeText(this,"배운 점을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        Spec spec = new Spec(spec_title,period_str,job,difficulty_point,learning_point);

        String uuid = UUID.randomUUID().toString();
        mDataReference.child("spec").child(uuid).setValue(spec);

        finish();
    }

}
