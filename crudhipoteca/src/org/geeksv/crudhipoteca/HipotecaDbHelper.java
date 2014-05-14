package org.geeksv.crudhipoteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HipotecaDbHelper extends SQLiteOpenHelper{
	
	private static int version =1;
	private static String name="HipotecaDb";
	private static CursorFactory factory= null;
	public HipotecaDbHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		Log.i(this.getClass().toString(),"Creando base de datos");
		
		db.execSQL("CREATE TABLE HIPOTECA(" +
				"_id INTEGER PRIMARY KEY,"+
				"hip_nombre TEXT NOT NULL,"+
				"hip_condiciones TEXT,"+
				"hip_contacto TEXT,"+
				"hip_email TEXT,"+
				"hip_telefono TEXT,"+
				"hip_observaciones TEXT)");
		db.execSQL("CREATE UNIQUE INDEX hip_nombre ON HIPOTECA(hip_nombre ASC)");
		Log.i(this.getClass().toString(),"Tabla HIPOTECA creada");
		
		//Insercion de datos iniciales
		
		db.execSQL("INSERT INTO HIPOTECA(_id,hip_nombre)VALUES (1,'BANCO AGRICOLA')");
		db.execSQL("INSERT INTO HIPOTECA(_id,hip_nombre)VALUES (2,'CITYBANK')");
		db.execSQL("INSERT INTO HIPOTECA(_id,hip_nombre)VALUES (3,'BANCO DE AMERICA CENTRAL')");
		db.execSQL("INSERT INTO HIPOTECA(_id,hip_nombre)VALUES (4,'BANCO PROCREDIT')");
		db.execSQL("INSERT INTO HIPOTECA(_id,hip_nombre)VALUES (5,'BANCO HIPOTECARIO')");
		db.execSQL("INSERT INTO HIPOTECA(_id,hip_nombre)VALUES (6,'BANCO PROMERICA')");
		db.execSQL("INSERT INTO HIPOTECA(_id,hip_nombre)VALUES (7,'BANCO G&T CONTINENTAL')");
		
		
		Log.i(this.getClass().toString(), "Datos iniciales HIPOTECA insertados");
		Log.i(this.getClass().toString(),"Base de datos creada");
		
		
	};
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		if (newVersion == 2)
		   {
		      db.execSQL("UPDATE HIPOTECA SET hip_contacto = 'Julián Gómez Martínez'," +
		                  "              hip_email = 'jgmartinez@gmail.com'," +
		                  "              hip_observaciones = 'Tiene toda la documentación y está estudiando la solicitud. En breve llamará para informar de las condiciones'" +
		                  " WHERE _id = 1");
		   }
		
	}

}
