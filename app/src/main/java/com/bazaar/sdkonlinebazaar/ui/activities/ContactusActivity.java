package com.bazaar.sdkonlinebazaar.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bazaar.sdkonlinebazaar.R;

public class ContactusActivity extends AppCompatActivity {


    private EditText textPersonName,subject,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


        textPersonName = findViewById(R.id.textPersonName);
        subject = findViewById(R.id.subject);
        message = findViewById(R.id.message);
        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"sdkonlinebazaar@gmail.com"};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("sdkonlinebazaar@gmail.com"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, ""+textPersonName.getText()+": "+subject.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT, ""+message.getText());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ContactusActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}