package com.example.covid_19stats

data class CovidData(

    val state: String,
    val activeCases: Int,
    val newActiveCases: Int,
    val totalRecovered: Int,
    val newRecovered: Int,
    val totalDeaths: Int,
    val newDeaths: Int,
    val totalCases: Int
)