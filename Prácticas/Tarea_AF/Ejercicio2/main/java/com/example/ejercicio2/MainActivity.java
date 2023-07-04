package com.example.ejercicio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addImage = (Button) findViewById(R.id.send_email);
        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Mail m = new Mail("tonatiuhro@gmail.com", "");

                String[] toArr = {"tonatiuhro@gmail.com", "tonatiuhro@gmail.com"};
                m.setTo(toArr);
                m.setFrom("tonatiuhro@gmail.com");
                m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
                m.setBody("Email body.");

                Toast.makeText(MainActivity.this, "Mensaje enviado", Toast.LENGTH_SHORT).show();
                try {
                    sendEmail();
                    m.addAttachment("/storage/emulated/0/forensics");

                    if(m.send()) {
                        Toast.makeText(MainActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                    }
                } catch(Exception e) {
                    //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                    Log.e("MailApp", "Could not send email", e);
                }
            }
        });
    }

    protected void sendEmail() {
        Log.e("Test email:", "enviando email");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Te mando la info");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.e("Test email:", "Fin envio email");

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
