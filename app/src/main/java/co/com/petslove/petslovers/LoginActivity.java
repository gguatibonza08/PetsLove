package co.com.petslove.petslovers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button registro, ingreso;
    private SignInButton google;
    private EditText correoUsuario, contrasenaUsuario;
    private FirebaseAuth mAuth;

    private G

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        referencias();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.tokenGoogle))
                .requestEmail()
                .build();


    }

    private void referencias() {
        registro = findViewById(R.id.registroLoginActivity);
        ingreso = findViewById(R.id.ingresarLoginActivity);
        google = findViewById(R.id.googleLoginActivity);
        google.setSize(SignInButton.SIZE_STANDARD);
        correoUsuario = findViewById(R.id.correoLoginActivity);
        contrasenaUsuario = findViewById(R.id.contrasenaLoginActivity);
    }

    @Override
    public void onClick(View v) {

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
