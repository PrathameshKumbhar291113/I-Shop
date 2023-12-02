package com.ishop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.ishop.databinding.ActivitySplashBinding
import com.ishop.get_products_feature.presentation.activity.GetProductActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.activities.start

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@SplashActivity, R.layout.activity_splash)
        setupUi()
    }

    private fun setupUi() {
        window.statusBarColor = getColor(R.color.black)
        lifecycleScope.launch {
            delay(3000)
            start<GetProductActivity>()
        }
    }
}