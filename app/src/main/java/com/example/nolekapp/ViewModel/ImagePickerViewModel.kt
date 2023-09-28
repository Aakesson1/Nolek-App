package com.example.nolekapp.ViewModel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.ViewModel

class ImagePickerViewModel : ViewModel() {
    private companion object {
        const val BILLEDE_VAELGER_RESULTAT = 1
    }

    fun handleImportButtonClick(context: Context) {
        Log.d("MyApp", "handleImportButtonClick called")
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        (context as Activity).startActivityForResult(intent, BILLEDE_VAELGER_RESULTAT)
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
