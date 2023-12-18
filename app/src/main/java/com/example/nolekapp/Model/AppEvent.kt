package com.example.nolekapp.Model

import org.mongodb.kbson.ObjectId

sealed interface AppEvent {
    object SaveTestResultat: AppEvent
    data class SetName(val Name: String) : AppEvent
    data class SetDescription(val Description: String) : AppEvent
    data class SetSnifftingPoint(val sniffing_point: String) : AppEvent
    data class SetObjectType(val Objecttype: String) : AppEvent
    data class SetStatus(val Status: String) : AppEvent
    data class SetReason(val Reason: String) : AppEvent
    object ShowDialog: AppEvent
    object HideDialog: AppEvent
    data class SortTestResultat(val sortType: SortType): AppEvent
    data class DeleteTestResultat(val testResultat: ObjectId): AppEvent

}