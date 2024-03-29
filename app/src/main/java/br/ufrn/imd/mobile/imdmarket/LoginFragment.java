package br.ufrn.imd.mobile.imdmarket;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        usernameInput = view.findViewById(R.id.username_input);
        passwordInput = view.findViewById(R.id.password_input);

        // Capturar o evento de clique no botão de login
        Button loginBtn = view.findViewById(R.id.login_button);
        loginBtn.setOnClickListener(event -> {
            boolean podeEntrar = validarLogin();

            if (podeEntrar) {
                renderMenu();
            }
        });

        return view;
    }



    private boolean validarLogin() {
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

    private void renderMenu() {
        System.out.println("fazendo login");
        FragmentTransaction fragTransaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.app_frame, new MenuFragment());
        fragTransaction.commit();

    }
}