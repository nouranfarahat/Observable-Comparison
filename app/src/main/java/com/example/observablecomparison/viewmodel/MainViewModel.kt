package com.example.observablecomparison.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _liveDataText=MutableLiveData<String>()
    val liveDlataText: LiveData<String> = _liveDataText

    private val _stateFlowText=MutableStateFlow<String>("Hello World!")
    val stateFlowText:StateFlow<String>
        get() = _stateFlowText

    private val _sharedFlowText=MutableSharedFlow<String>()
    val sharedFlowText:SharedFlow<String>
        get() = _sharedFlowText

    fun triggerLiveData(){
            _liveDataText.value="From Live Data"
    }
    fun triggerStateFlow(){
        _stateFlowText.value="From State Flow"

    }
    fun triggerSharedFlow(){
        viewModelScope.launch {
            _sharedFlowText.emit("From Shared Flow")
        }
    }
    fun triggerFlow(): Flow<String>  = flow{
           emit("From Flow")
        }

}