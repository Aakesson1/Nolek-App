package com.example.nolekapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageFormat
import android.graphics.SurfaceTexture
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CaptureRequest
import android.media.ImageReader
import android.media.MediaScannerConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.view.Surface
import android.view.TextureView
import android.widget.Button
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CameraActivity : AppCompatActivity() {

    private var ourRequestCode: Int = 123

    private lateinit var cameraManager: CameraManager

    private lateinit var capReq: CaptureRequest.Builder
    lateinit var handler: Handler
    private lateinit var handlerThread: HandlerThread
    lateinit var textureView: TextureView
    lateinit var cameraCaptureSession: CameraCaptureSession
    lateinit var cameraDevice: CameraDevice
    lateinit var captureRequest: CaptureRequest
    lateinit var imageReader: ImageReader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val backbutton = findViewById<Button>(R.id.backButton)
        backbutton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        getPermissions()


        textureView = findViewById(R.id.textureView)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        handlerThread = HandlerThread("CameraThread")
        handlerThread.start()
        handler = Handler((handlerThread).looper)

        textureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener{
            override fun onSurfaceTextureAvailable(p0: SurfaceTexture, p1: Int, p2: Int) {
                openCamera()
            }

            override fun onSurfaceTextureSizeChanged(p0: SurfaceTexture, p1: Int, p2: Int) {

            }

            override fun onSurfaceTextureDestroyed(p0: SurfaceTexture): Boolean {
                return false
            }

            override fun onSurfaceTextureUpdated(p0: SurfaceTexture) {

            }
        }

        imageReader = ImageReader.newInstance(1080,1920, ImageFormat.JPEG, 1)
        imageReader.setOnImageAvailableListener({ p0 ->
            var image = p0?.acquireLatestImage()
            var buffer = image !!.planes[0].buffer
            var bytes = ByteArray(buffer.remaining())
            buffer.get(bytes)
            val currentDateAndTime = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(
                Date()
            )
            val fileName = "$currentDateAndTime.jpeg"

            var file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), fileName)
            var opStream = FileOutputStream(file)

            opStream.write(bytes)

            opStream.close()
            image.close()
            MediaScannerConnection.scanFile(
                this@CameraActivity,
                arrayOf(file.toString()),
                arrayOf("image/jpeg"),
                null
            )
            Toast.makeText(this@CameraActivity, "image captured", Toast.LENGTH_SHORT).show()
        },handler )

        findViewById<Button>(R.id.takePhoto).apply {
            setOnClickListener {
                capReq = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE)
                capReq.addTarget(imageReader.surface)
                cameraCaptureSession.capture(capReq.build(),null,null)
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        cameraDevice.close()
        handler.removeCallbacksAndMessages(null)
        handlerThread.quitSafely()
    }
    @SuppressLint("MissingPermission")
    fun openCamera(){
        val cameraIdList = cameraManager.cameraIdList
        val rearCameraId = cameraIdList.find {
            cameraManager.getCameraCharacteristics(it)
                .get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK
        }

        if (rearCameraId != null) {
            cameraManager.openCamera(rearCameraId, object : CameraDevice.StateCallback() {
                override fun onOpened(p0: CameraDevice) {
                    cameraDevice = p0

                    var capReq = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                    var surface= Surface(textureView.surfaceTexture)
                    capReq.addTarget(surface)

                    capReq.set(CaptureRequest.JPEG_ORIENTATION, 90)

                    cameraDevice.createCaptureSession(listOf(surface, imageReader.surface), object:
                        CameraCaptureSession.StateCallback() {
                        override fun onConfigured(p0: CameraCaptureSession) {
                            cameraCaptureSession = p0
                            cameraCaptureSession.setRepeatingRequest(capReq.build(),null,null)
                        }

                        override fun onConfigureFailed(p0: CameraCaptureSession) {

                        }
                    },handler)
                }

                override fun onDisconnected(p0: CameraDevice) {

                }

                override fun onError(p0: CameraDevice, p1: Int) {

                }
            },handler)
        } else {
            Toast.makeText(this, "No rear camera available", Toast.LENGTH_SHORT).show()
        }
    }
    private fun getPermissions(){
        var permissionsList = mutableListOf<String>()

        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(android.Manifest.permission.CAMERA)
        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            permissionsList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if(permissionsList.size > 0){
            requestPermissions(permissionsList.toTypedArray(),ourRequestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        grantResults.forEach {
            if (it != PackageManager.PERMISSION_GRANTED){
                getPermissions()
            }
        }
    }
}