import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AuthActivity : AppCompatActivity() {

    private val clientId = "SEU_CLIENT_ID"
    private val redirectUri = "meli://auth"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginButton = Button(this).apply {
            text = "Entrar com Mercado Livre"
            setOnClickListener {
                val authUrl = "https://auth.mercadolivre.com.br/authorization" +
                        "?response_type=code" +
                        "&client_id=$clientId" +
                        "&redirect_uri=$redirectUri"

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
                startActivity(intent)
            }
        }

        setContentView(loginButton)
    }
}