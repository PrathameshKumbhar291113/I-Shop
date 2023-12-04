package com.ishop.add_product_feature.presentation.fragments

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.ishop.R
import com.ishop.add_product_feature.presentation.viewmodels.AddProductsViewModel
import com.ishop.databinding.FragmentAddProductBinding
import com.ishop.network.requests.createAddProductRequestBody
import com.ishop.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

@AndroidEntryPoint
class AddProductFragment : Fragment() {
    private lateinit var binding: FragmentAddProductBinding
    private val addProductsViewModel: AddProductsViewModel by activityViewModels()

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
        private const val REQUEST_READ_EXTERNAL_STORAGE = 123
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setUpObservers()
        if (hasReadExternalStoragePermission()){
            selectImageAndSetinImageView()
        }else{
            requestReadExternalStoragePermission()
        }

    }

    private fun setupUi() {
        selectImageAndSetinImageView()

        binding.addProductButton.setOnClickListener {
            val productDetails = createAddProductRequestBody(
                productName = binding.productNameValue.text.toString(),
                productType = binding.productTypeValue.text.toString(),
                price = binding.productPriceValue.text.toString(),
                tax = binding.productTaxValue.text.toString(),
                image = addProductsViewModel.image.value
            )
            Log.e("Prathamesh", "setupUi: ${productDetails.toString()}")
            addProductsViewModel.addProducts(productDetails)
        }
    }

    private fun setUpObservers() {
        addProductsViewModel.addProductsResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    it.data?.let { response ->
                        Toast.makeText(requireContext(), "${response.body()?.message}", Toast.LENGTH_SHORT).show()
                        Log.e("Prathamesh", "setUpObservers: Product Details:- ${response.body()?.productDetails} , Product Id:- ${response.body()?.productId}, Product Success:- ${response.body()?.success}")
                        if (response.body()?.success!!) {
                            requireActivity().finish()
                        }
                    }
                }

                is NetworkResult.Error -> {
                    Log.e("Prathamesh", "setUpObservers: Product Details:- ${it.data?.body()?.productDetails} , Product Id:- ${it.data?.body()?.productId}, Product Success:- ${it.data?.body()?.success} , Error Mesaage:- ${it.message.toString()}")
                }
            }
        }
    }


    private fun selectImageAndSetinImageView() {
        binding.imageSelected.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            data?.data?.let { selectedImageUri ->
                binding.imageSelected.load(selectedImageUri)

                val filePath = getRealPathFromURI(selectedImageUri)

                filePath?.let {
                    val file = File(it)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    val imagePart =
                        MultipartBody.Part.createFormData("files[]", file.name, requestFile)
                    addProductsViewModel.getImage(imagePart)
                }
            }
        }
    }

    private fun getRealPathFromURI(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = activity?.contentResolver?.query(uri, projection, null, null, null)
        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor?.moveToFirst()
        val path = cursor?.getString(columnIndex ?: 0)
        cursor?.close()
        return path
    }

    private fun hasReadExternalStoragePermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
           READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadExternalStoragePermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(READ_EXTERNAL_STORAGE),
            REQUEST_READ_EXTERNAL_STORAGE
        )
    }

    private fun performImageSelection() {
        // The logic to perform image selection can go here
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, perform the image selection logic here
                performImageSelection()
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
                Toast.makeText(
                    requireContext(),
                    "Permission denied. Cannot perform image selection.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}
