package com.example.nolekapp

import android.media.MediaScannerConnection
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.example.nolekapp.View.CameraScreen
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class CameraActivityCompose : AppCompatActivity() {
    private lateinit var cameraExecutor: ExecutorService
    var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        imageCapture = ImageCapture.Builder().build()
        setContent {
            CameraScreen(
                imageCapture = imageCapture ?: return@setContent,
                onCapture = { takePicture() },
                onBackPressed = { finish() }
            )
        }
    }

    private fun takePicture() {
        val localImageCapture = imageCapture ?: return

        val photoFile = createFile(getOutputDirectory(), FILENAME_FORMAT, PHOTO_EXTENSION)
        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        localImageCapture.takePicture(
            outputFileOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: Uri.fromFile(photoFile)
                    val msg = "Photo captured"
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    notifyMediaScanner(photoFile)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(baseContext, "Photo capture failed: ${exception.message}", Toast.LENGTH_SHORT).show()
                    exception.printStackTrace()
                }
            }
        )
    }

    private fun notifyMediaScanner(photoFile: File) {
        MediaScannerConnection.scanFile(this, arrayOf(photoFile.toString()), null, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
    companion object {
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val PHOTO_EXTENSION = ".jpg"
        // Yderligere konstanter, hvis nødvendigt
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    }
    private fun createFile(baseFolder: File, format: String, extension: String) = File(baseFolder, SimpleDateFormat(format, Locale.US)
        .format(System.currentTimeMillis()) + extension)
}

 