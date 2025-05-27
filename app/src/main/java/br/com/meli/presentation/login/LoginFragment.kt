package br.com.meli.presentation.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.meli.R
import br.com.meli.databinding.FragmentLoginBinding
import br.com.meli.presentation.auth.AuthManager

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val clientId = "SEU_CLIENT_ID"
    private val redirectUri = "meli://auth"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            val authUrl = "https://auth.mercadolivre.com.br/authorization" +
                    "?response_type=code" +
                    "&client_id=$clientId" +
                    "&redirect_uri=$redirectUri"

            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)))
        }
    }

    override fun onResume() {
        super.onResume()
        val token = AuthManager.getToken(requireContext())
        if (!token.isNullOrBlank()) {
            findNavController().navigate(R.id.action_loginFragment_to_searchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}