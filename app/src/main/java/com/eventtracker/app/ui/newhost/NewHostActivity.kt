package com.eventtracker.app.ui.newhost

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.R
import com.eventtracker.app.databinding.NewHostActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class NewHostActivity: DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: NewHostActivityBinding
    private val viewModel: NewHostViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewHostViewModel::class.java]
    }

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding = DataBindingUtil.setContentView(this, R.layout.new_host_activity)
        binding.viewModel = viewModel

        binding.buttonSelectPhoto.setOnClickListener {
            val options = arrayOf<CharSequence>(getString(R.string.take_photo), getString(R.string.choose_from_gallery), getString(R.string.cancel))

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose your host avatar")

            builder.setItems(options) { dialog, item ->
                when {
                    options[item] == getString(R.string.take_photo) -> {
                        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(takePicture, 0)
                    }
                    options[item] == getString(R.string.choose_from_gallery) -> {
                        val pickPhoto = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        startActivityForResult(pickPhoto, 1)
                    }
                    options[item] == getString(R.string.cancel) -> {
                        dialog.dismiss()
                    }
                }
            }.show()
        }

        binding.buttonSubmit.setOnClickListener {
            try {
                viewModel.validateInput()
                viewModel.onSubmit()
            } catch (exception: Exception) {
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED) {
            when (requestCode) {
                0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val selectedPhoto = data.extras!!["data"] as Bitmap?
                    binding.imageViewSelectedPhoto.setImageBitmap(selectedPhoto)
                }
                1 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val selectedPhoto = data.data
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhoto)
                    binding.imageViewSelectedPhoto.setImageBitmap(bitmap)
                }
            }

            binding.buttonSelectPhoto.alpha = 0f
        }
    }
}