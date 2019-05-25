package com.shuttle.easytransport.callfordoctors.UTILs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityHandler {
    private static ConnectivityHandler mInstance;

    private ConnectivityHandler(){

    }

    public static ConnectivityHandler getInstance(){
        if(mInstance == null){
            mInstance = new ConnectivityHandler();
        }
        return mInstance;
    }

    private NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public boolean isConnected(Context context){
        NetworkInfo info = getNetworkInfo(context);
        return (info != null && info.isConnected());
    }
}
