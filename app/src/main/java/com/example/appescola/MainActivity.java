package com.example.appescola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_cadastro = (Button)findViewById(R.id.btn_cadastro);
        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, InsereAlunoActivity.class);
                startActivity(it);
                //Toast.makeText(getApplicationContext(),  "Busque sua localização", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
