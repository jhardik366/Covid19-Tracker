package com.example.covid_19stats

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAdapter: CovidStatsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        fetchData()
        progressBar.visibility = View.VISIBLE
        mAdapter = CovidStatsAdapter()
        recyclerView.adapter = mAdapter
    }

    private fun fetchData() {

        val url = "https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                progressBar.visibility = View.GONE
                val activeCases = it.getInt("activeCases")
                val recoveredCases = it.getInt("recovered")
                val deaths = it.getInt("deaths")
                val totalCases = it.getInt("totalCases")
                overallActiveCases.text = activeCases.toString()
                overallRecoveredCases.text = recoveredCases.toString()
                overallDeaths.text = deaths.toString()
                overallCases.text = totalCases.toString()
                val lastUpdated: TextView = findViewById(R.id.lastUpdated)
                lastUpdated.text = it.getString("lastUpdatedAtApify")

                val statsJsonArray = it.getJSONArray("regionData")
                val statsArray = ArrayList<CovidData>()
                for (i in 0 until statsJsonArray.length()) {
                    val statsJsonObject = statsJsonArray.getJSONObject(i)
                    val stats = CovidData(
                        statsJsonObject.getString("region"),
                        statsJsonObject.getInt("activeCases"),
                        statsJsonObject.getInt("newInfected"),
                        statsJsonObject.getInt("recovered"),
                        statsJsonObject.getInt("newRecovered"),
                        statsJsonObject.getInt("deceased"),
                        statsJsonObject.getInt("newDeceased"),
                        statsJsonObject.getInt("totalInfected")
                    )
                    statsArray.add(stats)
                }
                mAdapter.updateStats(statsArray)
            },
            {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.find_vaccine, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.findVaccine) {
            val newIntent = Intent(this, FindVaccineActivity::class.java)
            startActivity(newIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}