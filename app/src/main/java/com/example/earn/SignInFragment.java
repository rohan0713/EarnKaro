package com.example.earn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class SignInFragment extends Fragment {

    EditText mobileNumber, email, password;
    TextInputLayout textInputLayout1, textInputLayout2, textInputLayout3;
    Button verify, login;
    boolean flag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        textInputLayout1 = view.findViewById(R.id.textInputLayout1);
        textInputLayout2 = view.findViewById(R.id.textInputLayout2);
        textInputLayout3 = view.findViewById(R.id.textinputlayout3);

        mobileNumber = view.findViewById(R.id.mobile_number);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        verify = view.findViewById(R.id.verify);
        login = view.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag){
                    userHelperClass.put(view.getContext(), "login", "true");
                    Intent intent = new Intent(view.getContext(), home_screen.class);
                    startActivity(intent);
                    requireActivity().finish();
                }else{
                    textInputLayout1.setVisibility(View.GONE);
                    textInputLayout2.setVisibility(View.VISIBLE);
                    textInputLayout3.setVisibility(View.VISIBLE);
                    flag = true;
                }

            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag){
                    textInputLayout1.setVisibility(View.VISIBLE);
                    textInputLayout2.setVisibility(View.GONE);
                    textInputLayout3.setVisibility(View.GONE);
                    flag = false;
                }else{

                    Intent intent = new Intent(view.getContext(), otpActivity.class);
                    startActivity(intent);
                    requireActivity().finish();
                }

            }
        });
        return view;
    }
}