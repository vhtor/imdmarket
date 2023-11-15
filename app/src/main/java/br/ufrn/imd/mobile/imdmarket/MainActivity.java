package br.ufrn.imd.mobile.imdmarket;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    ImageButton homeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeBtn = findViewById(R.id.home_button);
        homeBtn.setOnClickListener(event -> {
            renderMenuFragment();
        });

        setUpLoginPreference();
        renderLoginFragment();
    }

    private void renderMenuFragment() {
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.app_frame, new MenuFragment());
        fragTransaction.commit();
    }

    public void renderLoginFragment(){
        FragmentTransaction fragTransaction = getSupportFragmentManager().beginTransaction();
        fragTransaction.replace(R.id.app_frame, new LoginFragment());
        fragTransaction.commit();
    }

    private void setUpLoginPreference() {
        SharedPreferences loginPreference = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPreference.edit();
        editor.putString("username", "admin");
        editor.putString("password", "admin");
        editor.apply();
    }
}