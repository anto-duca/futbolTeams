package com.example.futbolteams

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ClubAdapter(val context: Context) : ListAdapter<Club, ClubAdapter.ViewHolder>(DiffCallBack) {

    lateinit var onItemClickListener: (Club) -> Unit

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        //BINDEO
        private val name: TextView = view.findViewById(R.id.textViewClub)
        private val year : TextView = view.findViewById(R.id.textViewYear)
        private val imageClub: ImageView = view.findViewById(R.id.imageViewTeam)

        fun  bind (equipo: Club) {
            //TEXTS
            name.text = "Nombre: " + equipo.name
            year.text = "Año " + equipo.year.toString() // .toString(), porque es un tipo de dato INT

            //GLIDE
            Glide.with(context)
                .load(equipo.url)
                .into(imageClub)

            view.setOnClickListener {
                onItemClickListener(equipo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubAdapter.ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClubAdapter.ViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.bind(pokemon)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Club>() {
        override fun areItemsTheSame(oldItem: Club, newItem: Club): Boolean {
            return  oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Club, newItem: Club): Boolean {
            return oldItem == newItem
        }
    }
}