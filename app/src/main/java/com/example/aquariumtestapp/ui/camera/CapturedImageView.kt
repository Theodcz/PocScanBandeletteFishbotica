
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter


import com.example.aquariumtestapp.data.viewModel.CameraViewModel
import com.example.aquariumtestapp.data.viewModel.StoreSelectedAquariumViewModel

@Composable
fun CapturedImageView(imageUri: Uri, onResult: (Boolean) -> Unit, onValidPost: (Boolean) -> Unit) {
    val context = LocalContext.current
    val storeSelectedAquariumViewModel = StoreSelectedAquariumViewModel(context)
    val cameraViewModel = CameraViewModel()


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = "Captured Image",
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp),
            horizontalArrangement = Arrangement.spacedBy(130.dp)

        ) {
            Button(
                onClick = { onResult(false) },

                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {

                Icon(
                    imageVector = Icons.Default.Close, // Icône de coche
                    contentDescription = "Valider",
                    tint = Color.White // Icône blanche
                )
            }
            Button(

                onClick = { onResult(true)
                    storeSelectedAquariumViewModel.getSelectedAquariumId()
                    val selectedAquarium = storeSelectedAquariumViewModel.selectedAquarium.value

                    if (selectedAquarium != null) {
                        cameraViewModel.uploadJpgImage(selectedAquarium.toInt(), imageUri, onValidPost)
                    }},
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),

                ) {

                Icon(
                    imageVector = Icons.Default.Check, // Icône de coche
                    contentDescription = "Valider",
                    tint = Color.White // Icône blanche
                )
            }
        }
    }
}
