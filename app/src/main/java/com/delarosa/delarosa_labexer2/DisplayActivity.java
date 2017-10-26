package com.delarosa.delarosa_labexer2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.R.attr.password;

public class DisplayActivity extends AppCompatActivity {
    Button btnLoadSharedPrefs, btnLoadIntStorage, btnClear, btnBack;
    TextView tvDisplay;
    FileInputStream fis;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        btnLoadSharedPrefs = (Button) findViewById(R.id.btnSharedPrefs);
        btnLoadIntStorage = (Button) findViewById(R.id.btnLoadIntStorage);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnBack = (Button) findViewById(R.id.btnBack);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void loadSharedPrefs(View view){
        String user = preferences.getString("user", "");
        String pwd = preferences.getString("pwd", "");

        tvDisplay.setText("Username is " + user + "\n Password is " + pwd);
    }

    public void loadIntStorage(View view){
        StringBuffer buffer = new StringBuffer();
        int read = 0;

        try{
            fis = openFileInput("output.txt");
            while((read = fis.read()) != -1){
                buffer.append((char)read);
            }

            fis.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        tvDisplay.setText(buffer.toString());
    }

    public void clearDisplay(View view){
        tvDisplay.setText("");
    }

    public void goBackToMain(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
