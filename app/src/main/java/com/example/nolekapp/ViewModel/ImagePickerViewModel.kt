package com.example.nolekapp.ViewModel

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.AndroidViewModel


class ImagePickerViewModel(application: Application) : AndroidViewModel(application) {
    private companion object {
        const val BILLEDE_VAELGER_RESULTAT = 1
    }
    fun handleImportButtonClick(launcher: ActivityResultLauncher<Intent>) {
        Log.d("MyApp", "handleImportButtonClick called")
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        launcher.launch(intent)
    }

    fun handleActivityResult(requestCode: Int, resultCode: Int, data: Intent?, imageView: ImageView) {
        Log.d("MyApp", "handleActivityResult called")
        if (requestCode == BILLEDE_VAELGER_RESULTAT && resultCode == Activity.RESULT_OK && data != null) {
            val billedeUri: Uri? = data.data
            Log.d("MyApp", "Selected image URI: $billedeUri")


            imageView.setImageURI(billedeUri)
        }
    }

}
