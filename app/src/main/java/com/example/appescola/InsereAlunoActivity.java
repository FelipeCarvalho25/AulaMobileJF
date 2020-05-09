package com.example.appescola;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsereAlunoActivity extends AppCompatActivity {
    EditText nome;
    EditText idade;
    EditText curso;
    SQLiteDatabase bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_cadastro);

        nome = findViewById(R.id.TextAluno);
        idade = findViewById(R.id.textIdade);
        curso = findViewById(R.id.TextCurso);
        criarBancoDados();
    }

    public void validaDados (View v) {
        int age = 0;
        boolean insere = true;

        if ( nome.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error: O nome não pode estar vazio", Toast.LENGTH_LONG).show();
            insere = false;
        }

        if ( idade.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "Error: A idade não pode ser vazio", Toast.LENGTH_LONG).show();
            insere = false;
        }
        else {
            age = Integer.parseInt(idade.getText().toString().trim());
            if (age < 0) {
                Toast.makeText(getApplicationContext(), "Error: A idade não pode ser negativa", Toast.LENGTH_LONG).show();
                insere = false;
            }
        }
        if (curso.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Error: O nome não pode estar vazio", Toast.LENGTH_LONG).show();
            insere = false;
        }
        if ( insere ) {
            gravaResultado(nome.toString().trim(), age, curso.toString().trim());
        }
    }

    public void jumpHome(View v) {
        Intent it = new Intent(InsereAlunoActivity.this, MainActivity.class);
        startActivity(it);
    }
    private void gravaResultado (String nome, int idade, String curso) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Aluno(nome, idade, curso) VALUES (");
        sql.append("'" + nome + "',");
        sql.append(idade + ",");
        sql.append("'" + curso+"'");
        sql.append(")");

        try {
            //Toast.makeText(getApplicationContext(), sql.toString(), Toast.LENGTH_LONG).show();
            bd.execSQL(sql.toString());
            Toast.makeText(getApplicationContext(), "Aluno " + this.nome.getText().toString() + " gravado com sucesso", Toast.LENGTH_LONG).show();
            this.nome.getText().clear();
            this.idade.getText().clear();
            this.curso.getText().clear();

        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void criarBancoDados(){
        bd = openOrCreateDatabase("Escola.db", Context.MODE_PRIVATE, null);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS Aluno(");
        sql.append("id INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome VARCHAR(100), ");
        sql.append("idade INTEGER  , ");
        sql.append("curso VARCHAR(30) ");
        sql.append(")");

        try {
            bd.execSQL(sql.toString());
        } catch (SQLException ex) {
            Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

