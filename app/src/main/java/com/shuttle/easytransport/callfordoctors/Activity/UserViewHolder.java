package com.shuttle.easytransport.callfordoctors.Activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shuttle.easytransport.callfordoctors.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name,title;
    public UserViewHolder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.user_single_name);
        title=(TextView)itemView.findViewById(R.id.user_single_status);
    }
}
