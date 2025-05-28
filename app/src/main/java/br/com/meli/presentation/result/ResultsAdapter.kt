package br.com.meli.presentation.results.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.meli.databinding.ItemProductBinding
import br.com.meli.domain.model.Product
import com.bumptech.glide.Glide
import ensureHttps
import formatAsCurrency

class ResultsAdapter(
    private val list: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ResultsAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.txtTitle.text = product.title
            binding.txtPrice.text = product.price.formatAsCurrency()
            Glide.with(binding.imgThumbnail.context)
                .load(product.thumbnail.ensureHttps())
                .into(binding.imgThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }
}