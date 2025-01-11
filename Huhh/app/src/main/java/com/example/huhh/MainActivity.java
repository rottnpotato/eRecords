package com.example.huhh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huhh.api.ApiClient;
import com.example.huhh.requestResponse.LoginRequest;
import com.example.huhh.requestResponse.LoginResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView logoImageView;
    private TextView loginText;
    private TextInputLayout usernameLayout, passwordLayout;
    private TextInputEditText usernameEditText, passwordEditText;
    private MaterialButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme); // Ensure the correct theme is applied
        setContentView(R.layout.activity_main);

        initViews();
        setupAnimations();
        setupListeners();
    }

    private void initViews() {
        logoImageView = findViewById(R.id.logoImageView);
        loginText = findViewById(R.id.loginText);
        usernameLayout = findViewById(R.id.usernameLayout);
        passwordLayout = findViewById(R.id.passwordLayout);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
    }

    private void setupAnimations() {
        logoImageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        loginText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_down));
        usernameLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up));
        passwordLayout.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_up));
        loginButton.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
    }

    private void setupListeners() {
        loginButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity.this, "Username / Password Required!", Toast.LENGTH_SHORT).show();
            } else {
                login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Logged In Successfully!", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();

                    // Animate the login button before transitioning
                    loginButton.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_out));

                    new Thread(() -> {
                        try {
                            Thread.sleep(300); // Wait for animation to finish
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(() -> {
                            Intent intent = new Intent(MainActivity.this, DashActivity.class);
                            intent.putExtra("token", loginResponse.getJwt());
                            intent.putExtra("rft", loginResponse.getRft());
                            startActivity(intent);
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            finish();
                        });
                    }).start();
                } else {
                    Toast.makeText(MainActivity.this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                    loginButton.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                Log.e("LoginError", "Error during login", throwable);
            }
        });
    }
}