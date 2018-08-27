package br.pro.adalto.appcompras_m171;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
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

        EditText etNomeCategoria = new EditText(this);
        etNomeCategoria.setHint( R.string.hintCategoria );
        etNomeCategoria.setTextColor(Color.BLUE);

        alerta.setView( etNomeCategoria );

        alerta.setNeutralButton(
                getResources().getString(R.string.txtCancelar) ,
                null );
        alerta.setPositiveButton(
                getResources().getString(R.string.txtSalvar) ,
                null);
        alerta.show();

    }


}
