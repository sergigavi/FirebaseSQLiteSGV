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

class LoginActivity : AppCompatActivity() {

    lateinit var btnIniciarSesion : Button
    lateinit var btnRegistrarse : Button

    lateinit var txtUser : EditText
    lateinit var txtPassword : EditText

    private lateinit var autenticacion: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        autenticacion = Firebase.auth

        cargarItems()


    }

    private fun cargarItems() {

        cargarBotones()

        txtUser = findViewById(R.id.itEmail)
        txtPassword = findViewById(R.id.itContrasenna)

    }

    private fun cargarBotones() {
        //
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnRegistrarse = findViewById(R.id.btnRegistro)

        //
        btnIniciarSesion.setOnClickListener()
        {
            var mail = txtUser.text.toString()
            var contra = txtPassword.text.toString()

            if (mail.isNotEmpty() && contra.isNotEmpty()){
                autenticacion.signInWithEmailAndPassword(mail, contra)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful){
                            iniciarSesion()
                            finish()
                        }else{
                            Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                        }
                    }

            }


        }

        btnRegistrarse.setOnClickListener()
        {
            registrarUsuario()
        }
    }

    private fun iniciarSesion() {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun registrarUsuario() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
}