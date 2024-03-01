package com.example.messageapp;

import static android.Manifest.permission.SEND_SMS;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {
    final int My_PERMISSIONS_REQUEST_SEND_SMS = 0;

    EditText txt_phoneno,txt_message;
    Button btn_send;
    String Phone,Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_message = findViewById(R.id.txt_message);
        txt_phoneno = findViewById(R.id.txt_phone);
        btn_send = findViewById(R.id.btn_send);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSMSMessage();
            }

            private void sendSMSMessage() {
                Phone = txt_phoneno.getText().toString();
                Message = txt_message.getText().toString();
                try {
                    if (ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED){
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                                SEND_SMS)){
                        } else {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{SEND_SMS}, My_PERMISSIONS_REQUEST_SEND_SMS);
                        }
                    }
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(Phone, null, Message, null, null);
                    Toast.makeText(MainActivity.this, "sms sent",
                            Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "sms failed! please try again",
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }

            }
        });
    }
    public boolean onCreateOptionMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}