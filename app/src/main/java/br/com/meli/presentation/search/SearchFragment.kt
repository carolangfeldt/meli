package br.com.meli.presentation.search

import SessionManager
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.meli.BuildConfig
import br.com.meli.R
import br.com.meli.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val sessionManager: SessionManager by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
        observeViewModel()
        setupBackHandler()
    }

    private fun setupListeners() {
        binding.etSearch.addTextChangedListener {
            viewModel.onQueryChanged(it.toString())
        }

        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                val action = SearchFragmentDirections.actionSearchFragmentToResultsFragment(query)
                findNavController().navigate(action)
            } else {
                binding.etSearch.error = "Digite algo para buscar"
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.isSearchEnabled.collectLatest { enabled ->
                binding.btnSearch.isEnabled = enabled
            }
        }
    }

    private fun setupBackHandler() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!BuildConfig.IS_MOCK) {
                    showLogoutConfirmationDialog()
                }
            }
        })
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Deseja sair?")
            .setMessage("Você será deslogado da sua conta.")
            .setPositiveButton("Sim") { _, _ ->
                sessionManager.clearSession()
                findNavController().navigate(R.id.action_global_loginFragment)
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}