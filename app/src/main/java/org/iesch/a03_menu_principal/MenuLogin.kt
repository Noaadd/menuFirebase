package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.dataStore.LoginDB
import org.iesch.a03_menu_principal.databinding.ActivityLoginMenuBinding
import kotlin.jvm.java


class MenuLogin : AppCompatActivity() {
    private lateinit var binding: ActivityLoginMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginMenuBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val db = LoginDB(this)


        binding.btnEntrar.setOnClickListener {
            if (db.estaLogueado()) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }

            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                db.guardarLogin(email, password)
                val intent = Intent(this, MenuActivity::class.java)
                intent.putExtra("email", email)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Introduce email y contraseña", Toast.LENGTH_SHORT).show()
            }

//            logear()
        }
    }
//    private fun logear() {
//        val eTEmail = binding.etEmail.text.toString()
//        val intent = Intent(this, MenuActivity::class.java)
//        intent.putExtra("email", eTEmail)
//        startActivity(intent)
//    }
}