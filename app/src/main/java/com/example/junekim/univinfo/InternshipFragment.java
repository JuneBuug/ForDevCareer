package com.example.junekim.univinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by JuneKim on 2017. 6. 12..
 */

@EFragment(R.layout.fragment_internship)
public class InternshipFragment  extends Fragment {

    @ViewById
    ListView internship_list;

    private ArrayList<String> dummys = new ArrayList<String>();

    private ListViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void afterViews(){

        for(int i=0;i<5;i++){
            dummys.add("a");
        }

        mAdapter = new ListViewAdapter(dummys);
        internship_list.setAdapter(mAdapter);

    }


    private class ListViewAdapter extends BaseAdapter {

        private List<String> mList;

        public ListViewAdapter(List<String> list){
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

            if(convertView == null){
                holder = new ViewHolder();
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.view_internship_card, null);



                convertView.setTag(holder);
            }else{
                convertView.getTag();
            }


            return convertView;
        }

        private void setViewHolder(View convertView, ViewHolder holder) {

        }

    }

    private class ViewHolder {
        public TextView title, subtitle;
    }
}
