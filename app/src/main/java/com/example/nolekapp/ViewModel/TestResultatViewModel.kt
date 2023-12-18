package com.example.nolekapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nolekapp.Database.Data.TestResultat
import com.example.nolekapp.Database.Data.TestResultatRepository
import com.example.nolekapp.Model.AppEvent
import com.example.nolekapp.Model.SortType
import com.example.nolekapp.Model.TestResultatState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class TestResultatViewModel @Inject constructor(private val repository: TestResultatRepository) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.NAME)
    private val _testResultater: Flow<List<TestResultat>> = repository.getData()

    private val _state = MutableStateFlow(TestResultatState())
    val state: StateFlow<TestResultatState> = combine(
        _state, _sortType, _testResultater
    ) { state, sortType, testResultater ->
        state.copy(
            sortType = sortType,
            testResultat = testResultater
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TestResultatState())

    fun onEvent(event: AppEvent) {
        when (event) {
            is AppEvent.DeleteTestResultat -> {
                viewModelScope.launch {
                    repository.deleteTestResultat(event.testResultat)
                }
            }
            AppEvent.HideDialog -> {
                _state.update { it.copy(isAddingTestResultat = false) }
            }

            AppEvent.SaveTestResultat -> {
                viewModelScope.launch {
                    val name = state.value.Name
                    val description = state.value.Description
                    val sniffingPoint = state.value.sniffing_point
                    val objectType = state.value.Objecttype
                    val reason = state.value.EmployeeId
                    val status= state.value.Status

                    val newTestResultat = TestResultat().apply {
                        this.name = name
                        this.description = description
                        this.sniffingPoint = sniffingPoint
                        this.objectType = objectType
                        this.reason = reason
                        this.status = status
                    }
                    repository.insertTestResultat(newTestResultat)
                    _state.update {
                        it.copy(
                            isAddingTestResultat = false,
                            Name = "",
                            Description = "",
                            sniffing_point = "",
                            EmployeeId = "",
                            Status = "",
                            Objecttype = ""
                        )
                    }
                }
            }

            is AppEvent.SetDescription -> {
                _state.update {
                    it.copy(
                        Description = event.Description
                    )
                }
            }

            is AppEvent.SetName -> {
                _state.update {
                    it.copy(
                        Name = event.Name
                    )
                }
            }

            is AppEvent.SetObjectType -> {
                _state.update {
                    it.copy(
                        Objecttype = event.Objecttype
                    )
                }
            }

            is AppEvent.SetSnifftingPoint -> {
                _state.update {
                    it.copy(
                        sniffing_point = event.sniffing_point
                    )
                }
            }

            is AppEvent.SetReason -> {
                _state.update {
                    it.copy(
                        EmployeeId = event.Reason
                    )
                }
            }

            is AppEvent.SetStatus -> {
                _state.update {
                    it.copy(
                        Status = event.Status
                    )
                }
            }

            AppEvent.ShowDialog -> {
                _state.update { it.copy(isAddingTestResultat = true) }
            }

            is AppEvent.SortTestResultat -> {
                _sortType.value = event.sortType
            }
        }
    }
}