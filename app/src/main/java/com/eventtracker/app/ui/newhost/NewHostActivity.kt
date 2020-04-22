package com.eventtracker.app.ui.newhost

import android.Manifest
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.eventtracker.app.R
import com.eventtracker.app.databinding.NewHostActivityBinding
import com.eventtracker.app.ui.MainActivity
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
            if (hasPermissions()) {
                selectPhotoDialog()
            }
        }

        binding.buttonSubmit.setOnClickListener {
            try {
                viewModel.validateInput()
                viewModel.onSubmit()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } catch (exception: Exception) {
                val animShake: ObjectAnimator = ObjectAnimator.ofFloat(it, "translationX", -5f, 5f)
                animShake.duration = 70
                animShake.interpolator = LinearInterpolator()
                animShake.repeatCount = 3
                animShake.repeatMode = ValueAnimator.REVERSE
                animShake.start()

                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (hasPermissions()) {
            selectPhotoDialog()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_CANCELED) {
            var bitmap: Bitmap? = null
            when (requestCode) {
                0 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(File(currentAvatarPath)))
                }
                1 -> if (resultCode == Activity.RESULT_OK && data != null) {
                    val selectedPhoto = data.data
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhoto)

                    var filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    if (selectedPhoto != null) {
                        var cursor = contentResolver.query(selectedPhoto, filePathColumn, null, null, null)
                        if (cursor != null) {
                            cursor.moveToFirst();
                            var columnIndex = cursor.getColumnIndex(filePathColumn[0])
                            currentAvatarPath = cursor.getString(columnIndex)
                            cursor.close()
                        }
                    }
                }
            }

            binding.viewModel!!.avatarUri = currentAvatarPath
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

    private fun selectPhotoDialog() {
        val options = arrayOf<CharSequence>(getString(R.string.take_photo), getString(R.string.choose_from_gallery), getString(R.string.cancel))

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose an avatar")

        builder.setItems(options) { dialog, item ->
            when {
                options[item] == getString(R.string.take_photo) -> {
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

    private fun hasPermissions(): Boolean {
        var permissionsToRequest = mutableListOf<String>()

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissionsToRequest.add(Manifest.permission.CAMERA)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 12)
        }

        return permissionsToRequest.isEmpty()
    }
}