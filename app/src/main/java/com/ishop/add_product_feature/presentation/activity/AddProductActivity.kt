package com.ishop.add_product_feature.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ishop.R
import com.ishop.databinding.ActivityAddProductBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@AddProductActivity, R.layout.activity_add_product)

        setupUi()
    }

    private fun setupUi() {
        window.statusBarColor = getColor(R.color.black)
    }
}