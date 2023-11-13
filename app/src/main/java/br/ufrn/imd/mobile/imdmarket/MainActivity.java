package br.ufrn.imd.mobile.imdmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpLoginPreference();
        renderLoginFragment();
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