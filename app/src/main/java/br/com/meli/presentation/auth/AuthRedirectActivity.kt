package br.com.meli.presentation.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AuthRedirectActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent?.data
        val code = uri?.getQueryParameter("code")

        if (code != null) {
            AuthManager.exchangeCodeForToken(this, code)
        } else {
            Toast.makeText(this, "Autenticação cancelada", Toast.LENGTH_SHORT).show()
        }

        finish()
    }
}