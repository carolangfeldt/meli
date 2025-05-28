package br.com.meli.presentation.auth

import MeliAuthApi
import SessionManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class AuthRedirectActivity : AppCompatActivity() {

    private val sessionManager: SessionManager by inject()
    private val authApi: MeliAuthApi by inject(named("auth"))

    private val redirectUri = "https://carolangfeldt.github.io/meli/redirect.html"
    private val clientId = "5613838051499714"
    private val clientSecret = "uTbYIbOk7vlwTvUcyE2G2csmVR9BIDKf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val code = intent?.data?.getQueryParameter("code")

        if (code != null) {
            Log.d("AuthRedirect", "Código recebido: $code")

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = authApi.exchangeCode(
                        grantType = "authorization_code",
                        clientId = clientId,
                        clientSecret = clientSecret,
                        code = code,
                        redirectUri = redirectUri
                    )
                    Log.e("AuthRedirect", "Token: ${response.accessToken}, Expira em: ${response.expiresIn}")
                    sessionManager.saveToken(response.accessToken)
                } catch (e: Exception) {
                    Log.e("AuthRedirect", "Erro ao trocar código por token", e)
                } finally {
                    withContext(Dispatchers.Main) {
                        finish()
                    }
                }
            }

        } else {
            Log.e("AuthRedirect", "Código não recebido na URI")
            finish()
        }
    }
}