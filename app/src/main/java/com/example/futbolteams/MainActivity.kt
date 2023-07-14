package com.example.futbolteams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ClubAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bindeo del recyclerview
        recyclerView = findViewById(R.id.recyclerViewTeams)

        //Le decimos que es un recyclerView de tipo lineal
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Adapter
        adapter = ClubAdapter(applicationContext)
        recyclerView.adapter = adapter
        adapter.submitList(getClubList())
        adapter.onItemClickListener = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it.name)
            intent.putExtra("url", it.url)
            startActivity(intent)
        }
    }
    private fun getClubList(): MutableList<Club>? {
        return mutableListOf(
            Club(1, "River Plate", 1910, "https://upload.wikimedia.org/wikipedia/commons/3/3f/Logo_River_Plate.png"),
            Club(2, "Boca Junior", 1900, "https://upload.wikimedia.org/wikipedia/commons/d/d1/Escudo_del_Club_Atl%C3%A9tico_Boca_Juniors_2009.png"),
            Club(3, "Velez Sarsfield", 1910, "")
        )
    }
}