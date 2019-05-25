package com.shuttle.easytransport.callfordoctors.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shuttle.easytransport.callfordoctors.Activity.PatientProfile;
import com.shuttle.easytransport.callfordoctors.Activity.ProfileActivity;
import com.shuttle.easytransport.callfordoctors.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorRequestAdapter extends RecyclerView.Adapter<DoctorRequestAdapter.RequestViewHolder>{

    private List<String> requestList;
    private FirebaseAuth mAuth;
    DatabaseReference mDatabaseReference ;

    private Context ctx;

    public DoctorRequestAdapter(List<String> requestList) {
        this.requestList = requestList;
    }

    @Override
    public DoctorRequestAdapter.RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_list_single_user,parent,false);
        mAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("patient");

        return new DoctorRequestAdapter.RequestViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final RequestViewHolder holder, int position) {
        String user_id = requestList.get(position);
        mDatabaseReference.child(user_id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                if (dataSnapshot.getValue() != null) {


                    String userName = dataSnapshot.child("name").getValue().toString();
                    String userThumbImage = dataSnapshot.child("thumb_image").getValue().toString();
                    String userStatus =dataSnapshot.child("status").getValue().toString();

                    holder.displayName.setText(userName);
                    holder.displayStatus.setText(userStatus);
                    Picasso.with(holder.displayImage.getContext()).load(userThumbImage).placeholder(R.drawable.user_img).into(holder.displayImage);



                }else{

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ctx,ProfileActivity.class);
                intent.putExtra("user_id",requestList.get(position));


            }
        }); */


    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {

        public TextView displayName;
        public TextView displayStatus;
        public CircleImageView displayImage;
        public ImageView imageView;

        public RequestViewHolder(View itemView) {
            super(itemView);

            ctx = itemView.getContext();

            displayName = (TextView)itemView.findViewById(R.id.textViewSingleListName);
            displayStatus = (TextView) itemView.findViewById(R.id.textViewSingleListStatus);
            displayImage = (CircleImageView)itemView.findViewById(R.id.circleImageViewUserImage);
            imageView = (ImageView)itemView.findViewById(R.id.userSingleOnlineIcon);
            imageView.setVisibility(View.INVISIBLE);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    Intent intent = new Intent(ctx,PatientProfile.class);
                    intent.putExtra("user_id",requestList.get(pos));
                    ctx.startActivity(intent);
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        return requestList.size();
    }

}
