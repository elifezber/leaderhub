package com.example.leaderhubtaslak.ui.map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    GoogleMap googleMap;
    private FragmentMapsBinding binding;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.0069,29.1899 ),18f));
            LatLng konum= new LatLng(41.0069, 29.1899);
            googleMap.addMarker(new MarkerOptions().position(konum).title("SENATECH / YETKİLİ YENİLEME MERKEZİ"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(konum));
            //41.0069,29.1899 ),18f));
            //Enlem: 41.0381
            //Boylam: 28.8523
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //MapsViewModel mapViewModel =
                //new ViewModelProvider(this).get(MapsViewModel.class);

        //binding = FragmentMapsBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();

        //return root;

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }


            //@Override
            //public void onDestroyView() {
                //super.onDestroyView();
                //binding = null;

            //}
}
