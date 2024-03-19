package frias.miguel.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var et_correo: EditText
    private lateinit var btn_registro: Button
    private lateinit var et_contra: EditText
    private lateinit var et_confirmar_contra: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        et_correo = findViewById(R.id.et_correo_registro)
        btn_registro = findViewById(R.id.btn_registro_registro)
        et_contra = findViewById(R.id.et_contra_registro)
        et_confirmar_contra= findViewById(R.id.et_confirmar_contra_registro)

        auth = Firebase.auth

        btn_registro.setOnClickListener{
            var correo = et_correo.text.toString()
            var contra = et_contra.text.toString()
            var confirmar_contra = et_confirmar_contra.text.toString()

            if (!(correo.isNullOrEmpty() && contra.isNullOrEmpty() && confirmar_contra.isNullOrEmpty())){
                if (contra == confirmar_contra){
                    auth.createUserWithEmailAndPassword(correo, contra)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("exito", "createUserWithEmail:success")
                                val user = auth.currentUser
                                finish()
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("fallo", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "Authentication failed.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(null)
                            }
                        }
                }else{
                   Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Ingrese los datos necesarios", Toast.LENGTH_SHORT).show()
            }
        }
    }
}