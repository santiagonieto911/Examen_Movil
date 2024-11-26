package com.example.examen_movil.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examen_movil.data.model.CovidRepository
import com.example.examen_movil.data.model.CovidRepositoryImpl

class CovidViewModelFactory(private val repository: CovidRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CovidViewModel::class.java)) {
            return CovidViewModel(repository as CovidRepositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}