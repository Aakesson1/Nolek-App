package com.example.nolekapp.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nolekapp.Model.AppEvent
import com.example.nolekapp.Model.SortType
import com.example.nolekapp.Model.TestResultatState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestResultatScreen(
    state: TestResultatState,
    onEvent: (AppEvent) -> Unit,
    navigateToMenu: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(AppEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Test Result"
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text("Test Resultat")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigateToMenu()
                    }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
    ) { contentPadding  ->
        if(state.isAddingTestResultat) {
            AddTestResultatDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(AppEvent.SortTestResultat(sortType))
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(AppEvent.SortTestResultat(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            state.testResultat.let { testResultats ->
                items(state.testResultat) { testresultat ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Name: ${testresultat.name}\n" +
                                        "Id: ${testresultat.reason}\n" +
                                        "Test Point: ${testresultat.sniffingPoint}\n" +
                                        "Status: ${testresultat.status}\n" +
                                        "Beskrivelse: ${testresultat.description}\n" +
                                        "Object Type: ${testresultat.objectType}",
                                fontSize = 20.sp
                            )

                        }
                        IconButton(onClick = {
                            onEvent(AppEvent.DeleteTestResultat(testresultat._id))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete contact"
                            )
                        }
                    }
                }
            }
    }
}

}