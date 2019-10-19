package com.example.thinkdifferent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityLogin extends AppCompatActivity {


    private Button login,createac;
    private static ProgressBar progressBar;
    private EditText email,pass;

    public static void  setProgressBarInVisible() {
        progressBar.setVisibility(View.INVISIBLE);
    }
    public static void setProgressBarVisible() {

        progressBar.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        login=findViewById(R.id.btnLogInLogin);
        progressBar=findViewById(R.id.progressBarLogin);
        email=findViewById(R.id.etEmailLogin);
        pass=findViewById(R.id.etPasswordLogin);

        setProgressBarInVisible();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setProgressBarVisible();
                String UEmail=email.getText().toString();
                final String UPass=pass.getText().toString();

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(UEmail).matches() || UEmail.isEmpty())
                {
                    email.setError("Enter a valid email address");
                    email.requestFocus();
                    setProgressBarInVisible();
                    return;
                }
                if(UPass.isEmpty() || UPass.length()<8){

                    pass.setError("Password Length should be at least 8 ");
                    pass.requestFocus();
                    setProgressBarInVisible();
                    return;

                }
                ActivityLogin.RegUser(UEmail,UPass,ActivityLogin.this);
            }
        });




    }


    public  static  void RegUser(String UEmail, String UPass, final Context context){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(UEmail, UPass)
                .addOnCompleteListener( context.getMainExecutor(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            setProgressBarInVisible();
                            Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show();

                            Intent a = new Intent( context ,BlankPage.class);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(a);

                        } else {
                            setProgressBarInVisible();
                            Toast.makeText(context ,task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }


                });

    }


}
