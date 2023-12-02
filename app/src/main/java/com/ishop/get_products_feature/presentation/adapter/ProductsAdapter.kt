package com.ishop.get_products_feature.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ishop.R
import com.ishop.databinding.ItemProductBinding
import com.ishop.network.models.GetProductsResponse

class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    companion object {
        private val diffUtilComparator = object : DiffUtil.ItemCallback<GetProductsResponse>() {
            override fun areItemsTheSame(
                oldItem: GetProductsResponse,
                newItem: GetProductsResponse
            ): Boolean {
                return oldItem.productName == newItem.productName
            }

            override fun areContentsTheSame(
                oldItem: GetProductsResponse,
                newItem: GetProductsResponse
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    val differ = AsyncListDiffer(this, diffUtilComparator)

    inner class ProductsViewHolder(
        val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(getProductsResponse: GetProductsResponse) {

            getProductsResponse.image?.let {
                if (it.isNullOrEmpty()) {
                    binding.productIcon.load(R.drawable.iv_shopping_bag)
                } else {
                    binding.productIcon.load(getProductsResponse.image)
                }
            }

            binding.productName.text = getProductsResponse.productName?.toString()
            binding.productType.text = getProductsResponse.productType?.toString()
            binding.productPrice.text = getProductsResponse.price?.toString()


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) =
        holder.bind(differ.currentList[position])
}