package org.geeksv.crudhipoteca;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HipotecaFormulario extends Activity {
	
	private HipotecaDbAdapter dbAdapter;
	private Cursor cursor;
	
	private int modo;
	private long id;
	
	 private EditText nombre;
	 private EditText condiciones;
	 private EditText contacto;
	 private EditText telefono;
	 private EditText email;
	 private EditText observaciones;
	 
	 private Button boton_guardar;
	 private Button boton_cancelar;
	 
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hipoteca_formulario);

		Intent intent= getIntent();
		Bundle extra= intent.getExtras();
		if(extra == null) 
			return;
		
		nombre =  (EditText) findViewById(R.id.nombre);
		condiciones = (EditText) findViewById(R.id.condiciones);
		contacto= (EditText) findViewById(R.id.contacto);
		telefono= (EditText) findViewById(R.id.telefono);
		email= (EditText) findViewById(R.id.email);
		observaciones= (EditText) findViewById(R.id.observaciones);
		
		boton_guardar= (Button) findViewById(R.id.boton_guardar);
		boton_cancelar= (Button) findViewById(R.id.boton_cancelar);
		dbAdapter= new HipotecaDbAdapter(this);
		dbAdapter.abrir();
		
		if(extra.containsKey(HipotecaDbAdapter.C_COLUMNA_ID));
		{
			id=extra.getLong(HipotecaDbAdapter.C_COLUMNA_ID);
			consultar(id);
		}
		
		establecerModo(extra.getInt(MainActivity.C_MODO));

boton_guardar.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		guardar();
		
	}
});

boton_cancelar.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		cancelar();
	}
});
		
	}
	
	//acciones para los botones
	
	private void establecerModo(int m){
		this.modo = m;
		if(modo==MainActivity.C_VISUALIZAR);
		{
			this.setTitle(nombre.getText().toString());
			this.setEdicion(false);
		}
		 if (modo==MainActivity.C_CREAR) {
			this.setTitle(R.string.hipoteca_crear_titulo);
			this.setEdicion(true);
		}
	}

	private void consultar(long id){
		cursor= dbAdapter.getRegistro(id);
		nombre.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_NOMBRE)));
		condiciones.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_CONDICIONES)));
		contacto.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_CONTACTO)));
		telefono.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_TELEFONO)));
		email.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_EMAIL)));
		observaciones.setText(cursor.getString(cursor.getColumnIndex(HipotecaDbAdapter.C_COLUMNA_OBSERVACIONES)));
	}
	
	private void setEdicion (boolean opcion){
		nombre.setEnabled(opcion);
		condiciones.setEnabled(opcion);
		contacto.setEnabled(opcion);
		telefono.setEnabled(opcion);
		email.setEnabled(opcion);
		observaciones.setEnabled(opcion);
	}
	
	 private void guardar()
	   {
	      //
	      // Obtenemos los datos del formulario
	      //
	      ContentValues reg = new ContentValues();
	       
	      reg.put(HipotecaDbAdapter.C_COLUMNA_NOMBRE, nombre.getText().toString());
	      reg.put(HipotecaDbAdapter.C_COLUMNA_CONDICIONES, condiciones.getText().toString());
	      reg.put(HipotecaDbAdapter.C_COLUMNA_CONTACTO, contacto.getText().toString());
	      reg.put(HipotecaDbAdapter.C_COLUMNA_TELEFONO, telefono.getText().toString());
	      reg.put(HipotecaDbAdapter.C_COLUMNA_EMAIL, email.getText().toString());
	      reg.put(HipotecaDbAdapter.C_COLUMNA_OBSERVACIONES, observaciones.getText().toString());
	       
	      if (modo == MainActivity.C_CREAR)
	      {
	         dbAdapter.insert(reg);
	         Toast.makeText(HipotecaFormulario.this, R.string.hipoteca_crear_confirmacion, Toast.LENGTH_SHORT).show();
	      }    
	       
	      //
	      // Devolvemos el control
	      //
	      setResult(RESULT_OK);
	      finish();
	   }
	    
	   private void cancelar()
	   {
	      setResult(RESULT_CANCELED, null);
	      finish();
	   }

	


}
