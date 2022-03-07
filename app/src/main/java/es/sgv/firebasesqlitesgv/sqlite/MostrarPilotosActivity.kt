package es.sgv.firebasesqlitesgv.sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import es.sgv.firebasesqlitesgv.R
import es.sgv.firebasesqlitesgv.databinding.ActivityMostrarPilotosBinding
import java.lang.Exception

class MostrarPilotosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarPilotosBinding //ActivityMainBinding

    //esto no me hace falta ya que uso el viewbinding
    //private lateinit var etDorsalAEliminar : EditText
    //private lateinit var btnBorrarPorDorsal:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //aqui lo quito ya que uso el binding
        binding = ActivityMostrarPilotosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDeleteByDorsal.setOnClickListener(){
            if (binding.dorsalAEliminar.text.isNotEmpty())
            {
                //var conn = DDBBSQLite(this)
                var db: SQLiteDatabase = DDBBSQLite(this).writableDatabase

                try {
                    db.execSQL("DELETE FROM piloto WHERE Dorsal = " + binding.dorsalAEliminar.text.toString() + ";")
                    Toast.makeText(this, "Piloto con dorsal" + binding.dorsalAEliminar.text.toString() + "Eliminado correctamente" , Toast.LENGTH_SHORT).show()
                }catch (e: Exception)
                {
                    Toast.makeText(this, "Error eliminando al piloto con dorsal" + binding.dorsalAEliminar.text.toString() , Toast.LENGTH_SHORT).show()
                }




            }
        }

        //lo metemos en el onCreate para que nada mas se entre en este activity (se cargue en el movil) se llame a este metodo
        initRecyclerView()

    }

    private fun initRecyclerView() {
        //cargarDatos()
        val manager = LinearLayoutManager(this) //puedo cambiar el linear por un GridLayoutManager, y tengo que pasarle el numero de columnas por fila además del contexto (this)
        //vamos a usar los divider decorator para que quede mejor y diferenciar los diferentes items del recyclerview, en este caso solo le mete un divider entre item y item
        val decoration = DividerItemDecoration(this, manager.orientation /*LinearLayoutManager(this).orientation*/) //he puesto en una variable el linearLayoutManager(this) para usarlo aqui y debajo tambien

        //val recyclerView = findViewById<RecyclerView>(R.id.recyclerPiloto)//podemos quitar esto ya que vamos a usar el binding
        //en lugar de recyclerView.layoutManager, al usar el binding ponemos binding.recyclerPiloto (el nombre de la id) //+ hay que cambiar tambien el Pilotoviewholder
        binding.recyclerPiloto.layoutManager = manager //LinearLayoutManager(this) //si pusieramos varios layouts se podrian hacer varias columnas a la vez, un gridlayout
        binding.recyclerPiloto.adapter = PilotoAdapter(getPilotosDDBB(), {piloto ->  onItemSelected(piloto)}) //le pasamos la funcion lambda (entre llaves ya que es una lambda) //podriamos pasarle directamente it sin poner piloto ->, it es iterador, el contenido del piloto, pero lo pongo con la lambda de piloto para que no me lie

    }

    private fun cargarDatos() {
        var conn = DDBBSQLite(this)
        var db: SQLiteDatabase = conn.writableDatabase


        //var cursor: Cursor

        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Max Verstappen', 1,'Red Bull Racing','https://e00-us-marca.uecdn.es/claro/assets/multimedia/imagenes/2021/12/11/16392343357149.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Sergio Pérez', 11,'Red Bull Racing','https://soymotor.com/sites/default/files/imagenes/noticia/sergio-perez-red-bull-2021-soymotor.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Lewis Hamilton', 44,'Mercedes-AMG F1 Petronas Formula One Team','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPvX6eF02d3R6ASp6Z5prxHF-Rqpi_mnKb3Q&usqp=CAU')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('George Russell', 63,'Mercedes-AMG F1 Petronas Formula One Team','https://i0.wp.com/www.thebestf1.es/wp-content/uploads/2021/06/george-russell-williams-2021-pitlane.jpg?fit=750%2C500&ssl=1')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Charles Leclerc', 16,'Scuderia Ferrari','https://www.f1latam.com/img/prin/clc21.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Carlos Sainz', 55,'Scuderia Ferrari','https://soymotor.com/sites/default/files/styles/mega/public/imagenes/noticia/carlos-sainz-ficha-por-ferrari-soymotor.jpg?itok=s3DgYj9t')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Lando Norris', 4,'McLaren F1 Team','https://as01.epimg.net/motor/imagenes/2021/07/14/formula_1/1626278627_407814_1626295263_noticia_normal_recorte1.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Daniel Ricciardo', 3,'McLaren F1 Team','https://cdn-5.motorsport.com/images/mgl/2d1WwrpY/s8/daniel-ricciardo-mclaren-1.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Pierre Gasly', 10,'Scuderia AlphaTauri','https://soymotor.com/sites/default/files/usuarios/redaccion/portal/avazquez/pierre-gasly-2021-soymotor_0.png')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Yuki Tsunoda', 22,'Scuderia AlphaTauri','https://static.motor.es/f1/fichas/contenido/yuki-tsunoda/yuki-tsunoda2021_1617631415.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Esteban Ocon', 31,'Alpine F1 Team','https://cdn-2.motorsport.com/images/mgl/Yv8Wedv0/s8/esteban-ocon-alpine-f1-team-1.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Fernando Alonso', 14,'Alpine F1 Team','https://phantom-marca.unidadeditorial.es/eaa671044e0d25c62fbd7859086894c7/resize/1320/f/jpg/assets/multimedia/imagenes/2021/03/24/16165980226279.jpg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Lance Stroll', 18,'Aston Martin Cognizant Formula One Team','https://imagenes.20minutos.es/files/image_656_370/files/fp/uploads/imagenes/2021/03/25/lance-stroll.r_d.594-206.jpeg')")
        db.execSQL("INSERT INTO piloto (Nombre, Dorsal, Escudería, Imagen) VALUES ('Sebastian Vettel', 5,'Aston Martin Cognizant Formula One Team','https://phantom-marca.unidadeditorial.es/8106be106a6b99a14dd53ab8e733e4cc/resize/1320/f/jpg/assets/multimedia/imagenes/2021/06/10/16233332433486.jpg')")


    }

    //creamos la funcion que le tenemos que pasar al adapter junto a la lista
    fun onItemSelected(piloto:Piloto) //recibe un objeto piloto
    {
        Toast.makeText(this, piloto.nombre, Toast.LENGTH_SHORT).show() //aqui si le podemos pasar el contexto con el this ya que estamos en un activity
    }

    private fun getPilotosDDBB(): ArrayList<Piloto> {
        var conn = DDBBSQLite(this)
        var db: SQLiteDatabase = conn.readableDatabase
        var cursor: Cursor = db.rawQuery("SELECT * FROM piloto", null)
        var usuarios = ArrayList<Piloto>()
        //Log.d("", "")
        while (cursor.moveToNext()) {
            usuarios.add(
                Piloto(
                    cursor.getString(0), //nombre
                    cursor.getInt(1), //dorsal
                    cursor.getString(2), //escuderia
                    cursor.getString(3) //imagen
                )
            )

        }

        db.close()
        //usuarios = pilotos as ArrayList<Piloto>
        return usuarios
    }

    /*private fun construirPiloto(): Piloto {
        return Piloto(etId.text.toString(), etNombre.text.toString(), )
        //TODO inicializar los botones y asignarles las cosas
    }*/


    companion object{
        val pilotos = listOf<Piloto>(

            Piloto("Max Verstappen", 1,"Red Bull Racing","https://e00-us-marca.uecdn.es/claro/assets/multimedia/imagenes/2021/12/11/16392343357149.jpg"),
            Piloto("Sergio Pérez", 11,"Red Bull Racing","https://soymotor.com/sites/default/files/imagenes/noticia/sergio-perez-red-bull-2021-soymotor.jpg"),
            Piloto("Lewis Hamilton", 44,"Mercedes-AMG F1 Petronas Formula One Team","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPvX6eF02d3R6ASp6Z5prxHF-Rqpi_mnKb3Q&usqp=CAU"),
            Piloto("George Russell", 63,"Mercedes-AMG F1 Petronas Formula One Team","https://i0.wp.com/www.thebestf1.es/wp-content/uploads/2021/06/george-russell-williams-2021-pitlane.jpg?fit=750%2C500&ssl=1"),
            Piloto("Charles Leclerc", 16,"Scuderia Ferrari","https://www.f1latam.com/img/prin/clc21.jpg"),
            Piloto("Carlos Sainz", 55,"Scuderia Ferrari","https://soymotor.com/sites/default/files/styles/mega/public/imagenes/noticia/carlos-sainz-ficha-por-ferrari-soymotor.jpg?itok=s3DgYj9t"),
            Piloto("Lando Norris", 4,"McLaren F1 Team","https://as01.epimg.net/motor/imagenes/2021/07/14/formula_1/1626278627_407814_1626295263_noticia_normal_recorte1.jpg"),
            Piloto("Daniel Ricciardo", 3,"McLaren F1 Team","https://cdn-5.motorsport.com/images/mgl/2d1WwrpY/s8/daniel-ricciardo-mclaren-1.jpg"),
            Piloto("Pierre Gasly", 10,"Scuderia AlphaTauri","https://soymotor.com/sites/default/files/usuarios/redaccion/portal/avazquez/pierre-gasly-2021-soymotor_0.png"),
            Piloto("Yuki Tsunoda", 22,"Scuderia AlphaTauri","https://static.motor.es/f1/fichas/contenido/yuki-tsunoda/yuki-tsunoda2021_1617631415.jpg"),
            Piloto("Esteban Ocon", 31,"Alpine F1 Team","https://cdn-2.motorsport.com/images/mgl/Yv8Wedv0/s8/esteban-ocon-alpine-f1-team-1.jpg"),
            Piloto("Fernando Alonso", 14,"Alpine F1 Team","https://phantom-marca.unidadeditorial.es/eaa671044e0d25c62fbd7859086894c7/resize/1320/f/jpg/assets/multimedia/imagenes/2021/03/24/16165980226279.jpg"),
            Piloto("Lance Stroll", 18,"Aston Martin Cognizant Formula One Team","https://imagenes.20minutos.es/files/image_656_370/files/fp/uploads/imagenes/2021/03/25/lance-stroll.r_d.594-206.jpeg"),
            Piloto("Sebastian Vettel", 5,"Aston Martin Cognizant Formula One Team","https://phantom-marca.unidadeditorial.es/8106be106a6b99a14dd53ab8e733e4cc/resize/1320/f/jpg/assets/multimedia/imagenes/2021/06/10/16233332433486.jpg"),
            Piloto("Nicholas Latifi", 6,"Williams Racing","https://noticiasdelmundo.news/wp-content/uploads/2021/03/Nicholas-Latifi-apunta-a-las-ganancias-de-clasificacion-en-2021.jpg"),
            Piloto("Alexander Albon", 23,"Williams Racing","https://soymotor.com/sites/default/files/imagenes/noticia/albon-red-bull-dtm-2021-soymotor.jpg"),
            Piloto("Valtteri Bottas", 77,"Alfa Romeo F1 Team ORLEN","https://soymotor.com/sites/default/files/imagenes/noticia/valtteri-bottas-mercedes-2021-soymotor.jpg"),
            Piloto("Guanyu Zhou", 24,"Alfa Romeo F1 Team ORLEN","https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2021/11/16/16370763276486.jpg"),
            Piloto("Mick Schumacher", 47,"Uralkali Haas F1 Team","https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/mick-schumacher-haas-2021-1626945106.jpg?crop=1xw:0.2766463646745337xh;center,top&resize=1200:*"),
            Piloto("Nikita Mazepin", 9,"Uralkali Haas F1 Team","https://images.daznservices.com/di/library/DAZN_News/92/d3/2021-03-12-mazepin-haas-f1-formula-1_jf8k76dwh1531i38bsw1j1749.jpg?t=688246472&quality=60&h=450")
        )
    }
}