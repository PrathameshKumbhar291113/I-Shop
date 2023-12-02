package com.ishop.get_products_feature.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ishop.R
import com.ishop.databinding.FragmentGetProductListBinding
import com.ishop.get_products_feature.presentation.adapter.ProductsAdapter
import com.ishop.get_products_feature.presentation.viewmodels.GetProductsViewModel
import com.ishop.network.models.GetProductsResponse
import com.ishop.utils.NetworkResult

class GetProductListFragment : Fragment() {
    private lateinit var binding: FragmentGetProductListBinding
    private lateinit var productAdapter: ProductsAdapter
    private val productsViewModel: GetProductsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_get_product_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpObservers()
    }

    private fun setUpUi() {

    }

    private fun setUpObservers() {
        productsViewModel.getProductsList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    it.data?.body()?.let { productList ->
                        Log.e("Prathamesh", "setUpObservers: ${productList.toString()}")
                        setupRecyclerView(productList)
                    }
                }

                is NetworkResult.Error -> {}
            }

        }
    }

    private fun setupRecyclerView(productList: List<GetProductsResponse>) {
        productAdapter = ProductsAdapter()
        productAdapter.differ.submitList(productList)
        binding.productsRecyclerView.adapter = productAdapter
    }

}