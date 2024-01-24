package com.example.leaderhubtaslak.ui.urunler;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentUrunlerBinding;
import com.example.leaderhubtaslak.ui.urundetay.UrunDetayFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class fragment_urunler extends Fragment {
    private Snackbar snackbar;
    private Button buttonAboneOl2;
    private ImageView imageView11;
    private EditText editTextEmailAddress6;

    private String txtEmail;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    DatabaseReference abonelerReferansi;

    private HashMap<String, Object> mData;

    private FragmentUrunlerBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        urunlerViewModel urunlerViewModel =
                new ViewModelProvider(this).get(urunlerViewModel.class);

        binding = FragmentUrunlerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();
        abonelerReferansi = FirebaseDatabase.getInstance().getReference().child("Aboneler");

        buttonAboneOl2 = root.findViewById(R.id.buttonAboneOl2);
        imageView11 = root.findViewById(R.id.imageView11);
        editTextEmailAddress6 = root.findViewById(R.id.editTextEmailAddress6);

        binding.imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, new UrunDetayFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.buttonAboneOl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEmail = editTextEmailAddress6.getText().toString();

                if (!TextUtils.isEmpty(txtEmail)) {
                    String sifre = "gecerliBirSifre"; // Kullanıcı şifresi
                    mAuth.createUserWithEmailAndPassword(txtEmail, sifre)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        mUser = mAuth.getCurrentUser();
                                        if (mUser != null) {
                                            mData = new HashMap<>();
                                            mData.put("kullaniciEmail", txtEmail);

                                            abonelerReferansi.child(mUser.getUid())
                                                    .setValue(mData)
                                                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Snackbar.make(v, "Abone olduğunuz için teşekkür ederiz.", Snackbar.LENGTH_LONG)
                                                                        .setAction("Action", null).show();
                                                                //  Toast.makeText(fragment_urunler.this.getContext(), "Abone olduğunuz için teşekkür ederiz.", Toast.LENGTH_LONG).show();
                                                            } else {
                                                                Toast.makeText(fragment_urunler.this.getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        } else {
                                            Snackbar.make(v, "Kullanıcı bilgileri alınamadı.", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();

                                        }
                                    } else {
                                        Toast.makeText(fragment_urunler.this.getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Snackbar.make(v, "Email adresi boş bırakılamaz!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
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



