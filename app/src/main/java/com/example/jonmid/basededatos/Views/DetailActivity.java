package com.example.jonmid.basededatos.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jonmid.basededatos.R;

public class DetailActivity extends AppCompatActivity {

    TextView textViewName, textViewPhone, textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewName = (TextView) findViewById(R.id.id_tv_detail_name);
        textViewPhone = (TextView) findViewById(R.id.id_tv_detail_phone);
        textViewEmail = (TextView) findViewById(R.id.id_tv_detail_email);

        textViewName.setText( getIntent().getExtras().getString("name") );
        textViewPhone.setText( getIntent().getExtras().getString("phone") );
        textViewEmail.setText( getIntent().getExtras().getString("email") );
    }
}
