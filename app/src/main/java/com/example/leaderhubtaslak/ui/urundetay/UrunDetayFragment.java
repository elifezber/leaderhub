package com.example.leaderhubtaslak.ui.urundetay;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentUrunDetayBinding;
import com.google.android.material.snackbar.Snackbar;

public class UrunDetayFragment extends Fragment {
    private Snackbar snackbar;
    private FragmentUrunDetayBinding binding;
    private Button button11;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        UrunDetayViewModel urunDetayViewModel =
                new ViewModelProvider(this).get(UrunDetayViewModel.class);

        binding = FragmentUrunDetayBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        button11=root.findViewById(R.id.button11);

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Ürün Sepete Eklendi", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });


        return root;
    }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
    }
}