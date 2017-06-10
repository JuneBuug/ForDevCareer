package com.example.junekim.univinfo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
public class MainActivity extends Activity{

    @ViewById
    ListView main_list;

    private ListViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void afterViews(){

        mAdapter = new ListViewAdapter();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("message");

        myRef.setValue("안녕 나는 준");


        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG,"Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG,"값을 읽는데 실패했습니다.");
            }
        });
    }



    private class ListViewAdapter extends BaseAdapter {

        private List<String> mList;

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

            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate

                convertView.setTag(holder);
            }else{
                convertView.getTag();
            }
            return null;
        }

        private void setViewHolder(View convertView, ViewHolder holder) {

        }

    }

    private class ViewHolder {
        public TextView title, subtitle;
    }
}
