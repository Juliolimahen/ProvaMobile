package com.example.provamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private Intent i;
    private Bundle params;
    private Button btnAvançar;
    private CheckBox chbAlugar, chbComprar;
    private String checkAlugar = "nao", checkComprar = "nao";
    private String apelido, local, area, aluguel, comprar;
    private String atualizar = "NOT", updAlugar="NOT", updComprar="NOT";
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bootstrap();

        Intent it = getIntent();
        if(it.hasExtra("atualizar")){
            atualizar = params.getString("atualizar");
            updAlugar = params.getString("updQueroAlugar");
            updComprar = params.getString("updQueroComprar");
            id = params.getInt("id");
        }

        if(atualizar.toUpperCase().equalsIgnoreCase("UPD")){
            if(updAlugar.toUpperCase().equalsIgnoreCase("SIM")){
                chbAlugar.setChecked(true);
            }
            if(updComprar.toUpperCase().equalsIgnoreCase("SIM")){
                chbComprar.setChecked(true);
            }
        }
    }
    private void Bootstrap(){
        btnAvançar = findViewById(R.id.btnAvancarTela2);
        chbAlugar = findViewById(R.id.chbAlugar);
        chbComprar = findViewById(R.id.chbComprar);


        //Pegando parametros tela 1
        i = getIntent();

        params = i.getExtras();

        if(params!=null) {
            apelido = params.getString("apelido");
            local = params.getString("local");
            area = params.getString("area");
            aluguel = params.getString("aluguel");
            comprar = params.getString("comprar");
        }
    }

    public void CheckBoxClick(View v){
        boolean checked = ((CheckBox) v).isChecked();

        switch(v.getId()) {
            case R.id.chbAlugar:
                if (checked) {
                    checkAlugar = "sim";
                }else{
                    checkAlugar = "nao";
                }
                break;
            case R.id.chbComprar:
                if (checked) {
                    checkComprar = "sim";
                }else{
                    checkComprar = "nao";
                }
                break;
        }
    }

    public void OnClickAvancar(View v){
        i = new Intent(this, MainActivity3.class);
        Bundle params = new Bundle();
        //anteriores
        params.putString("apelido", apelido);
        params.putString("local", local);
        params.putString("area", area);
        params.putString("aluguel", aluguel);
        params.putString("comprar", comprar);

        //atuais
        params.putString("queroAlugar", checkAlugar);
        params.putString("queroComprar", checkComprar);
        params.putString("salvar", "OK");

        if(atualizar.toUpperCase().equalsIgnoreCase("UPD")){
            params.putString("att", "UPD");
            params.putInt("id", id);
        }


        i.putExtras(params);
        startActivity(i);
    }
}