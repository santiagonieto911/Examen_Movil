package com.example.examen_movil.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_movil.R
import com.example.examen_movil.viewmodel.CovidViewModel
import com.example.examen_movil.data.model.CovidApiService
import com.example.examen_movil.data.model.CovidData
import com.example.examen_movil.data.model.CovidRepositoryImpl
import com.example.examen_movil.viewmodel.CovidViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: CovidViewModel by viewModels {
        CovidViewModelFactory(CovidRepositoryImpl(CovidApiService.create()))
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var covidAdapter: CovidAdapter
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCovid)
        recyclerView.layoutManager = LinearLayoutManager(this)
        covidAdapter = CovidAdapter(emptyList())
        recyclerView.adapter = covidAdapter

        // Configurar SearchView
        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    viewModel.fetchCovidData(query) // Realiza la búsqueda en la API
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Puedes implementar filtrado local si se necesita
                return false
            }
        })

        // Observa los datos del ViewModel
        viewModel.covidData.observe(this, Observer { data ->
            if (data.isNotEmpty()) {
                covidAdapter = CovidAdapter(data)
                recyclerView.adapter = covidAdapter
            } else {
                Toast.makeText(this, "No se encontraron datos.", Toast.LENGTH_SHORT).show()
            }
        })

        // Observa errores
        viewModel.error.observe(this, Observer { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_LONG).show()
        })
    }
}
