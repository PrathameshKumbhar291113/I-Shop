package com.ishop.get_products_feature.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.ishop.R
import com.ishop.databinding.FragmentGetProductDetailBinding
import com.ishop.get_products_feature.presentation.viewmodels.GetProductDetailsViewModel
import com.ishop.network.models.GetProductsResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetProductDetailFragment : Fragment() {
    private lateinit var binding: FragmentGetProductDetailBinding
    private val getProductsDetailsViewModel: GetProductDetailsViewModel by activityViewModels()
    private lateinit var getProductDetail : GetProductsResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_get_product_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUi()
        setUpObservers()
    }

    private fun setUpUi() {
        getProductDetail = arguments?.getParcelable<GetProductsResponse>(GetProductListFragment.PRODUCT_DATA)!!
    }

    private fun setUpObservers() {
        getProductsDetailsViewModel.setProductDetails(getProductDetail)
        getProductsDetailsViewModel.productDetails.observe(viewLifecycleOwner){
            it?.let {
                if (it.image.isNullOrEmpty()) {
                    binding.productImage.load(R.drawable.iv_i_shop_logo_white)
                } else {
                    binding.productImage.load(it.image)
                }
                binding.productNameValue.text = it.productName
                binding.productTypeValue.text = it.productType
                binding.productPriceValue.text = "₹${it.price.toString()}/-"
                binding.productTaxValue.text = "${it.tax.toString()}%"
            }
        }
    }

}