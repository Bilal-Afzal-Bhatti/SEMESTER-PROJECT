package com.example.layer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class loginFragment extends Fragment {

    private EditText etemail, etpwd;
    private Button btnlogin, register;
    private DBHELPER dbHelper;
    private Fragment AFragment;

    public loginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login2, container, false);

        etemail = view.findViewById(R.id.etemail);
        etpwd = view.findViewById(R.id.etpwd);
        btnlogin = view.findViewById(R.id.btnlogin);
        register = view.findViewById(R.id.register);
        dbHelper = new DBHELPER(getActivity());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString().trim();
                String password = etpwd.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbHelper.checkUserCredentials (email, password)) {
                        Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                        navigateToFragmentA();
                    } else {
                        Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(v -> {
            signupFragment signUpFragment = new signupFragment();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, signUpFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return view;
    }

    private void navigateToFragmentA() {
        AFragment Fragment = new AFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, Fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
