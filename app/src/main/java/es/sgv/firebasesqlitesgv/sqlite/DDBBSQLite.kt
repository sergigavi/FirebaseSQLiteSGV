package es.sgv.firebasesqlitesgv.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.util.Log
import android.widget.Toast

class DDBBSQLite(context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version)  {

    constructor(context: Context): this(context, "pilotosF1", null, 1){

    }

    override fun onCreate(bbdd: SQLiteDatabase) {

        val query:String = """
            CREATE TABLE piloto (
            Nombre TEXT PRIMARY KEY,
            Dorsal INTEGER NOT NULL,
            Escudería TEXT NOT NULL,
            Imagen TEXT
            );
        """.trimIndent()

        //Log.d("crear tabla", query)

        bbdd.execSQL(query)
    }

    override fun onUpgrade(bbdd: SQLiteDatabase, versionAntigua: Int, versionNueva: Int) {
        bbdd.execSQL("DROP TABLE IF EXISTS piloto")
        onCreate(bbdd)
    }

}