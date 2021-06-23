# Covid19-Tracker App

Covid-19 Tracker is an android application which displays the statistics of Covid-19 in India. In this app, you can also find the vaccine centers with vaccine availability. It asks the user for their area pincode and pick the date on which user wants to check vaccine availability and returns the data such as vaccination center name, address, center timings, vaccine name, vaccination fees, and no. of vaccines available.

## Project Structure
- For displaying covid-19 stats
  - CovidData.kt (Model Class for storing information of the Covid stats for RecyclerView)
  - CovidStatsAdapter.kt (for creating RecyclerView)
  
- For checking availability of vaccine centers
  - VaccineCenterData.kt (Model Class for storing information about vaccination center for RecyclerView)
  - VaccineCenterAdapter.kt (for creating RecyclerView)
  
- MainActivity.kt (Starting the app and creating the view)
- MySingleton.kt (To implement Singleton design pattern - restrict restricts the instantiation of a class to one single instance)

## API Used
- https://api.apify.com/v2/key-value-stores/toDWvRj1JpTXiM8FF/records/LATEST?disableRedirect=true
- https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=$pinCode&date=$date

## App Screenshots
<p float="left">
<img src="https://user-images.githubusercontent.com/33342767/123138883-2f9bcf80-d473-11eb-98a3-6cd046a1c58d.png"  width="250" height="450">
<img src="https://user-images.githubusercontent.com/33342767/123138917-3aeefb00-d473-11eb-819f-a61b3480e004.png"  width="250" height="450">
<img src="https://user-images.githubusercontent.com/33342767/123138940-43dfcc80-d473-11eb-9858-789a70f758dc.png"  width="250" height="450">
<img src="https://user-images.githubusercontent.com/33342767/123138975-4b9f7100-d473-11eb-8e25-1c2cdec6be35.png"  width="250" height="450">
<img src="https://user-images.githubusercontent.com/33342767/123138998-53f7ac00-d473-11eb-9285-6c7f518ae01a.png"  width="250" height="450">
</p> 
