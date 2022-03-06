package es.sgv.firebasesqlitesgv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistroActivity : AppCompatActivity() {

    private lateinit var usuario : EditText
    private lateinit var contra1 : EditText
    private lateinit var contra2 : EditText

    private lateinit var registrarse : Button
    private lateinit var irAInicio : Button

    private lateinit var autentificacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        autentificacion = Firebase.auth

        cargarElementos()

        registrarse.setOnClickListener(){
            var mail = usuario.text.toString()
            var contra1 = contra1.text.toString()
            var contra2 = contra2.text.toString()

            if (contra1.equals(contra2) && checkIfEmpty(mail, contra1, contra2)){
                registrarse(mail, contra1)
            }
        }

        irAInicio.setOnClickListener(){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registrarse(mail: String, contra1: String) {
        autentificacion.createUserWithEmailAndPassword(mail, contra1).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){

                Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
            }
            
        }
    }

    private fun checkIfEmpty(mail: String, contra1: String, contra2: String): Boolean {
        return mail.isNotEmpty() && contra1.isNotEmpty() && contra2.isNotEmpty()
    }

    private fun cargarElementos() {
        usuario = findViewById(R.id.itRegEmail)
        contra1 = findViewById(R.id.itRegContra)
        contra2 = findViewById(R.id.itRegContra2)

        registrarse = findViewById(R.id.btnRegistrarse)
        irAInicio = findViewById(R.id.btnIrLogin)
    }
}