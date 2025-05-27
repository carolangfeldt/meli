package br.com.meli.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.meli.databinding.FragmentResultsBinding
import br.com.meli.presentation.results.ResultsViewModel
import br.com.meli.presentation.results.adapter.ResultsAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ResultsViewModel by viewModels()
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
                binding.recyclerView.adapter = ResultsAdapter(products) { product ->
                    val action = ResultsFragmentDirections
                        .actionResultsFragmentToDetailsFragment(
                            title = product.title,
                            price = product.price.toFloat(),
                            thumbnail = product.thumbnail
                        )
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}