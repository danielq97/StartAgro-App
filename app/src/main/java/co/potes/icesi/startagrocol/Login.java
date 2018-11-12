package co.potes.icesi.startagrocol;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {


    public final static int SIGN_IN_CODE = 777;

    private EditText et_email;
    private EditText et_pass;
    private Button btn_login;
    private Button btn_registro;
    private Button btn_recuperar;


    private SignInButton btn_login_google;
    private GoogleApiClient googleApiClient;

    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        btn_login_google = findViewById(R.id.btn_login_google);
        btn_login_google.setOnClickListener(this);

        et_email = findViewById(R.id.et_email);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_registro = findViewById(R.id.btn_registro);
        btn_recuperar = findViewById(R.id.btn_recuperarContrasenia);
        btn_recuperar.setOnClickListener(this);
        btn_registro.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SIGN_IN_CODE){
            GoogleSignInResult resultado = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(resultado);
        }
    }

    private void handleSignInResult(GoogleSignInResult resultado) {

        if(resultado.isSuccess()){
            FirebaseAuthAuthWithGoogle(resultado.getSignInAccount());
        }
        else{
            Toast.makeText(Login.this, "Login con google fallido", Toast.LENGTH_SHORT).show();

        }
    }

    private void FirebaseAuthAuthWithGoogle(GoogleSignInAccount signInAccount) {

        AuthCredential credencial = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
        auth.signInWithCredential(credencial).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(Login.this, "Login con google fallido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btn_login)){

            loginUsuario(et_email.getText().toString(), et_pass.getText().toString());
            Intent i = new Intent(this.getApplicationContext(), Proyectos.class);
            startActivity(i);
        }
        else if(v.equals(btn_registro)){

            Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        else if(v.equals(btn_recuperar)){
            Intent i = new Intent(this.getApplicationContext(), RestaurarContrasenia.class);
            startActivity(i);
        }

        else if(v.equals(btn_login_google)){

            FirebaseUser usuario = auth.getCurrentUser();
            Intent i = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
            usuario = auth.getCurrentUser();
            if(usuario!=null){
                Toast.makeText(Login.this, "Bienvenido "+usuario.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
            startActivityForResult(i,SIGN_IN_CODE);
            Intent o = new Intent(this.getApplicationContext(), Proyectos.class);
            startActivity(o);
        }

    }

    private void loginUsuario(String email, String pass) {

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, task.getException().toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener!=null){
            auth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
