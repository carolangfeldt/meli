package br.com.meli.presentation.auth

import android.content.Context
import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

object AuthManager {
    private const val CLIENT_ID = "SEU_CLIENT_ID"
    private const val CLIENT_SECRET = "SEU_CLIENT_SECRET"
    private const val REDIRECT_URI = "meli://auth"

    fun exchangeCodeForToken(context: Context, code: String) {
        val body = FormBody.Builder()
            .add("grant_type", "authorization_code")
            .add("client_id", CLIENT_ID)
            .add("client_secret", CLIENT_SECRET)
            .add("code", code)
            .add("redirect_uri", REDIRECT_URI)
            .build()

        val request = Request.Builder()
            .url("https://api.mercadolibre.com/oauth/token")
            .post(body)
            .build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Auth", "Erro: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                response.body?.string()?.let { responseBody ->
                    val json = JSONObject(responseBody)
                    val token = json.optString("access_token")

                    if (token.isNotEmpty()) {
                        saveToken(context, token)
                        Log.d("Auth", "Token salvo: $token")
                    }
                }
            }
        })
    }

    private fun saveToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().putString("access_token", token).apply()
    }

    fun getToken(context: Context): String? {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        return prefs.getString("access_token", null)
    }
}