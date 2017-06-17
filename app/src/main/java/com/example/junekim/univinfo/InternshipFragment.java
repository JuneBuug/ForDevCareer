package com.example.junekim.univinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junekim.univinfo.Model.Internship;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.google.android.gms.internal.zzs.TAG;


/**
 * Created by JuneKim on 2017. 6. 12..
 */

@EFragment(R.layout.fragment_internship)
public class InternshipFragment  extends Fragment {

    private static final String TAG = "IntershipFragment" ;

    @ViewById
    ListView internship_list;

    private ArrayList<Internship> dummys = new ArrayList<Internship>();


    private Internship internship;
    private ListViewAdapter mAdapter;
    private DatabaseReference myRef;
    private ValueEventListener internshiplistener;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myRef = db.getReference("internship");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(), "onCreateView()");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                ArrayList<Internship> internships = new ArrayList<Internship>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    internship = snapshot.getValue(Internship.class);
                    internships.add(internship);
                }

                // [START_EXCLUDE]
                mAdapter = new ListViewAdapter(internships);
                internship_list.setAdapter(mAdapter);

                if(progressDialog != null){
                    progressDialog.hide();
                }

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(getActivity(), "인턴십 정보를 가져오는 데 실패했습니다.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        });

        return inflater.inflate(R.layout.fragment_internship, null);
    }

    @Override
    public void onStart(){
        super.onStart();




    }

    @AfterViews
    protected void afterViews(){

        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.show();
        }

//        mAdapter = new ListViewAdapter(dummys);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    private class ListViewAdapter extends BaseAdapter {

        private List<Internship> mList;

        public ListViewAdapter(List<Internship> list){
            super();
            this.mList = list;
        }
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final ViewHolder holder;
            final Internship mInternship = mList.get(position);

            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.view_internship_card, null);
                setViewHolder(convertView,holder);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }


            if(mInternship!=null){
                if(mInternship.title!=null){
                    holder.job_name.setText(mInternship.title);
                }


                if(mInternship.endDate!=null){
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Date date = dateFormat.parse(mInternship.endDate);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);

                        holder.endDate.setText(cal.get(Calendar.MONTH)+1+"월 "+cal.get(Calendar.DATE)+"일 마감");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }

            return convertView;
        }

        private void setViewHolder(View convertView, ViewHolder holder) {
                holder.job_name = (TextView) convertView.findViewById(R.id.job_name);
                holder.endDate = (TextView) convertView.findViewById(R.id.endDate);
        }

    }

    private class ViewHolder {
        public TextView job_name,endDate;
    }

    public static boolean isEmpty(String string) {
        if(string == null || string.equals("") || string.length() == 0) {
            return true;
        }
        return false;
    }
}
