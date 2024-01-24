package com.example.leaderhubtaslak.ui.kayitol;

import androidx.fragment.app.FragmentTransaction;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentKayitBinding;
import com.example.leaderhubtaslak.ui.introduction.introductionFragment;
import com.example.leaderhubtaslak.ui.settings.SettingsFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class KayitFragment extends Fragment {
    private EditText editTextAd, editTextSoyad, editTextEmailAddress, editTextPassword;
    private Button buttonHesapOlustur;
    private TextView textView39;

    private String txtAd, txtSoyad, txtEmail, txtPassword;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mReference;
    private HashMap<String, Object> mData;


    private FragmentKayitBinding binding;

    private KayitViewModel mViewModel;

    public static KayitFragment newInstance() {
        return new KayitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        KayitViewModel kayitViewModel =
                new ViewModelProvider(this).get(KayitViewModel.class);

        binding = FragmentKayitBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        editTextAd = root.findViewById(R.id.editTextAd);
        editTextSoyad = root.findViewById(R.id.editTextSoyad);
        editTextEmailAddress = root.findViewById(R.id.editTextEmailAddress);
        editTextPassword = root.findViewById(R.id.editTextPassword);

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();

        buttonHesapOlustur = root.findViewById(R.id.buttonHesapOlustur);

        textView39 = root.findViewById(R.id.textViewGirisYap);

        binding.buttonHesapOlustur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAd = editTextAd.getText().toString();
                txtSoyad = editTextSoyad.getText().toString();
                txtEmail = editTextEmailAddress.getText().toString();
                txtPassword = editTextPassword.getText().toString();

                if(!TextUtils.isEmpty(txtAd) && !TextUtils.isEmpty(txtSoyad) && !TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtPassword)){
                    mAuth.createUserWithEmailAndPassword( txtEmail , txtPassword)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        mUser = mAuth.getCurrentUser();
                                        goToIntroductionFragment();

                                    //    Toast.makeText(KayitFragment.this.getContext(), "Kayıt işlemi başarılı!", Toast.LENGTH_LONG).show();

                                        mData = new HashMap<>();
                                        mData.put("kullaniciAdı", txtAd);
                                        mData.put("kullaniciSoyadi" , txtSoyad);
                                        mData.put("kullaniciEmail" , txtEmail);
                                        mData.put("kullaniciSifre" , txtPassword);
                                        mData.put("kullaniciId" , mUser.getUid());

                                        mReference.child("Kullanıcılar").child(mUser.getUid())
                                                .setValue(mData)
                                                .addOnCompleteListener(requireActivity(), new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful())
                                                            Snackbar.make(v, "Kayıt işlemi başarılı!", Snackbar.LENGTH_LONG)
                                                                    .setAction("Action", null).show();
                                                           // Toast.makeText(KayitFragment.this.getContext(), "Kayıt işlemi başarılı!", Toast.LENGTH_LONG).show();
                                                        else
                                                            Toast.makeText(KayitFragment.this.getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(KayitFragment.this.getContext(), task.getException().getMessage() , Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
                else
                    Toast.makeText(KayitFragment.this.getContext(), "Belirtilen alanlar boş bırakılamaz!" , Toast.LENGTH_LONG).show();
            }
        });

        textView39.setOnClickListener(new View.OnClickListener() {
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
    private void goToIntroductionFragment() {
        // Giriş başarılı olduğunda introductionFragment'a geçiş yap
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_content_main, new introductionFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }


        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
            super.onActivityCreated(savedInstanceState);
            mViewModel = new ViewModelProvider(this).get(KayitViewModel.class);
            // TODO: Use the ViewModel
        }

}