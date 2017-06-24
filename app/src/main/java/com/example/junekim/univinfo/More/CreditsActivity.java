package com.example.junekim.univinfo.More;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junekim.univinfo.InternshipFragment;
import com.example.junekim.univinfo.Model.Credit;
import com.example.junekim.univinfo.Model.Internship;
import com.example.junekim.univinfo.R;
import com.example.junekim.univinfo.WebViewActivity_;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_spec)
public class CreditsActivity extends FragmentActivity{


    @ViewById
    TextView page_title;

    @ViewById
    ImageView ic_cancel;

    @ViewById
    ListView spec_list;

    private DatabaseReference myRef;
    private ListViewAdapter mAdapter;

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

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                ArrayList<Credit> credits = new ArrayList<Credit>();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Credit credit = snapshot.getValue(Credit.class);
                    credits.add(credit);
                }

                // [START_EXCLUDE]
                mAdapter = new ListViewAdapter(credits);
                spec_list.setAdapter(mAdapter);


                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
//                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(getApplicationContext(), "참조 정보를 가져오는 데 실패했습니다.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        });

    }

    private class ListViewAdapter extends BaseAdapter {

        private List<Credit> mList;

        public ListViewAdapter(List<Credit> list){
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
            final Credit mCredit = mList.get(position);

            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.view_credits_card, null);
                setViewHolder(convertView,holder);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }

            if(mCredit != null){

                if(mCredit.source_by != null){
                    holder.source_by.setText(mCredit.source_by);
                }

                if(mCredit.source_name != null){
                    holder.source_name.setText(mCredit.source_name);
                }


                holder.base_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebViewActivity_.intent(getApplicationContext())
                                        .extra("url",mCredit.link_url)
                                        .start();
                    }
                });
            }

            return convertView;
        }

        private void setViewHolder(View convertView, ViewHolder holder) {
            holder.source_name = (TextView) convertView.findViewById(R.id.source_name);
            holder.source_by = (TextView) convertView.findViewById(R.id.source_by);
            holder.base_layout = (LinearLayout) convertView.findViewById(R.id.base_layout);
        }

    }

    private class ViewHolder {
        public TextView source_name,source_by;
        public LinearLayout base_layout;
    }

    public static boolean isEmpty(String string) {
        if(string == null || string.equals("") || string.length() == 0) {
            return true;
        }
        return false;
    }


}
