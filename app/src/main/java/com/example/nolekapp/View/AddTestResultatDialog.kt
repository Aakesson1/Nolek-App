package com.example.nolekapp.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nolekapp.Model.AppEvent
import com.example.nolekapp.Model.TestResultatState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTestResultatDialog(
    state: TestResultatState,
    onEvent: (AppEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(AppEvent.HideDialog)
        },
        title = { Text(text = "Add Test Resultat") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.Name.toString(),
                    onValueChange = {
                        onEvent(AppEvent.SetName(it))
                    },
                    placeholder = {
                        Text(text = "Name")
                    }
                )
                TextField(
                    value = state.Description.toString(),
                    onValueChange = {
                        onEvent(AppEvent.SetDescription(it))
                    },
                    placeholder = {
                        Text(text = "Description")
                    }
                )
                TextField(
                    value = state.sniffing_point.toString(),
                    onValueChange = {
                        onEvent(AppEvent.SetSnifftingPoint(it))
                    },
                    placeholder = {
                        Text(text = "sniffing point")
                    }
                )
                TextField(
                    value = state.Objecttype.toString(),
                    onValueChange = {
                        onEvent(AppEvent.SetObjectType(it))
                    },
                    placeholder = {
                        Text(text = "Object Type")
                    }
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(AppEvent.SaveTestResultat)
                }) {
                    Text(text = "Save")
                }
            }
        }
    )
}