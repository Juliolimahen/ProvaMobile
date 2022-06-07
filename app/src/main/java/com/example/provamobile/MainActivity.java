package com.example.provamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Intent i;
    private Button btnAvançar;
    private EditText apelido, local, area, aluguel, comprar;
    private Imovel imovel=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bootstrap();
        //limparCampos();

        Intent it = getIntent();
        if(it.hasExtra("imovel")){

            imovel = (Imovel) it.getSerializableExtra("imovel");
            apelido.setText(imovel.getApelido());
            local.setText(imovel.getLocal());
            area.setText(imovel.getArea());
            aluguel.setText(imovel.getAluguel());
            comprar.setText(imovel.getComprar());
        }
    }

    public void limparCampos(){

        apelido.setText("");
        local.setText("");
        area.setText("");
        aluguel.setText("");
        comprar.setText("");

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

        if(imovel != null){
            params.putString("atualizar", "UPD");
            params.putString("updQueroAlugar", imovel.getQueroAlugar());
            params.putString("updQueroComprar", imovel.getQueroComprar());
            params.putInt("id", imovel.getIdImovel());
        }

        i.putExtras(params);
        startActivity(i);
    }

}