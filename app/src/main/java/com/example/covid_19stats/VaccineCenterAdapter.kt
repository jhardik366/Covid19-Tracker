package com.example.covid_19stats

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VaccineCenterAdapter(private val vaccineCenterList: List<VaccineCenterData>) : RecyclerView.Adapter<VaccineCenterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaccineCenterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vaccine_center_list, parent, false)
        return VaccineCenterViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VaccineCenterViewHolder, position: Int) {
        val currentItem = vaccineCenterList[position]
        holder.vaccineCenterName.text = currentItem.vaccineCenterName
        holder.vaccineCenterAddress.text = currentItem.vaccineCenterAddress
        holder.vaccineCenterTiming.text = "From : ${currentItem.vaccineCenterStartTime} To : ${currentItem.vaccineCenterEndTime}"
        holder.vaccineName.text = currentItem.vaccineName
        holder.vaccineFees.text = currentItem.vaccineFees
        holder.vaccineAgeLimit.text = "Age Limit : ${currentItem.vaccineAgeLimit}+"
        holder.vaccineAvailability.text = "Availablity : ${currentItem.vaccineAvailable}+"
    }

    override fun getItemCount(): Int {
        return vaccineCenterList.size
    }
}

class VaccineCenterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val vaccineCenterName: TextView = itemView.findViewById(R.id.vaccineCenterName)
    val vaccineCenterAddress: TextView = itemView.findViewById(R.id.vaccineCenterAddress)
    val vaccineCenterTiming: TextView = itemView.findViewById(R.id.vaccineCenterTiming)
    val vaccineName: TextView = itemView.findViewById(R.id.vaccineName)
    val vaccineFees: TextView = itemView.findViewById(R.id.vaccineFees)
    val vaccineAgeLimit: TextView = itemView.findViewById(R.id.vaccineAgeLimit)
    val vaccineAvailability: TextView = itemView.findViewById(R.id.vaccineAvailability)
}