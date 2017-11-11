package dizhang.com.example.Control;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Class Name: ConnectionDetector
 *
 * Created by dz2 on 2017-11-10.
 *
 *source demonstration of coding: https://www.youtube.com/watch?v=9H4Ik9hqDlQ
 *
 * Version: 1.0
 *
 * Copyright (c) Team03. CMPUT301. University of Alberta. All Rights Reserved. You may use,
 * distribute or modify the code under terms and conditions of the Code of Students Behavior
 * at University of Alberta
 */


public class ConnectionDetector {
    Context context;

    public ConnectionDetector() {
        this.context = context;
    }
    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);

        if (cm != null) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null){
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }
    return false;
    }
}
