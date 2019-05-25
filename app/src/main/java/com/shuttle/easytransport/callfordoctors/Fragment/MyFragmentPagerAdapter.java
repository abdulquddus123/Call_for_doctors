package com.shuttle.easytransport.callfordoctors.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Kshitiz on 3/17/2018.
 * //---THIS CLASS FOR MANAGING FRAGMENTS---
 *
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    public MyFragmentPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RequestsFragment requestFragment=new RequestsFragment();
                return requestFragment;
            case 1:
                ChatsFragment chatFragment=new ChatsFragment();
                return chatFragment;
            case 2:
                FriendsFragment friendFragment=new FriendsFragment();
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
