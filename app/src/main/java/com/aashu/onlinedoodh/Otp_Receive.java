package com.aashu.onlinedoodh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Otp_Receive extends AppCompatActivity {
    EditText phone_edt_text2;
    Button OTP_btn2;
    String Phonenumber;
    private FirebaseAuth mAuth;
    String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_receive);
        mAuth = FirebaseAuth.getInstance();

        phone_edt_text2 = (EditText) findViewById(R.id.phone_edt_text2);
        OTP_btn2 = (Button) findViewById(R.id.OTP_btn2);

        Phonenumber = getIntent().getStringExtra("number");
        sendverificationcode(Phonenumber);

         OTP_btn2.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (phone_edt_text2.getText().toString().isEmpty())
                     Toast.makeText(getApplicationContext(),"This Field Can not be Empty",Toast.LENGTH_LONG).show();
                 else if (phone_edt_text2.getText().toString().length()!=6)
                     Toast.makeText(getApplicationContext(),"Invalid Otp",Toast.LENGTH_LONG).show();
                 else{
                     PhoneAuthCredential credential  = PhoneAuthProvider.getCredential(verificationId,phone_edt_text2.getText().toString());
                     signInWithPhoneAuthCredential(credential);
                 }
             }
         });



    }

    private void sendverificationcode(String phonenumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phonenumber)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential);

        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(Otp_Receive.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(Otp_Receive.this,Dashboard_Activity.class));
                        } else {

                            Toast.makeText(getApplicationContext(),"error ",Toast.LENGTH_LONG).show();



                        }
                    }
                });
    }
}
