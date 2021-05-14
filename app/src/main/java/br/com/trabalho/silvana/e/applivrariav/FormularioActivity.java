package br.com.trabalho.silvana.e.applivrariav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etAutor;
    private Spinner spAno;
    private Button btnSalvar;
    private String acao;
    private LivrariaV livrariaV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etAutor = findViewById(R.id.etAutor);
        spAno = findViewById(R.id.spAno);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")) {
            carregarFormulario();
        }
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
    }

    private void carregarFormulario() {
        int idLivrariaV = getIntent().getIntExtra("idLivrariaV", 0);
        if (idLivrariaV != 0) {
            livrariaV = LivrariaVDAO.getLivrariaVBiId(this, idLivrariaV);
            etNome.setText(livrariaV.nome);
            etAutor.setText(livrariaV.autor);

            String[] arrayAno = getResources().getStringArray(R.array.arrayAno);
            for (int i = 0; i < arrayAno.length; i++) {
                if (Integer.valueOf(arrayAno[i]) == livrariaV.getAno()) {
                    spAno.setSelection(i);
                }
            }
        }
    }

    private void salvar() {
        if (spAno.getSelectedItemPosition() == 0 || etNome.getText().toString().isEmpty()) {
            Toast.makeText(this, "Todos campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
        } else {

            if (acao.equals("novo")) {
                livrariaV = new LivrariaV();
            }
            livrariaV.nome = etNome.getText().toString();
            livrariaV.autor = etAutor.getText().toString();
            livrariaV.setAno((Integer.valueOf(spAno.getSelectedItem().toString())));
            if (acao.equals("editar")) {
                LivrariaVDAO.editar(livrariaV, this);
                finish();
            } else {
                LivrariaVDAO.inserir(livrariaV, this);
                etNome.setText("");
                etAutor.setText("");
                spAno.setSelection(0);
            }

        }
    }
}