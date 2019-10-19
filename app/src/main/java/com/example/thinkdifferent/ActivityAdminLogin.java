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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ActivityAdminLogin extends AppCompatActivity  {


    private Button button;
    private EditText email,pass;
    private  static  ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_dialogue);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        button=findViewById(R.id.btnAdminLoginDial);
        email=findViewById(R.id.emailAdminLoginDial);
        pass=findViewById(R.id.passAdminLoginDial);
         progressBar=findViewById(R.id.progressBarAdminLoginDialogue);

         progressBar.setVisibility(View.INVISIBLE);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches() || email.getText().toString().isEmpty())
                {
                    email.setError("Enter a valid email address");
                    email.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                applyTexts(email.getText().toString(),pass.getText().toString(),ActivityAdminLogin.this);
            }
        });
    }

    public static void  applyTexts(final String Email, final String Pass, final Context context) {


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ADMINISTRATOR");
        Query applesQuery = ref.orderByKey();

        String x=  ref.getDatabase().toString();
        System.out.println("DEBUG"+x);

        applesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String,String > admin= (Map<String, String>) dataSnapshot.getValue();

                if(admin.get("email").trim().equals(Email.trim()) &&
                        admin.get("UniqueCode").trim().equals(Pass.trim().toString()))
                {
                    startAct(context);
                    progressBar.setVisibility(View.VISIBLE);

                }
                else{
                    Toast.makeText( context,"Give Correct Admin Credentials",Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);

                }
                System.out.println("DEBUG"+admin.values().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public   static void  startAct(Context context){

        Intent a = new Intent( context,BlankPage.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(a);
    }
}
