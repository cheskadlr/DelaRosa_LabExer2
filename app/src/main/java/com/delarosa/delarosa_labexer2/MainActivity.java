package com.delarosa.delarosa_labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnSharedPrefs, btnIntStorage, btnNext;
    SharedPreferences preferences;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnSharedPrefs = (Button) findViewById(R.id.btnSharedPrefs);
        btnIntStorage = (Button) findViewById(R.id.btnIntStorage);
        btnNext = (Button) findViewById(R.id.btnNext);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public void saveSharedPrefs(View view) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",etUsername.getText().toString());
        editor.putString("pwd", etPassword.getText().toString());
        editor.commit();

        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }

    public void saveIntStorage(View view){
        String username = "Username is " + etUsername.getText().toString() + "\n";
        String pwd = "Password is " + etPassword.getText().toString();

        try{
            fos = openFileOutput("output.txt", Context.MODE_PRIVATE);
            fos.write(username.getBytes());
            fos.write(pwd.getBytes());
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        Toast.makeText(this, "Saved in Internal Storage!", Toast.LENGTH_LONG).show();
    }

    public void goToDisplayActivity(View view){
        Intent intent = new Intent(this,DisplayActivity.class);
        startActivity(intent);
    }
}
