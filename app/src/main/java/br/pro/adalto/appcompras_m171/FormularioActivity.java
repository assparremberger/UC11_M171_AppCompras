package br.pro.adalto.appcompras_m171;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import br.pro.adalto.appcompras_m171.dao.CategoriaDAO;
import br.pro.adalto.appcompras_m171.dao.ProdutoDAO;
import br.pro.adalto.appcompras_m171.model.Categoria;
import br.pro.adalto.appcompras_m171.model.Produto;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome, etQuantidade;
    private Button btnSalvar;
    private Spinner spCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = (EditText) findViewById(R.id.etNomeProduto);
        etQuantidade = (EditText) findViewById(R.id.etQauntidade);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        spCategoria = (Spinner) findViewById(R.id.spCategoria);

        carregarCategorias();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }


    private void salvar(){
        String nome = etNome.getText().toString();

        if ( nome.isEmpty() || spCategoria.getSelectedItemPosition() == 0){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle( getResources().getString(R.string.txtAtencao) );
            alerta.setMessage( R.string.txtCamposObrigatorios );
            alerta.setIcon( android.R.drawable.ic_dialog_alert );
            alerta.setNeutralButton("OK", null);
            alerta.show();
        }else {
            String quantidade = etQuantidade.getText().toString();
            if ( quantidade.isEmpty() )
                quantidade = "0";
            quantidade = quantidade.replace( "," , "." );
            double qtd = Double.valueOf( quantidade );

            Produto prod = new Produto();
            prod.setNome( nome );
            prod.setQuantidade( qtd );
            prod.setCategoria( (Categoria) spCategoria.getSelectedItem() );

            ProdutoDAO.inserir(this, prod );

            finish();

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if ( item.getItemId() == R.id.menu_nova_categoria ){
            cadastrarCategoria();
        }

        return super.onOptionsItemSelected(item);
    }

    private void cadastrarCategoria(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle( getResources().getString(R.string.txtNovaCategoria) );
        alerta.setIcon( android.R.drawable.ic_menu_edit );

        final EditText etNomeCategoria = new EditText(this);
        etNomeCategoria.setHint( R.string.hintCategoria );
        etNomeCategoria.setTextColor(Color.BLUE);

        alerta.setView( etNomeCategoria );

        alerta.setNeutralButton(
                getResources().getString(R.string.txtCancelar) ,
                null );
        alerta.setPositiveButton(
                getResources().getString(R.string.txtSalvar),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String nome = etNomeCategoria.getText().toString();
                        if ( ! nome.isEmpty() ){
                            Categoria cat = new Categoria();
                            cat.setNome( nome );
                            CategoriaDAO.inserir(FormularioActivity.this, cat);
                            carregarCategorias();
                        }
                    }
                });
        alerta.show();

    }


    private void carregarCategorias(){

        List<Categoria> lista = CategoriaDAO.getCategorias(this);
        Categoria fake = new Categoria();
        fake.setId( 0 );
        fake.setNome( getResources().getString(R.string.txtSelecione) );
        lista.add(0, fake);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, lista);
        spCategoria.setAdapter(adapter);

    }




}
