package com.example.layer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class signupFragment extends Fragment {

    private EditText etemail, etpwd;
    private Button btnlogin;
    private TextView tvreg;
    private DBHELPER dbHelper;

    public signupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        etemail = view.findViewById(R.id.etemail);
        etpwd = view.findViewById(R.id.etpwd);
        btnlogin = view.findViewById(R.id.btnlogin);
        tvreg = view.findViewById(R.id.tvreg);
        dbHelper = new DBHELPER(getActivity());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etemail.getText().toString().trim();
                String password = etpwd.getText().toString().trim();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbHelper.checkUserExists(email)) {
                        Toast.makeText(getActivity(), "User already exists with this email", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean isInserted = dbHelper.insertUser(email, password);
                        if (isInserted) {
                            Toast.makeText(getActivity(), "Signup successful", Toast.LENGTH_SHORT).show();
                            navigateToLoginFragment();
                        } else {
                            Toast.makeText(getActivity(), "Signup failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        return view;
    }

    private void navigateToLoginFragment() {
        loginFragment loginFragment = new loginFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, loginFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
