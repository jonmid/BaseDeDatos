package com.example.jonmid.basededatos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jonmid.basededatos.Connection.Sqlite;
import com.example.jonmid.basededatos.Utilities.Utilities;
import com.example.jonmid.basededatos.Views.DetailActivity;

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

    public void onClickSearchUser(View view){
        searchUser();
    }

    public void searchUser(){
        Sqlite sqlite = new Sqlite(this, "bd_users", null, 1);
        SQLiteDatabase db = sqlite.getReadableDatabase();

        String[] params = {textInputEditTextName.getText().toString()};
        String[] fields = {Utilities.TABLA_FIELD_NAME,Utilities.TABLA_FIELD_PHONE,Utilities.TABLA_FIELD_EMAIL};


        try {
            Cursor cursor = db.query(Utilities.TABLA_NAME, fields, Utilities.TABLA_FIELD_NAME+"=?",params,null,null,null);
            cursor.moveToFirst();

            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("name", cursor.getString(0));
            intent.putExtra("phone", cursor.getString(1));
            intent.putExtra("email", cursor.getString(2));
            startActivity(intent);

            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, "Nombre del usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
