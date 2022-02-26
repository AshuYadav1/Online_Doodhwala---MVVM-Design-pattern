package com.aashu.onlinedoodh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class Introslider extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText phone_edt_text;
    Button OTP_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introslider);

        phone_edt_text = (EditText)findViewById(R.id.phone_edt_text);
        OTP_btn = (Button) findViewById(R.id.OTP_btn);
        ccp = (CountryCodePicker)findViewById(R.id.ccp);

        ccp.registerCarrierNumberEditText(phone_edt_text);

        OTP_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Introslider.this,Otp_Receive.class);
                intent.putExtra("number",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });



    }
}