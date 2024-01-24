package com.example.leaderhubtaslak.ui.iletisim;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentIletisimBinding;
import com.google.android.material.snackbar.Snackbar;

public class IletisimFragment extends Fragment {
    private Snackbar snackbar;

    private Button button5;


    private FragmentIletisimBinding binding;

    private IletisimViewModel mViewModel;

    public static IletisimFragment newInstance() {
        return new IletisimFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        IletisimViewModel iletisimViewModel =
                new ViewModelProvider(this).get(IletisimViewModel.class);

        binding = FragmentIletisimBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        button5 = root.findViewById(R.id.button5);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Bizimle iletişime geçtiğiniz için teşekkür ederiz. En kısa sürede size geri dönüş yapacağız.", Snackbar.LENGTH_LONG)
                 .setAction("Action", null).show();


            }
        });





        return root;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(IletisimViewModel.class);
        // TODO: Use the ViewModel
    }

}