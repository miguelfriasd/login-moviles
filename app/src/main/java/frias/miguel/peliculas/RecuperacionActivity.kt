package frias.miguel.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RecuperacionActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var et_correo: EditText
    lateinit var btn_recuperar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperacion)
        et_correo= findViewById(R.id.et_correo_recuperacion)
        btn_recuperar= findViewById(R.id.btn_recuperar_recuperacion)

        auth= Firebase.auth

        btn_recuperar.setOnClickListener{
            
        }

    }
}