package com.example.jonmid.basededatos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jonmid.basededatos.Connection.Sqlite;
import com.example.jonmid.basededatos.Utilities.Utilities;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText textInputEditTextName;
    TextInputEditText textInputEditTextPhone;
    TextInputEditText textInputEditTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.id_tv_name);
        textInputEditTextPhone = (TextInputEditText) findViewById(R.id.id_tv_phone);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.id_tv_email);


    }

    public void onClickCreateUser(View view){
        createUser();
    }

    public void createUser(){
        Sqlite sqlite = new Sqlite(this, "bd_users", null, 1);
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilities.TABLA_FIELD_NAME, textInputEditTextName.getText().toString());
        values.put(Utilities.TABLA_FIELD_PHONE, textInputEditTextPhone.getText().toString());
        values.put(Utilities.TABLA_FIELD_EMAIL, textInputEditTextEmail.getText().toString());

        // (nombre de la tabla, campo que va retornar, valores a guardar)
        Long idResult = db.insert(Utilities.TABLA_NAME, Utilities.TABLA_FIELD_ID, values);

        Toast.makeText(this, "Id registro: "+idResult, Toast.LENGTH_SHORT).show();
    }
}
