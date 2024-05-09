package com.ar.enbaldeapp.ui.register;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ar.enbaldeapp.R;
import com.ar.enbaldeapp.services.ApiServices;
import com.ar.enbaldeapp.ui.Utilities;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private EditText passwordEditText;
    private EditText usernameEditText;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        TextView textView = view.findViewById(R.id.registrationAlreadyAccountTextView);
        textView.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
            Utilities.changeToolbarTitleToLogin(getActivity());
        });

        firstNameEditText = view.findViewById(R.id.registerFirstNameEditText);
        lastNameEditText = view.findViewById(R.id.registerLastNameEditText);
        emailEditText = view.findViewById(R.id.registerEmailEditText);
        addressEditText = view.findViewById(R.id.registerAddressEditText);
        phoneEditText = view.findViewById(R.id.registerPhoneEditText);
        passwordEditText = view.findViewById(R.id.registerPasswordEditText);
        usernameEditText = view.findViewById(R.id.registerUsernameEditText);

        Button button = view.findViewById(R.id.buttonRegisterAccount);
        button.setOnClickListener(v -> {
            new ApiServices().register(firstNameEditText.getText().toString(),
                    lastNameEditText.getText().toString(),
                    emailEditText.getText().toString(),
                    addressEditText.getText().toString(),
                    phoneEditText.getText().toString(),
                    usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    r -> {

                    },
                    f -> {

                    });
        });

        Utilities.changeToolbarTitleToRegistration(getActivity());
        return view;
    }
}