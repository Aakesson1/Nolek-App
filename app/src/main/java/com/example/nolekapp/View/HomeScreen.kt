import android.content.Intent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.nolekapp.MainActivity
import com.example.nolekapp.MenuActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomLayout() {
    val context = LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // The START button aligned to the top-start of the screen
        Button(
            onClick = {
                val intent = Intent(context, MenuActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text("START")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(65.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Placeholder for image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(2.dp, color = Color.Black)
            ) {
                Text("IMG", modifier = Modifier.align(Alignment.Center))
            }

            // Import image button
            Button(onClick = {}) {
                Text("import img")
            }

            // Input for "hvor mange spørgsmål?"
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("hvor mange punkter?") }
            )

            // Input for "spørgsmål"
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("spørgsmål") }
            )

            // OK and "Ikke ok" buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {}) {
                    Text("OK")
                }

                Button(onClick = {}) {
                    Text("Ikke ok")
                }

            }

        }

    }
}
@Preview
@Composable
fun PreviewCustomLayout() {
    CustomLayout()
}
