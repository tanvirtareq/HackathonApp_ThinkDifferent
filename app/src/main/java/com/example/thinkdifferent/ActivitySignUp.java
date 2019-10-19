package com.example.thinkdifferent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class ActivitySignUp extends AppCompatActivity {


    private Button button;
    private EditText email,pass;
    static ProgressBar progressBar;
    private FirebaseAuth mAuth;

    public static  void  setProgressBarInVisible() {
        progressBar.setVisibility(View.INVISIBLE);
    }
    public static void setProgressBarVisible() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        mAuth=FirebaseAuth.getInstance();
        button=findViewById(R.id.btnLogInSignUp);
        email=findViewById(R.id.etEmailSignUp);
        pass=findViewById(R.id.etPasswordSignUp);
        progressBar=findViewById(R.id.progressBarSignUp);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setProgressBarInVisible();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setProgressBarVisible();
                String UEmail=email.getText().toString();
                String UPass=pass.getText().toString();

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

                ActivitySignUp.doRegister(UEmail,UPass,ActivitySignUp.this);
            }
        });
    }

    private  static void   doRegister(final String UEmail, String UPass, final Context context){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(UEmail,UPass)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){


                            setProgressBarInVisible();;

                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show();

                            String ID=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                            UserProfileClass U=new UserProfileClass(UEmail,FirebaseAuth.getInstance().getUid());
                            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference()
                                    .child("user").child(ID);
                            mDatabase.setValue(U);
                            FirebaseAuth.getInstance().signOut();

                            Intent a = new Intent( context ,MainActivity.class);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(a);
                        }
                        else{

                            setProgressBarInVisible();;
                            Toast.makeText( context , task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
/*

 */