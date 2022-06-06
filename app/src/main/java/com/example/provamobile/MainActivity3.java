package com.example.provamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {

    Imovel imovel;
    ImovelDAO imovelDao;
    Intent i;
    Bundle params;
    Button btnAvançar;
    String apelido, local, area, aluguel, comprar, queroAlugar, queroComprar;

    EditText edtPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bootstrap();
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
        }
    }

    private void salvar() {
        if (imovel == null) {

            imovel = new Imovel(apelido, local, area, aluguel, comprar, queroAlugar, queroComprar);
            imovelDao = new ImovelDAO(this);

            imovelDao.inserir(imovel);

        } else {
            imovel.setApelido(apelido);
            imovel.setLocal(local);
            imovel.setArea(area);
            imovel.setAluguel(aluguel);
            imovel.setComprar(comprar);
            imovel.setQueroAlugar(queroAlugar);
            imovel.setQueroComprar(queroComprar);
            imovelDao.atualizar(imovel);
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
        param.putString("pesquisa", pesq);

        i.putExtras(param);
        startActivity(i);
    }
}