package br.com.trabalho.silvana.e.applivrariav;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.Normalizer;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvLivrariaV;
    private AdapterLivrariaV adapter;
    private List<LivrariaV> listaLivrariaV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "novo");
                startActivity(intent);

            }
        });
        lvLivrariaV = findViewById(R.id.lvLivrariaV);

        carregarLivrariaV();

        configurarListView();

    }//fecha onCreate

    private void configurarListView() {

        lvLivrariaV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LivrariaV livrariaVSelecionado = listaLivrariaV.get(position);

                Intent intent = new Intent(MainActivity.this, FormularioActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idLivrariaV", livrariaVSelecionado.id);
                startActivity(intent);

            }
        });

        lvLivrariaV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                LivrariaV livrariaVSelecionado = listaLivrariaV.get(position);
                excluirLivrariaV(livrariaVSelecionado);

                return true;
            }
        });

    }

    private void excluirLivrariaV(LivrariaV  livrariaV) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setIcon(android.R.drawable.ic_input_delete);
        alerta.setTitle("Atenção");
        alerta.setMessage("Confirma a exclusão do livro " + livrariaV.nome + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                LivrariaVDAO.excluir(livrariaV.id, MainActivity.this);
                carregarLivrariaV();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        carregarLivrariaV();
    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    private void carregarLivrariaV() {

        listaLivrariaV = LivrariaVDAO.getLivrariaV(this);
        adapter = new AdapterLivrariaV(this, listaLivrariaV);
        lvLivrariaV.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
