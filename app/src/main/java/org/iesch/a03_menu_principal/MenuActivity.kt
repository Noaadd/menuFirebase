package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.remoteconfig.remoteConfig
import org.iesch.a03_menu_principal.apirazas.RazasApiActivity
import org.iesch.a03_menu_principal.cine.ListaPeliculasActivity
import org.iesch.a03_menu_principal.databinding.ActivityMenuBinding
import org.iesch.a03_menu_principal.edadcanina.EdadCaninaActivity
import org.iesch.a03_menu_principal.fragments.FragmentsActivity
import org.iesch.a03_menu_principal.mapas.MapasActivity
import org.iesch.a03_menu_principal.settings.SettingsActivity
import org.iesch.a03_menu_principal.superheroes.RegistroSuperHeroeActivity
import kotlin.or

enum class ProviderType{
    EMAILYCONTRASENA,
    GOOGLE
}
class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    private val db = FirebaseFirestore.getInstance()

    lateinit var email : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate( layoutInflater )
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras
        email = bundle?.getString("usuario").toString()
        val provider = bundle?.getString("provider")

        binding.tvBienvenida.text = "Hola " + email

        // Recuperamos nuestra Configuración Remota
//        configuracionRemota()

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }

        binding.btnSave.setOnClickListener {
            // 2 - Creamos una estructura dedatos para guardar en Firestore
            // Hemos decidido que la clave por cada usuario sea su email
            db.collection("usuarios").document( email.toString() ).set(
                hashMapOf(
                    "provider" to provider,
                    "email" to email,
                    )
            )
        }





        binding.btnRazas.setOnClickListener {
            irARazasActivity()
        }
        binding.btnFragments.setOnClickListener {
            irAMenuFragments()
        }
        binding.btnEdadCanina.setOnClickListener {
            irAEdadCanina()
        }
        binding.btnSuperheroes.setOnClickListener {
            irASuperHeroes()
        }
        binding.btnSettings.setOnClickListener {
            irASettings()
        }
        binding.btnCine.setOnClickListener {
            irAPeliculas()
        }
        binding.btnMapas.setOnClickListener {
            irAMapas()
        }
        binding.btnLogout.setOnClickListener{
            irALogin()
        }


    }

//    private fun configuracionRemota() {
//        binding.optionalButton.visibility = View.INVISIBLE
//        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener {
//            val showOptionalButtom = Firebase.remoteConfig.getBoolean("show_optional_button")
//            val textoBotonOpcional = Firebase.remoteConfig.getString("optional_button_text")
//            val colorDeFondo = Firebase.remoteConfig.getString("color_bg")
//
//            if (showOptionalButtom) {
//                binding.optionalButton.visibility = View.VISIBLE
//                binding.optionalButton.text = textoBotonOpcional
//            }
//            // Aplicar color de fondo usando la cadena desde Remote Config
//            val bgColor = resolveColorString(colorDeFondo)
//            binding.root.setBackgroundColor(bgColor)
//        }
//    }


    private fun irALogin() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            // con este flag evitamos que el usuario pueda volver atras al menu despues de hacer logout
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }

    private fun irAMapas() {
        val intent = Intent(this, MapasActivity::class.java)
        startActivity(intent)
    }

    private fun irAPeliculas() {
        val intent = Intent(this, ListaPeliculasActivity::class.java)
        startActivity(intent)
    }

    private fun irASettings() {
        val irASettings = Intent(this, SettingsActivity::class.java)
        startActivity(irASettings)
    }

    private fun irAMenuFragments() {
        val irAFragments = Intent(this, FragmentsActivity::class.java)
        startActivity(irAFragments)
    }
    private fun irAEdadCanina() {
        val irAEdadCanina = Intent(this, EdadCaninaActivity::class.java)
        startActivity(irAEdadCanina)
    }
    private fun irASuperHeroes() {
        val irASuperHeroes = Intent(this, RegistroSuperHeroeActivity::class.java)
        startActivity(irASuperHeroes)
    }

    private fun irARazasActivity() {
        val irARazas = Intent(this, RazasApiActivity::class.java)
        startActivity(irARazas)

    }
}


