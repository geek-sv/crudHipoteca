package org.geeksv.crudhipoteca;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


public class MainActivity extends ListActivity {
	public static final String C_MODO ="modo";
	public static final int C_VISUALIZAR=551;
	public static final int C_CREAR=552;
	private HipotecaDbAdapter dbAdapter;
	private Cursor cursor;
	private HipotecaCursorAdapter hipotecaAdapter;
	private ListView lista;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		lista=(ListView) findViewById(android.R.id.list);
		
		dbAdapter = new HipotecaDbAdapter(this);
		dbAdapter.abrir();
		
		consultar();
		
		
		
	}
	
	private void consultar(){
		cursor= dbAdapter.getCursor();
		startManagingCursor(cursor);
		hipotecaAdapter= new HipotecaCursorAdapter(this, cursor);
		lista.setAdapter(hipotecaAdapter);
	}
	
	public boolean onCreateOptionMenu(Menu menu){
	getMenuInflater().inflate(R.menu.main, menu);
	return super.onCreateOptionsMenu(menu);
	}

	private void visualizar (long id){
		Intent i = new Intent(this, HipotecaFormulario.class);
		i.putExtra(C_MODO,C_VISUALIZAR);
		i.putExtra(HipotecaDbAdapter.C_COLUMNA_ID,id);
	
		startActivityForResult(i, C_VISUALIZAR);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		visualizar(id);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		Intent i;
		switch (item.getItemId()) {
			case R.id.menu_crear:
					i= new Intent(MainActivity.this,HipotecaFormulario.class);
					i.putExtra(C_MODO, C_CREAR);
					startActivityForResult(i, C_CREAR);
					return true;
		
		}
		return super.onMenuItemSelected(featureId, item);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	// TODO Auto-generated method stub
	 switch (requestCode) {
	 	case C_CREAR:
	 		if(resultCode== RESULT_OK)
	 			consultar();

	 	default:
	 		super.onActivityResult(requestCode, resultCode, data);
	 }
	}
	

}
