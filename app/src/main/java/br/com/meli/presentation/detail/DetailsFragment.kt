package br.com.meli.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.meli.R
import br.com.meli.databinding.FragmentDetailsBinding
import br.com.meli.util.animation.ZoomOutPageTransformer
import formatAsCurrency
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import setExpandableText

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.loadProductDetail(args.productId, args.categoryId)

        binding.txtDescription.setHorizontallyScrolling(false)
        binding.txtDescription.overScrollMode = View.OVER_SCROLL_NEVER

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.productDetail.collectLatest { detail ->
                detail?.let {
                    binding.txtTitle.text = it.title
                    binding.txtPrice.text = it.price.formatAsCurrency()

                    val adapter = ImageCarouselAdapter(it.pictures)
                    binding.carouselViewPager.adapter = adapter
                    binding.dotsIndicator.setViewPager2(binding.carouselViewPager)
                    binding.carouselViewPager.setPageTransformer(ZoomOutPageTransformer())
                    binding.carouselViewPager.apply {
                        offscreenPageLimit = 3
                        clipToPadding = false
                        clipChildren = false
                        setPadding(60, 0, 60, 0)
                        setPageTransformer { page, position ->
                            val scale = 1 - 0.2f * kotlin.math.abs(position)
                            page.scaleY = scale
                        }
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.description.collectLatest { description ->
                description?.let {
                    binding.txtDescription.setExpandableText(it)
                } ?: run {
                    binding.txtDescription.text = "Descrição indisponível."
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}