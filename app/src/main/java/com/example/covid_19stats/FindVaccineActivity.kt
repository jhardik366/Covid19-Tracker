package com.example.covid_19stats

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.vaccine_center_activity.*
import org.json.JSONException
import java.util.*

@Suppress("NAME_SHADOWING")
class FindVaccineActivity : AppCompatActivity() {

    private lateinit var vaccineCenterList: MutableList<VaccineCenterData>
    private lateinit var vaccineCenterAdapter: VaccineCenterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vaccine_center_activity)

        vaccineCenterList = mutableListOf()
        val vaccineCenterRecyclerView: RecyclerView = findViewById(R.id.vaccineCenterRecyclerView)
        vaccineCenterRecyclerView.layoutManager = LinearLayoutManager(this)

        searchVaccinationCenterButton.setOnClickListener {
            closeKeyboard()
            val pinCode = pinCode.editableText.toString()
            if (pinCode.length != 6) {
                Toast.makeText(this, "Please enter a valid pincode", Toast.LENGTH_LONG).show()
            } else {
                vaccineCenterList.clear()

                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)

                val dpd = DatePickerDialog(
                    this, { _, year, monthOfYear, dayOfMonth ->
                        val dateStr = """$dayOfMonth-${monthOfYear + 1}-${year}"""

                        getAppointment(pinCode, dateStr)
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        }
    }

    private fun getAppointment(pincode: String, date: String) {

        val url =
            "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=$pincode&date=$date"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->
            Log.e("request", "Success response is $response")
            try {
                val centerArray = response.getJSONArray("centers")
                if (centerArray.length() == 0) {
                    Toast.makeText(this, "No centers available", Toast.LENGTH_SHORT).show()
                }
                for (i in 0 until centerArray.length()) {
                    val centerObj = centerArray.getJSONObject(i)
                    val vaccineCenterName = centerObj.getString("name")
                    val vaccineCenterAddress = centerObj.getString("address")
                    val vaccineCenterStartTime = centerObj.getString("from")
                    val vaccineCenterEndTime = centerObj.getString("to")
                    val vaccineFees = centerObj.getString("fee_type")

                    val sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0)
                    val vaccineAgeLimit = sessionObj.getInt("min_age_limit")
                    val vaccineName = sessionObj.getString("vaccine")
                    val vaccineAvailable = sessionObj.getInt("available_capacity")

                    val vaccineCenter = VaccineCenterData(
                        vaccineCenterName,
                        vaccineCenterAddress,
                        vaccineCenterStartTime,
                        vaccineCenterEndTime,
                        vaccineFees,
                        vaccineAgeLimit,
                        vaccineName,
                        vaccineAvailable
                    )
                    vaccineCenterList.add(vaccineCenter)
                }
                vaccineCenterAdapter = VaccineCenterAdapter(vaccineCenterList)
                vaccineCenterRecyclerView.adapter = vaccineCenterAdapter
                vaccineCenterRecyclerView.setHasFixedSize(true)
                vaccineCenterAdapter.notifyDataSetChanged()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, { error ->
            Log.e("Error", "Response is $error")
            Toast.makeText(this, "Failed to get response", Toast.LENGTH_SHORT).show()
        })
        queue.add(jsonObjectRequest)
    }

    private fun closeKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}