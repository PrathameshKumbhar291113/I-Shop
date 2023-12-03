package com.ishop.get_products_feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ishop.R
import com.ishop.databinding.ItemProductBinding
import com.ishop.network.models.GetProductsResponse
import java.util.Locale

class ProductsAdapter(private val initialProducts: List<GetProductsResponse>) : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(), Filterable {

    private var filteredProducts: List<GetProductsResponse> = initialProducts

    inner class ProductsViewHolder(
        val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(getProductsResponse: GetProductsResponse) {

            getProductsResponse.image?.let {
                if (it.isNullOrEmpty()) {
                    binding.productIcon.load(R.drawable.iv_i_shop_logo_white)
                } else {
                    binding.productIcon.load(getProductsResponse.image)
                }
            }

            binding.productName.text = getProductsResponse.productName?.toString()
            binding.productType.text = getProductsResponse.productType?.toString()
            binding.productPrice.text = "₹${getProductsResponse.price?.toString()}/-"
            binding.productTax.text = "Tax: ${getProductsResponse.tax?.toString()}%"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = filteredProducts.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) =
        holder.bind(filteredProducts[position])


    // Implement Filterable interface
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val charString = charSequence.toString().toLowerCase(Locale.getDefault())
                filteredProducts = if (charString.isEmpty()) {
                    initialProducts
                } else {
                    initialProducts.filter {
                        it.productName?.toLowerCase(Locale.getDefault())?.contains(charString) == true
                    }
                }

                val filterResults = FilterResults()
                filterResults.values = filteredProducts
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: FilterResults?
            ) {
                filteredProducts = filterResults?.values as List<GetProductsResponse>
                notifyDataSetChanged()
            }
        }
    }
}