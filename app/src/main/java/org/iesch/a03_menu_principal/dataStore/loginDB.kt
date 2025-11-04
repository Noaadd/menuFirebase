package org.iesch.a03_menu_principal.dataStore

import android.content.Context

class LoginDB(context: Context) {

    private val PREFS_NAME = "loginDB"
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun guardarLogin(email: String, password: String) {
        val editor = prefs.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putBoolean("logueado", true)
        editor.apply()
    }

    fun cerrarSesion() {
        val editor = prefs.edit()
        editor.putBoolean("logueado", false)
        editor.apply()
    }

    fun estaLogueado(): Boolean {
        return prefs.getBoolean("logueado", false)
    }

    fun getEmail(): String? {
        return prefs.getString("email", null)
    }

    fun getPassword(): String? {
        return prefs.getString("password", null)
    }
}
