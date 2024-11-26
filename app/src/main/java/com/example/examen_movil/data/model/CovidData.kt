package com.example.examen_movil.data.model

data class CovidData(
    val country: String,
    val region: String,
    val cases: Map<String, CaseDetails>
)

data class CaseDetails(
    val total: Int,
    val new: Int
)


