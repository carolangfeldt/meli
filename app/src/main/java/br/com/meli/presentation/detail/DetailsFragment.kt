package br.com.meli.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.meli.databinding.FragmentDetailsBinding
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.txtTitle.text = args.title
        binding.txtPrice.text = "R$ %.2f".format(args.price)

        Glide.with(requireContext())
            .load(args.thumbnail)
            .into(binding.imgProduct)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}