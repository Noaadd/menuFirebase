package org.iesch.a03_menu_principal.dataStore

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.iesch.a03_menu_principal.MenuActivity
import org.iesch.a03_menu_principal.databinding.ActivityDataStoreBinding


class DataStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("loginDB", MODE_PRIVATE)

        val nombreGuardado = prefs.getString("nombre", "No establecido")
        val edadGuardada = prefs.getString("edad", "No establecida")
        val modoOscuro = prefs.getBoolean("modoOscuro", false)
        val notificaciones = prefs.getBoolean("notificaciones", true)

        binding.textNombreGuardado.text = "Nombre: $nombreGuardado"
        binding.textEdadGuardada.text = "Edad: $edadGuardada"
        binding.textModoOscuro.text =
            "Modo Oscuro: ${if (modoOscuro) "Activado" else "Desactivado"}"
        binding.textNotificaciones.text =
            "Notificaciones: ${if (notificaciones) "Activadas" else "Desactivadas"}"

        binding.switchModoOscuro.isChecked = modoOscuro
        binding.switchNotificaciones.isChecked = notificaciones

        binding.btnGuardarNombre.setOnClickListener {
            val nuevoNombre = binding.editTextNombre.text.toString()
            if (nuevoNombre.isNotEmpty()) {
                prefs.edit().putString("nombre", nuevoNombre).apply()
                binding.textNombreGuardado.text = "Nombre: $nuevoNombre"
                Toast.makeText(this, "Nombre guardado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Introduce un nombre", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGuardarEdad.setOnClickListener {
            val nuevaEdad = binding.editTextEdad.text.toString()
            if (nuevaEdad.isNotEmpty()) {
                prefs.edit().putString("edad", nuevaEdad).apply()
                binding.textEdadGuardada.text = "Edad: $nuevaEdad"
                Toast.makeText(this, "Edad guardada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Introduce una edad", Toast.LENGTH_SHORT).show()
            }
        }

        binding.switchModoOscuro.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("modoOscuro", isChecked).apply()
            binding.textModoOscuro.text =
                "Modo Oscuro: ${if (isChecked) "Activado" else "Desactivado"}"
            delegate.applyDayNight()
        }

        binding.switchNotificaciones.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("notificaciones", isChecked).apply()
            binding.textNotificaciones.text =
                "Notificaciones: ${if (isChecked) "Activadas" else "Desactivadas"}"
        }

        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
