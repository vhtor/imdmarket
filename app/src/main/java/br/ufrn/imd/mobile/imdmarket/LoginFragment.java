package br.ufrn.imd.mobile.imdmarket;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class LoginFragment extends Fragment {
    TextView usernameInput, passwordInput;

    public LoginFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        usernameInput = view.findViewById(R.id.username_input);
        passwordInput = view.findViewById(R.id.password_input);

        // Get user input when login button is clicked
        Button loginBtn = view.findViewById(R.id.login_button);
        loginBtn.setOnClickListener(event -> {
            boolean podeEntrar = validateLogin();
            if (podeEntrar) {
                fazerLogin();
            }
        });

        return view;
    }



    private boolean validateLogin() {
        String usernameInputText = usernameInput.getText().toString();
        String passwordInputText = passwordInput.getText().toString();

        if (usernameInput.getText().toString().isEmpty() || passwordInput.getText().toString().isEmpty()) {
            return false;
        }

        SharedPreferences loginPreference = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);

        String usernamePreference = loginPreference.getString("username", "");
        String passwordPreference = loginPreference.getString("password", "");

        return usernameInputText.equals(usernamePreference) && passwordInputText.equals(passwordPreference);
    }

    private void fazerLogin() {
        System.out.println("fazendo login");
    }
}