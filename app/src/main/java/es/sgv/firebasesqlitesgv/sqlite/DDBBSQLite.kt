package es.sgv.firebasesqlitesgv.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase.CursorFactory

class DDBBSQLite(context: Context, name: String, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version)  {

    constructor(context: Context): this(context, "pilotosF1", null, 1){

    }

    override fun onCreate(bbdd: SQLiteDatabase) {
        var query:String = """
            CREATE TABLE piloto (
            Nombre TEXT,
            Dorsal INTEGER,
            Escudería TEXT,
            Imagen TEXT
            )
        """.trimIndent()

        bbdd.execSQL(query)
    }

    override fun onUpgrade(bbdd: SQLiteDatabase, versionAntigua: Int, versionNueva: Int) {
        bbdd.execSQL("DROP TABLE IF EXISTS piloto")
        onCreate(bbdd)
    }

}