package es.sgv.firebasesqlitesgv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.sgv.firebasesqlitesgv.sqlite.MostrarPilotosActivity

class MainActivity : AppCompatActivity() {

    lateinit var bienvenida: TextView
    lateinit var btn: Button

    lateinit var btnIrASqlite: Button

    private lateinit var autenticacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autenticacion = Firebase.auth

        bienvenida = findViewById(R.id.tvBienvenida)
        btn = findViewById(R.id.btnIni)
        btnIrASqlite = findViewById(R.id.btnIrASQLite)

        var usuario:String = autenticacion.currentUser?.email.toString()
        bienvenida.text = "Bienvenido a la aplicacion usuario con correo " + usuario + "."

        btn.setOnClickListener(){
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btnIrASqlite.setOnClickListener(){
            startActivity(Intent(this, MostrarPilotosActivity::class.java))
        }

    }
}