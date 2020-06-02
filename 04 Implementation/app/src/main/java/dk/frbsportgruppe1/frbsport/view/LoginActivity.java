package dk.frbsportgruppe1.frbsport.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dk.frbsportgruppe1.frbsport.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

    private Button loginButton;
    private TextInputEditText textInputEmail;
    private TextInputEditText textInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase authentication

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            Intent intent = new Intent(this, PatientMainActivity.class);
            startActivity(intent);
        }

        // Firebase authentication

        loginButton = findViewById(R.id.loginButton);
        textInputEmail = findViewById(R.id.loginEmailInput);
        textInputPassword = findViewById(R.id.loginPasswordInput);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputEmail.getText().toString();
                String password = textInputPassword.getText().toString();

                signIn(email, password);
            }
        });
    }

    /**
     * Forsøger at logge ind med den angivne email og password
     * @param email email til at logge ind med
     * @param password password til at logge ind
     */
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete: login success");
                    Intent intent = new Intent(LoginActivity.this, PatientMainActivity.class);
                    startActivity(intent);
                } else {
                    Log.d(TAG, "onComplete: login fail");
                    Toast toast = Toast.makeText(LoginActivity.this, "Kunne ikke logge ind.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
}