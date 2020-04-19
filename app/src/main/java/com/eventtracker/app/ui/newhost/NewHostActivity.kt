package com.eventtracker.app.ui.newhost

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.R
import com.eventtracker.app.databinding.NewHostActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import java.io.File
import java.io.IOException
import java.util.*
import javax.inject.Inject

class NewHostActivity: DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: NewHostActivityBinding
    private val viewModel: NewHostViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[NewHostViewModel::class.java]
    }

    private lateinit var currentAvatarPath: String

    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        binding = DataBindingUtil.setContentView(this, R.layout.new_host_activity)
        binding.viewModel = viewModel

        binding.buttonSelectPhoto.setOnClickListener {
            val options = arrayOf<CharSequence>(getString(R.string.take_photo), getString(R.string.choose_from_gallery), getString(R.string.cancel))

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Choose an avatar")

            builder.setItems(options) { dialog, item ->
                when {
                    options[item] == getString(R.string.take_photo) -> {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 12);
                        }

                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 12);
                        }

                        val takePicture = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        if (takePicture.resolveActivity(packageManager) != null) {
                            val photoFile: File? = try {
                                createImageFile()
                            } catch (ex: IOException) {
                                null
                            }
                            if (photoFile != null) {
                                val photoURI: Uri = FileProvider.getUriForFile(
                                    this,
                                    "com.eventtracker.app.utils.FileProvider",
                                    photoFile
                                )
                                takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            }
                        }
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
            var bitmap: Bitmap? = null
            when (requestCode) {
                0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(File(currentAvatarPath)))
                    binding.viewModel!!.avatarUri = currentAvatarPath
                }
                1 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val selectedPhoto = data.data
                    binding.viewModel!!.avatarUri = selectedPhoto.toString()
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhoto)
                }
            }

            binding.imageViewSelectedPhoto.setImageBitmap(bitmap)
            binding.buttonSelectPhoto.alpha = 0f
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "temp_${UUID.randomUUID()}",
            ".jpg",
            storageDir
        ).apply {
            currentAvatarPath = absolutePath
        }
    }
}