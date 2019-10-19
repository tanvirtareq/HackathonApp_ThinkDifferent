package com.example.thinkdifferent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private Button login, createac, admin;

    private ProgressBar progressBar;
    private EditText email, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        login = findViewById(R.id.btnLoginasStMain);
        admin = findViewById(R.id.btnADMINMain);
        createac = findViewById(R.id.btnSignupMain);
        createac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(MainActivity.this, ActivitySignUp.class);
                startActivity(a);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(MainActivity.this, ActivityLogin.class);
                startActivity(a);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(MainActivity.this, ActivityAdminLogin.class);
                startActivity(a);

            }
        });

    }



}
