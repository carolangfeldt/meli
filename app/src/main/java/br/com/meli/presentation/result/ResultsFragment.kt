package br.com.meli.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.meli.databinding.FragmentResultsBinding
import br.com.meli.presentation.results.adapter.ResultsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ResultsViewModel by viewModel()
    private val args by navArgs<ResultsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.search(args.query)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.products.collectLatest { products ->
                if (products.isEmpty()) {
                    binding.recyclerView.visibility = View.GONE
                    binding.txtEmptyMessage.visibility = View.VISIBLE
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.txtEmptyMessage.visibility = View.GONE
                    binding.recyclerView.adapter = ResultsAdapter(products) { product ->
                        val action = ResultsFragmentDirections
                            .actionResultsFragmentToDetailsFragment(
                                productId = product.id,
                                categoryId = product.categoryId
                            )
                        findNavController().navigate(action)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.error.collectLatest { error ->
                error?.let {
                    androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Erro")
                        .setMessage(it)
                        .setPositiveButton("OK", null)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}