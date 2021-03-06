package com.example.diamond_miner;


import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;


public class GPS extends Service {
    private LocationListener listener;
    private LocationManager locationManager;
    private Location mLocation;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent(Constants.ACTION_KEY);
                i.putExtra(Constants.LONGITUDE_KEY, location.getLongitude());
                i.putExtra(Constants.LATITUDE_KEY, location.getLatitude());
                Log.d("loc", location.getLongitude() + " foq " + location.getLatitude());
                Log.d("loc", mLocation.getLongitude() + "  foq " + mLocation.getLatitude());
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };

        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, listener);

        mLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (mLocation != null) {
            Intent i = new Intent(Constants.ACTION_KEY);
            i.putExtra(Constants.LONGITUDE_KEY, mLocation.getLongitude());
            i.putExtra(Constants.LATITUDE_KEY, mLocation.getLatitude());
            sendBroadcast(i);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            locationManager.removeUpdates(listener);
        }
    }
}