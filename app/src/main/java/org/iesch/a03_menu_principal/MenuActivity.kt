package org.iesch.a03_menu_principal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.apirazas.RazasApiActivity
import org.iesch.a03_menu_principal.cine.ListaPeliculasActivity
import org.iesch.a03_menu_principal.dataStore.DataStoreActivity
import org.iesch.a03_menu_principal.dataStore.LoginDB
import org.iesch.a03_menu_principal.databinding.ActivityMenuBinding
import org.iesch.a03_menu_principal.edadcanina.EdadCaninaActivity
import org.iesch.a03_menu_principal.fragments.FragmentsActivity
import org.iesch.a03_menu_principal.mapas.MapasActivity
import org.iesch.a03_menu_principal.settings.SettingsActivity
import org.iesch.a03_menu_principal.superheroes.RegistroSuperHeroeActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // 2
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMenuBinding.inflate( layoutInflater )
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bundle = intent.extras!!
        val email = bundle.getString("email")

        binding.tvBienvenida.text = "Hola " + email

        val db = LoginDB(this)

        binding.btnLogOut.setOnClickListener {
            db.cerrarSesion()
            val intent = Intent(this, MenuLogin::class.java)
            startActivity(intent)
            finish()
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

        binding.btnDataStore.setOnClickListener {
            irADataStore()
        }

    }

    private fun irAMapas() {
        val intent = Intent(this, MapasActivity::class.java)
        startActivity(intent)
    }

    private fun irADataStore() {
        val intent = Intent(this, DataStoreActivity::class.java)
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


