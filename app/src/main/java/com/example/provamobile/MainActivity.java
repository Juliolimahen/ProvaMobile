package com.example.provamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Intent i;
    Button btnAvançar;
    EditText apelido, local, area, aluguel, comprar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bootstrap();
    }

    private void Bootstrap(){
        btnAvançar = findViewById(R.id.btnAvancarTela1);
        apelido = findViewById(R.id.edtApelido);
        local = findViewById(R.id.edtLocal);
        area = findViewById(R.id.edtArea);
        aluguel = findViewById(R.id.edtAluguel);
        comprar = findViewById(R.id.edtComprar);
    }


    public void OnClickAvancar(View v){
        i = new Intent(this, MainActivity2.class);
        Bundle params = new Bundle();
        params.putString("apelido", apelido.getText().toString());
        params.putString("local", local.getText().toString());
        params.putString("area", area.getText().toString());
        params.putString("aluguel", aluguel.getText().toString());
        params.putString("comprar", comprar.getText().toString());

        i.putExtras(params);
        startActivity(i);
    }


}