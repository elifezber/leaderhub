package com.example.leaderhubtaslak.ui.introduction;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentIntroductionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class introductionFragment extends Fragment {
    private Button buttonAboneOl;
    private EditText editTextEmailAddress4;
    private String txtEmail;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    private HashMap<String, Object> mData;

    private DatabaseReference mReference;
    private Snackbar snackbar;




    private FragmentIntroductionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        introductionViewModel introductionViewModel =
                new ViewModelProvider(this).get(introductionViewModel.class);

        binding = FragmentIntroductionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonAboneOl=root.findViewById(R.id.button27);
        editTextEmailAddress4=root.findViewById(R.id.editTextEmailAddress4);

        mAuth = FirebaseAuth.getInstance();






        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}