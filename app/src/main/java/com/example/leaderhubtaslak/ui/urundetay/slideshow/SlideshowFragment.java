package com.example.leaderhubtaslak.ui.urundetay.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.leaderhubtaslak.R;
import com.example.leaderhubtaslak.databinding.FragmentSlideshowBinding;
import com.example.leaderhubtaslak.ui.reflow.ReflowFragment;

public class SlideshowFragment extends Fragment {
    private TextView textView8;

    private ImageView imageView8;
    private ImageView imageView7;

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imageView8= root.findViewById(R.id.imageView8);
        imageView7= root.findViewById(R.id.imageView7);
        textView8= root.findViewById(R.id.textView8);

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, new ReflowFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });


        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, new ReflowFragment())
                        .addToBackStack(null)
                        .commit();



            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, new ReflowFragment())
                        .addToBackStack(null)
                        .commit();

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