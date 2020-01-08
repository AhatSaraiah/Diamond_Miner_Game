package com.example.diamond_miner;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class Map extends AppCompatActivity implements OnMapReadyCallback {
    private ArrayList<player> playersList;
    private GoogleMap mMap;
    private LatLng[] locations;
    private ListView listView_top10;
    private Button btn_backTomenu;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_map);

        initComponents();

    }
    private void initComponents() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);

        PlayerManager playerManager = new PlayerManager(Map.this);
        playersList = playerManager.getRecords();


        listView_top10 = findViewById(R.id.listView_top10);
        btn_backTomenu = findViewById(R.id.btn_backTomenu);
        btn_backTomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        locations = new LatLng[playersList.size()];
        fillPlacesList();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (playersList.size() == 0)
            return;
        mMap = googleMap;
        player p;
        for (int i = 0; i < locations.length; i++) {
            p = playersList.get(i);
            locations[i] = new LatLng(p.getLatitude(), p.getLongitude());
            Log.d("lag",p.getLatitude()+"and "+ p.getLongitude() );
            mMap.addMarker(new MarkerOptions().position(locations[i]).title("" + (i + 1) + ": " + p.toString()).icon(BitmapDescriptorFactory.defaultMarker()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locations[0], 18.0f));
    }


    private AdapterView.OnItemClickListener listClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locations[position], 18.0f));
        }
    };

    private void fillPlacesList() {
        ArrayAdapter<player> placesAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, playersList);
        listView_top10.setAdapter(placesAdapter);
        listView_top10.setOnItemClickListener(listClickedHandler);
    }
}