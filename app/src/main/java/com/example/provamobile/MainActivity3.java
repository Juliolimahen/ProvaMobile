package com.example.provamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    private Imovel imovel;
    private ImovelDAO imovelDao;
    private Intent i;
    private Bundle params;
    private Button btnAvançar;
    private String apelido, local, area, aluguel, comprar, queroAlugar, queroComprar, salvar = "NOT", atualizar = "NOT";
    private Integer id = 0;
    private EditText edtPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        id = 1;
        Bootstrap();

        Intent it = getIntent();

        if(it.hasExtra("att")){
            atualizar = params.getString("att");
            id = params.getInt("id");
        }
        salvar();
    }


    private void Bootstrap() {
        btnAvançar = findViewById(R.id.btnAvancarTela3);
        edtPesquisar = findViewById(R.id.edtPesquisar);
        //Pegando parametros tela 2
        i = getIntent();

        params = i.getExtras();

        if (params != null) {
            apelido = params.getString("apelido");
            local = params.getString("local");
            area = params.getString("area");
            aluguel = params.getString("aluguel");
            comprar = params.getString("comprar");
            queroAlugar = params.getString("queroAlugar");
            queroComprar = params.getString("queroComprar");
            salvar = params.getString("salvar");
        }
    }

    private void salvar() {
        if(salvar.toUpperCase().equalsIgnoreCase("OK")) {
            if (atualizar.toUpperCase().equalsIgnoreCase("UPD")) {
                imovel = new Imovel(apelido, local, area, aluguel, comprar, queroAlugar, queroComprar);
                imovelDao = new ImovelDAO(this);
                imovelDao.atualizar(imovel, id);

            } else {
                imovel = new Imovel(apelido, local, area, aluguel, comprar, queroAlugar, queroComprar);
                imovelDao = new ImovelDAO(this);

                imovelDao.inserir(imovel);
            }
        }
    }

    public void OnClickAvancar(View v) {
        String pesq = edtPesquisar.getText().toString();

        if (pesq.toUpperCase().equalsIgnoreCase("PESQUISAR") || pesq.trim().equalsIgnoreCase("")
                || pesq.equalsIgnoreCase(null) || pesq.toUpperCase().equalsIgnoreCase("TODOS")) {
            pesq = "todos";
        } else if (pesq.toUpperCase().equalsIgnoreCase("ALUGAR")) {
            pesq = "alugar";
        } else if (pesq.toUpperCase().equalsIgnoreCase("COMPRAR")) {
            pesq = "comprar";
        }


        i = new Intent(this, MainActivity4.class);
        Bundle param = new Bundle();
        param.putString("pesquisa", pesq.toUpperCase());

        i.putExtras(param);
        startActivity(i);
    }
}