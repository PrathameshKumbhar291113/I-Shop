package com.ishop.get_products_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ishop.R
import com.ishop.add_product_feature.presentation.activity.AddProductActivity
import com.ishop.databinding.FragmentGetProductListBinding
import com.ishop.get_products_feature.presentation.adapter.ProductsAdapter
import com.ishop.get_products_feature.presentation.viewmodels.GetProductsViewModel
import com.ishop.network.models.GetProductsResponse
import com.ishop.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.image
import splitties.fragments.start

@AndroidEntryPoint
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
        handleOnBackPress()
    }

    private fun setUpUi() {
        setUpFloatingActionButtons()
    }

    private fun setUpObservers() {
        productsViewModel.getProductsList.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {}
                is NetworkResult.Success -> {
                    it.data?.body()?.let { productList ->
                        setupRecyclerView(productList)
                    }
                }

                is NetworkResult.Error -> {}
            }

        }
    }

    private fun setUpFloatingActionButtons() {
        binding.expandFab.setOnClickListener {
            if (!binding.addProduct.isVisible){
                binding.expandFab.image = getDrawable(requireContext(),R.drawable.iv_cancel)
                binding.addProduct.isVisible = true
                binding.searchProduct.isVisible = true
            }else{
                binding.expandFab.image = getDrawable(requireContext(),R.drawable.iv_add)
                binding.addProduct.isVisible = false
                binding.searchProduct.isVisible = false
            }
        }

        binding.searchProduct.setOnClickListener {
            binding.searchView.isVisible = !binding.searchView.isVisible
        }

        binding.addProduct.setOnClickListener {
            start<AddProductActivity>()
        }

        setUpSearchFeature()
    }

    private fun setUpSearchFeature() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                productAdapter.filter.filter(newText)
                return true
            }
        })
    }


    private fun setupRecyclerView(productList: List<GetProductsResponse>) {
        productAdapter = ProductsAdapter(productList)
        binding.productsRecyclerView.adapter = productAdapter
    }

    private fun handleOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        productsViewModel.getProductsList()
    }

}