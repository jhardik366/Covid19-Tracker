package com.example.covid_19stats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

    class CovidStatsAdapter() : RecyclerView.Adapter<StatsViewHolder>() {

    private val stats: ArrayList<CovidData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_state_list, parent, false)
        return StatsViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {

        val currentItem = stats[position]
        holder.state.text = currentItem.state
        holder.activeCases.text = currentItem.activeCases.toString()
        holder.newActiveCases.text = currentItem.newActiveCases.toString()
        holder.totalRecovered.text = currentItem.totalRecovered.toString()
        holder.newRecovered.text = currentItem.newRecovered.toString()
        holder.totalDeaths.text = currentItem.totalDeaths.toString()
        holder.newDeaths.text = currentItem.newDeaths.toString()
        holder.totalCases.text = currentItem.totalCases.toString()
    }

    override fun getItemCount(): Int {

        return stats.size
    }

    fun updateStats(updatedStats: ArrayList<CovidData>) {
        stats.clear()
        stats.addAll(updatedStats)
        notifyDataSetChanged()
    }
}

class StatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val state: TextView = itemView.findViewById(R.id.stateName)
    val activeCases: TextView = itemView.findViewById(R.id.stateActiveCase)
    val newActiveCases: TextView = itemView.findViewById(R.id.stateNewCase)
    val totalRecovered: TextView = itemView.findViewById(R.id.stateTotalRecovered)
    val newRecovered: TextView = itemView.findViewById(R.id.stateNewRecovered)
    val totalDeaths: TextView = itemView.findViewById(R.id.stateTotalDeaths)
    val newDeaths: TextView = itemView.findViewById(R.id.stateNewDeaths)
    val totalCases: TextView = itemView.findViewById(R.id.stateTotalCases)
}