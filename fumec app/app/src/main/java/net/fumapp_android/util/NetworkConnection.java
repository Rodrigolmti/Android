package net.fumapp_android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Rodrigo Lopes Martins on 19/01/17.
 */

public class NetworkConnection {

    private final static String PING_HOST = "google.com";
    private static boolean internetAvailable = false;

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return isInternetAvailable() && activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static boolean isInternetAvailable() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    InetAddress ipAddress = InetAddress.getByName(PING_HOST);
                    if(!ipAddress.equals("")) { internetAvailable=true; }
                } catch (UnknownHostException e) {
                    internetAvailable = false;
                }
            }
        };

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return internetAvailable;
    }
}
