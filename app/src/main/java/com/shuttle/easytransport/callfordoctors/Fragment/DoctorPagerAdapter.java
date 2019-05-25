package com.shuttle.easytransport.callfordoctors.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DoctorPagerAdapter extends FragmentPagerAdapter {

    public DoctorPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                DoctorRequestsFragment requestFragment=new DoctorRequestsFragment();
                return requestFragment;
            case 1:
                DoctorChatsFragment chatFragment=new DoctorChatsFragment();
                return chatFragment;
            case 2:
                DoctorFriendsFragment friendFragment=new DoctorFriendsFragment();
                return friendFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0:
                return "REQUESTS";
            case 1:
                return "CHATS";
            case 2:
                return "FRIENDS";
            default:
                return null;
        }
    }

}
