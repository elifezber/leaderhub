package com.example.leaderhubtaslak.ui.sifre;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentSifreBinding;
import com.example.leaderhubtaslak.ui.settings.SettingsFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class SifreFragment extends Fragment {
    private Button buttonGonder;
    private EditText editTextEmailAddress3;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    private TextView textViewIptal;
    private String txtEmail;

    private FragmentSifreBinding binding;

    private SifreViewModel mViewModel;

    public static SifreFragment newInstance() {
        return new SifreFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SifreViewModel sifreViewModel =
                new ViewModelProvider(this).get(SifreViewModel.class);

        binding = FragmentSifreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        buttonGonder=root.findViewById(R.id.buttonGonder);
        editTextEmailAddress3=root.findViewById(R.id.editTextEmailAddress3);

        textViewIptal=root.findViewById(R.id.textViewIptal);
        progressBar=root.findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        binding.buttonGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               txtEmail = editTextEmailAddress3.getText().toString();

               if(!TextUtils.isEmpty(txtEmail)){
                    ResetPassword();
               } else{
                   editTextEmailAddress3.setError("Email kısmı boş olamaz");
               }


            }
        });

        binding.textViewIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main,new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return root;


    }

    private void ResetPassword(){
        progressBar.setVisibility(View.VISIBLE);
        buttonGonder.setVisibility(View.INVISIBLE);

        mAuth.sendPasswordResetEmail(txtEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(SifreFragment.this.getContext(), "Şifre sıfırlama linki kayıtlı email adresinize gönderilmiştir" , Toast.LENGTH_LONG).show();

                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SifreFragment.this.getContext(), "Şifre sıfırlama başarısız oldu", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        buttonGonder.setVisibility(View.VISIBLE);
                    }
                });
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SifreViewModel.class);
        // TODO: Use the ViewModel
    }

}