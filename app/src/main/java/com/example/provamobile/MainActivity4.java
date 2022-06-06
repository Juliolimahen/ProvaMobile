package com.example.provamobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {


    private ListView lstImoveis;
    private ImovelDAO imovelDao;
    private List<Imovel> imoveis;
    private List<Imovel> imoveisComFiltro = new ArrayList<>();
    Intent i;
    Bundle params;
    TextView txtRes;
    String pesquisar = "todos";

    Button btnNovoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Bootstrap();
        Listar();

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procuraImovel(s);
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procuraImovel(String nome) {
        imoveisComFiltro.clear();
        for (Imovel i : imoveis) {
            //Coparar se tem clietes Com filtro na lista de clientes
            if (i.getApelido().toLowerCase().contains(nome.toLowerCase())) {
                imoveisComFiltro.add(i);
            }
        }
        lstImoveis.invalidateViews();
    }

    public void excluir(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Imovel imovelExcluir = imoveisComFiltro.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir o imovel")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        imoveisComFiltro.remove(imovelExcluir);
                        imoveis.remove(imovelExcluir);
                        imovelDao.deletar(imovelExcluir);
                        lstImoveis.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Imovel imovelAtualizar = imoveisComFiltro.get(menuInfo.position);

        Intent it = new Intent(this, MainActivity.class);
        it.putExtra("imovel", String.valueOf(imovelAtualizar));
        startActivity(it);
    }

    private void Bootstrap() {
        lstImoveis = findViewById(R.id.lstImoveis);
        btnNovoCadastro = findViewById(R.id.btnNovoCadastro);
        txtRes = findViewById(R.id.txtRes);

        i = getIntent();
        params = i.getExtras();

        if (params != null) {
            pesquisar = params.getString("pesquisa");
        }

        txtRes.setText(pesquisar);
    }

    private void Listar() {
        imovelDao = new ImovelDAO(this);
        imoveis = imovelDao.obterImoveis(pesquisar);

        //gambiarra clientes com filtro
        imoveisComFiltro.addAll(imoveis);

        ArrayAdapter<Imovel> adaptador = new ArrayAdapter<Imovel>(this, android.R.layout.simple_list_item_1, imoveisComFiltro);
        lstImoveis.setAdapter(adaptador);
        //Registrar menu, vincular com a listview
        registerForContextMenu(lstImoveis);

    }

    public void OnClickNovoCadastro(View v) {
        i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}