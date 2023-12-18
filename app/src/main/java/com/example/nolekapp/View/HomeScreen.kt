import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.widget.EditText
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.nolekapp.MenuActivity
import com.example.nolekapp.ViewModel.StatusViewModel
import androidx.compose.runtime.livedata.observeAsState
import coil.compose.rememberAsyncImagePainter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLayout(statusViewModel: StatusViewModel,navigateToMenu: () -> Unit) {
    val context = LocalContext.current

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var expanded by remember { mutableStateOf(false) }
    val pointsList = (1..15).map { it.toString() }
    var selectedPoint by remember { mutableStateOf(pointsList[0]) }
    var isApproved by remember { mutableStateOf(false) }
    val currentQuestion = statusViewModel.currentQuestion.observeAsState().value

    val imagePicker =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                selectedImageUri = result.data?.data
            }
        }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Test Screen") },
                navigationIcon = {
                    IconButton(onClick = { navigateToMenu() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier =
            Modifier.fillMaxSize().padding(innerPadding)
        ) {
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(65.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .border(2.dp, color = Color.Black)
                ) {
                    if (selectedImageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = selectedImageUri),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Text("IMG", modifier = Modifier.align(Alignment.Center))
                    }
                }

                Button(onClick = {
                    imagePicker.launch(Intent(Intent.ACTION_GET_CONTENT).apply {
                        type = "image/*"
                    })
                }) {
                    Text("import img")
                }

                Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        OutlinedButton(onClick = { expanded = true }) {
                            Text(text = selectedPoint)
                        }
                        Button(onClick = { isApproved = true }) {
                            Text("Godkend")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        pointsList.forEach { point ->
                            OutlinedButton(onClick = {
                                selectedPoint = point
                                expanded = false
                                val points = point.toIntOrNull()
                                if (points != null) {
                                    statusViewModel.setQuestionsBasedOnPoints(points)
                                }
                            }) {
                                Text(text = point)
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray)
                        .padding(16.dp)
                ) {
                    if (isApproved) {
                        Text(text = currentQuestion ?: "")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { statusViewModel.showNextQuestion() }) {
                        Text("OK")
                    }

                    Button(onClick = {
                        val input = EditText(context)
                        val builder = AlertDialog.Builder(context)
                            .setTitle("Angiv begrundelse")
                            .setView(input)
                            .setPositiveButton("OK") { _, _ ->
                                statusViewModel.showNextQuestion()
                            }
                            .setNegativeButton("Annuller") { dialog, _ ->
                                dialog.dismiss()
                            }
                        builder.show()
                    }) {
                        Text("Ikke ok")
                    }
                }
            }
        }
    }

