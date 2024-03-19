package frias.miguel.peliculas

import android.util.Log
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var btn_ingresar: Button;
    lateinit var btn_registro: Button
    lateinit var tv_recuperacion: TextView
    lateinit var et_correo: EditText
    lateinit var et_contra: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_ingresar = findViewById(R.id.btn_ingresar_login)
        btn_registro = findViewById((R.id.btn_registro_login))
        tv_recuperacion = findViewById(R.id.tv_recuparar_login)
        et_correo= findViewById(R.id.et_correo_login)
        et_contra= findViewById(R.id.et_contra_login)

        auth= Firebase.auth

        btn_registro.setOnClickListener{
            var intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btn_ingresar.setOnClickListener{
            var correo= et_correo.text.toString()
            var contra= et_contra.text.toString()
            if (!(correo.isNullOrEmpty() && contra.isNullOrEmpty())){
                auth.signInWithEmailAndPassword(correo, contra)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("exito", "signInWithEmail:success")
                            val user = auth.currentUser
                            var intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            //updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("fallo", "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            //updateUI(null)
                        }
                    }
            }else{
                Toast.makeText(this,"Credenciales invalidas", Toast.LENGTH_SHORT).show()
            }
        }

        tv_recuperacion.setOnClickListener{
            var intent = Intent(this, RecuperacionActivity::class.java)
            startActivity(intent)
        }
    }

}