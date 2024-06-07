package com.example.movieappmad24

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {

    private var preview: Preview? = null
    private var imageCapture: ImageCapture? = null

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. You can now initialize CameraX or any camera-related operations.
                Toast.makeText(this, "Camera permission granted.", Toast.LENGTH_SHORT).show()
            } else {
                // Explain to the user that the feature is unavailable because the
                // features require a permission that the user has denied.
                Toast.makeText(this, "Camera permission is required to use this feature.", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                val navController = rememberNavController()

                // Request camera permission if not already granted
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                } else {
                    // Permission already granted, initialize camera here
                    val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        val previewConfig = Preview.Builder().build()
                        val imageCaptureConfig = ImageCapture.Builder().build()

                        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                        preview = previewConfig
                        imageCapture = imageCaptureConfig

                        try {
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                this,
                                cameraSelector,
                                previewConfig,
                                imageCaptureConfig
                            )
                        } catch (exc: Exception) {
                            // Handle error here, such as showing a toast message
                            Toast.makeText(this, "Error initializing camera: ${exc.message}", Toast.LENGTH_SHORT).show()
                        }
                    }, ContextCompat.getMainExecutor(this))

                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(navController = navController)
                }
            }
        }
    }
}

