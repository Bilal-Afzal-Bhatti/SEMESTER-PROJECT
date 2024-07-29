package com.example.layer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class AFragment extends Fragment {

    public AFragment() {
        // Required empty public constructor
    }

    private ImageButton youtube_button;
    private Button menuButton;
    Button button;
    LanguageManager languageManager;

    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        languageManager = new LanguageManager(requireContext());


        button = view.findViewById(R.id.my_button);


        button.setOnClickListener(v -> {

            languageManager.updateResources("de");
            requireActivity().recreate();
        });

        youtube_button = view.findViewById(R.id.youtube_button);
        youtube_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openit("https://youtube.com/@layersbakeshop9798?si=gFf8vbgffaW9cfKK");
            }
        });

        menuButton = view.findViewById(R.id.button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenuFragment();
            }
        });

        return view;
    }

    private void openit(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    private void openMenuFragment() {
        Menu_Fragment menuFragment = new Menu_Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, menuFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
