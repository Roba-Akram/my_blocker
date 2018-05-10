package com.iug.blocker;

/**
 * Created by ahmed on 4/20/2018.
 */


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.iug.blocker.model.Profile;

import java.util.List;


public class ProfilesRecyclerViewAdapter extends RecyclerView.Adapter<ProfilesRecyclerViewAdapter.ContactViewHolder> {

    public static List<Profile> contactList;

    public ProfilesRecyclerViewAdapter(List<Profile> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, int i) {
        Profile profile = contactList.get(i);
        contactViewHolder.name.setText(profile.getName());

     }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.rw_recycle, viewGroup, false);

        return new ContactViewHolder(itemView);
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout swipeLayout;
        private View frontLayout;
        private View deleteLayout;
        private TextView name;
        TextView description ;


        public ContactViewHolder(final View v) {
            super(v);
//            swipeLayout = (LinearLayout) itemView.findViewById(R.id.swipe_layout2);
//            frontLayout = itemView.findViewById(R.id.front_layout);

            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);


        }

    }
}
