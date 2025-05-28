package br.com.meli.presentation.login

import SessionManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.meli.BuildConfig
import br.com.meli.R
import br.com.meli.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val sessionManager: SessionManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupLoginButton()
    }

    override fun onResume() {
        super.onResume()
        checkExistingSession()
    }

    private fun setupLoginButton() {
        binding.btnLogin.setOnClickListener {
            val authUrl = buildAuthUrl()
            launchAuthBrowser(authUrl)
        }
    }

    private fun buildAuthUrl(): String {
        return AUTH_BASE_URL +
                "?response_type=code" +
                "&client_id=$CLIENT_ID" +
                "&redirect_uri=$REDIRECT_URI"
    }

    private fun launchAuthBrowser(authUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
        startActivity(intent)
    }

    private fun checkExistingSession() {
        if (BuildConfig.IS_MOCK) {
            Log.d(TAG, "Executando em modo mock: ignorando login")
            findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
        } else {
            val token = sessionManager.getToken()
            Log.d(TAG, "Token carregado: $token")
            if (!token.isNullOrBlank()) {
                findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "LoginFragment"
        private const val CLIENT_ID = "5613838051499714"
        private const val REDIRECT_URI = "https://carolangfeldt.github.io/meli/redirect.html"
        private const val AUTH_BASE_URL = "https://auth.mercadolivre.com.br/authorization"
    }
}