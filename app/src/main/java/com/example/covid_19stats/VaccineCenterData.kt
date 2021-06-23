package com.example.covid_19stats

data class VaccineCenterData(
    val vaccineCenterName: String,
    val vaccineCenterAddress: String,
    val vaccineCenterStartTime: String,
    var vaccineCenterEndTime: String,
    var vaccineFees: String,
    var vaccineAgeLimit: Int,
    var vaccineName: String,
    var vaccineAvailable: Int
)