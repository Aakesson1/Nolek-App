package com.example.nolekapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nolekapp.Model.AppEvent
import com.example.nolekapp.Database.Dao.TestResultatDao
import com.example.nolekapp.Model.SortType
import com.example.nolekapp.Model.TestResultatState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.nolekapp.Database.Data.TestResultat
import kotlinx.coroutines.flow.*

class TestResultatViewModel(private val dao: TestResultatDao): ViewModel() {

    private val _sorttype = MutableStateFlow(SortType.NAME)
    private val _testResultat = _sorttype
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.OBJECT_TYPE -> dao.getallTestResultat()
                SortType.NAME -> dao.getallTestResultatOrderedByName()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(TestResultatState())
    val state = combine(_state, _sorttype, _testResultat) { state, sortType, testresultat -> state.copy(
        sortType = sortType,
        testResultat = testresultat
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TestResultatState())


    fun onEvent(event: AppEvent){
        when(event){
            is AppEvent.DeleteTestResultat -> {
                viewModelScope.launch {
                    dao.deleteTestResultat(event.testResultat)
                }

            }
            AppEvent.HideDialog -> {
                _state.update { it.copy(isAddingTestResultat = false) }
            }
            AppEvent.SaveTestResultat -> {
                val Name = state.value.Name
                val Description = state.value.Description
                val sniffing_point = state.value.sniffing_point
                val Objecttype = state.value.Objecttype

                if (Name.isBlank() ||Description.isBlank() || sniffing_point.isBlank() || Objecttype.isBlank()){
                    return
                }

                    val testResultat = TestResultat(
                        Name = Name,
                        Description = Description,
                        sniffing_point = sniffing_point,
                        Objecttype = Objecttype
                    )
                viewModelScope.launch { dao.upsertTestresultat(testResultat) }
                _state.update { it.copy(
                    isAddingTestResultat = false,
                    Name = "",
                    Description = "",
                    sniffing_point = "",
                    Objecttype = ""

                ) }
            }
            is AppEvent.SetDescription -> {
                _state.update { it.copy(
                    Description = event.Description
                ) }
            }
            is AppEvent.SetName -> {
                _state.update { it.copy(
                    Name = event.Name
                ) }
            }
            is AppEvent.SetObjectType -> {
                _state.update { it.copy(
                    Objecttype = event.Objecttype
                ) }
            }
            is AppEvent.SetSnifftingPoint -> {
                _state.update { it.copy(
                    sniffing_point= event.sniffing_point
                ) }
            }
            AppEvent.ShowDialog -> {
                _state.update { it.copy(isAddingTestResultat = true) }
            }
            is AppEvent.SortTestResultat -> {
                _sorttype.value = event.sortType
            }
        }
    }
}