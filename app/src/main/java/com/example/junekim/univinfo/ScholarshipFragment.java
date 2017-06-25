package com.example.junekim.univinfo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junekim.univinfo.Model.Internship;
import com.example.junekim.univinfo.Model.Scholarship;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JuneKim on 2017. 6. 12..
 */

@EFragment(R.layout.fragment_scholarship)
public class ScholarshipFragment extends Fragment {

    private static final String TAG = "ScholarshipFragment" ;
    @ViewById
    ListView scholarship_list;

    private ArrayList<Scholarship> dummys = new ArrayList<Scholarship>();


    private ListViewAdapter mAdapter;
    private DatabaseReference myRef;
    private Scholarship scholarship;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        myRef = db.getReference("scholarship");


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(), "onCreateView()");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    scholarship = snapshot.getValue(Scholarship.class);
                    dummys.add(scholarship);
                }

                mAdapter = new ListViewAdapter(dummys);
                scholarship_list.setAdapter(mAdapter);

                if(progressDialog != null){
                    progressDialog.hide();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"장학금 정보를 불러오는데 실패했습니다.",Toast.LENGTH_LONG).show();
            }
        });

        return inflater.inflate(R.layout.fragment_scholarship, null);
    }


    @AfterViews
    protected void afterViews(){

        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }


    private class ListViewAdapter extends BaseAdapter {

        private List<Scholarship> mList;

        public ListViewAdapter(List<Scholarship> list){
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
            final Scholarship mScholarship = mList.get(position);

            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.view_scholarship_card, null);
                setViewHolder(convertView,holder);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }


            if(mScholarship!=null){
                if(mScholarship.title!=null){
                    holder.job_name.setText(mScholarship.title);
                }
            }

            return convertView;
        }

        private void setViewHolder(View convertView, ViewHolder holder) {
                holder.job_name = (TextView) convertView.findViewById(R.id.job_name);
        }

    }

    private class ViewHolder {
        public TextView job_name;
    }
}
