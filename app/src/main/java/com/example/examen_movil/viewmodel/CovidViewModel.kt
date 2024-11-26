package com.example.examen_movil.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examen_movil.data.model.CovidData
import com.example.examen_movil.data.model.CovidRepository
import kotlinx.coroutines.launch

class CovidViewModel(private val repository: CovidRepository) : ViewModel() {

    private val _covidData = MutableLiveData<List<CovidData>>()
    val covidData: LiveData<List<CovidData>> = _covidData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCovidData(country: String) {
        viewModelScope.launch {
            try {
                val data = repository.getCovidData(country)
                if (data.isNotEmpty()) {
                    _covidData.value = data
                } else {
                    _error.value = "País no encontrado o no hay datos disponibles."
                }
            } catch (e: Exception) {
                _error.value = "Error al obtener datos: ${e.message}"
            }
        }
    }
}
