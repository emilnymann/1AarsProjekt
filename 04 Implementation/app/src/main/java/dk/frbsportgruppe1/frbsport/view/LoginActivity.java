package dk.frbsportgruppe1.frbsport.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Observable;
import java.util.Observer;

import dk.frbsportgruppe1.frbsport.R;
import dk.frbsportgruppe1.frbsport.model.Patient;
import dk.frbsportgruppe1.frbsport.model.Practicioner;
import dk.frbsportgruppe1.frbsport.model.SessionManager;
import dk.frbsportgruppe1.frbsport.model.User;
import dk.frbsportgruppe1.frbsport.repository.UserRepository;

public class LoginActivity extends AppCompatActivity implements Observer {

    private static final String TAG = "LoginActivity";

    private FirebaseAuth auth;

    private Button loginButton;
    private TextInputEditText textInputEmail;
    private TextInputEditText textInputPassword;
    private LinearLayout loginLinearLayout;
    private ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        textInputEmail = findViewById(R.id.loginEmailInput);
        textInputPassword = findViewById(R.id.loginPasswordInput);
        loginLinearLayout = findViewById(R.id.loginLinearLayout);
        loginProgressBar = findViewById(R.id.loginProgressBar);

        auth = FirebaseAuth.getInstance();
        final UserRepository userRepository = new UserRepository(this);

        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            userRepository.signInSession(user);
        } else {
            loginProgressBar.setVisibility(View.INVISIBLE);
            loginLinearLayout.setVisibility(View.VISIBLE);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textInputEmail.getText().toString();
                String password = textInputPassword.getText().toString();

                signIn(email, password, userRepository);
            }
        });
    }

    /**
     * Forsøger at logge ind med den angivne email og password
     * @param email email til at logge ind med
     * @param password password til at logge ind
     */
    private void signIn(String email, String password, final UserRepository userRepository) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "onComplete: login success");
                    userRepository.signInSession(task.getResult().getUser());
                } else {
                    Log.d(TAG, "onComplete: login fail");
                    Toast toast = Toast.makeText(LoginActivity.this, "Kunne ikke logge ind.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {

        User user = SessionManager.getInstance().getCurrentUser();
        Intent intent = new Intent();

        if (user.getClass() == Patient.class) {
            intent = new Intent(this, PatientMainActivity.class);
        } else if (user.getClass() == Practicioner.class) {
            Log.d(TAG, "update: logged in practitioner.");
        } else {
            Log.d(TAG, "update: logged in: invalid user class");
        }

        startActivity(intent);
    }
}