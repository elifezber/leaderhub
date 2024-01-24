package com.example.leaderhubtaslak.ui.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentSettingsBinding;
import com.example.leaderhubtaslak.ui.introduction.introductionFragment;
import com.example.leaderhubtaslak.ui.kayitol.KayitFragment;
import com.example.leaderhubtaslak.ui.sifre.SifreFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment {

    private Button buttonGirisYap;
    private EditText editTextEmailAddress2,editTextPassword2;
    private String txtEmail, txtPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;


    private TextView textView43,textView13;


    private FragmentSettingsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textView43=root.findViewById(R.id.textView43);
        textView13=root.findViewById(R.id.textView13);
        editTextEmailAddress2=root.findViewById(R.id.editTextEmailAddress2);
        editTextPassword2=root.findViewById(R.id.editTextPassword2);
        buttonGirisYap = root.findViewById(R.id.buttonGirisYap);

        mAuth = FirebaseAuth.getInstance();

        binding.buttonGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail = editTextEmailAddress2.getText().toString();
                txtPassword = editTextPassword2.getText().toString();

                if(!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtPassword)){
                    mAuth.signInWithEmailAndPassword(txtEmail , txtPassword)
                            .addOnSuccessListener(getActivity(), new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    mUser = mAuth.getCurrentUser();

                                    goToIntroductionFragment();
                                }
                            }).addOnFailureListener(getActivity(), new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SettingsFragment.this.getContext(), e.getMessage() , Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });
        binding.textView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main,new SifreFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        textView43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, new KayitFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return root;
    }

    private void goToIntroductionFragment() {
        // Giriş başarılı olduğunda introductionFragment'a geçiş yap
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, new introductionFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }





    //final TextView textView = binding.textSettings;
    //  settingsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}