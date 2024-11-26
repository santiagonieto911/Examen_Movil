package com.example.examen_movil.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_movil.R
import com.example.examen_movil.data.model.CovidData

class CovidAdapter(private val covidDataList: List<CovidData>) : RecyclerView.Adapter<CovidAdapter.CovidViewHolder>() {

    class CovidViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountry: TextView = itemView.findViewById(R.id.tvCountry)
        val tvCases: TextView = itemView.findViewById(R.id.tvCases)
        val tvDeaths: TextView = itemView.findViewById(R.id.tvDeaths)
        val tvRecovered: TextView = itemView.findViewById(R.id.tvRecovered)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CovidViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_covid, parent, false)
        return CovidViewHolder(view)
    }

    override fun onBindViewHolder(holder: CovidViewHolder, position: Int) {
        val data = covidDataList[position]

        // Asignar el país
        holder.tvCountry.text = data.country

        // Extraer la fecha más reciente de los datos de casos
        val latestDate = data.cases.keys.maxOrNull()
        val latestCases = latestDate?.let { data.cases[it] }

        // Mostrar los datos de la fecha más reciente (o "N/A" si no hay datos)
        holder.tvCases.text = "Total Cases: ${latestCases?.total ?: "N/A"}"
        holder.tvDeaths.text = "New Cases: ${latestCases?.new ?: "N/A"}"
        holder.tvRecovered.text = "Region: ${data.region?.ifBlank { "N/A" } ?: "N/A"}"

    }
    override fun getItemCount(): Int = covidDataList.size
}
