package com.ishop.get_products_feature.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ishop.R
import com.ishop.databinding.ActivityGetProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@GetProductActivity, R.layout.activity_get_product)
        setupUi()
    }

    private fun setupUi() {
        window.statusBarColor = getColor(R.color.black)
    }
}