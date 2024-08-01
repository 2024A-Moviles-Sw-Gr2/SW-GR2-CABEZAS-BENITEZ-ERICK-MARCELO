package com.example.myapplication
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StateAdapter(private val stateList: List<State>) : RecyclerView.Adapter<StateAdapter.StateViewHolder>() {

    class StateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewState: ImageView = itemView.findViewById(R.id.imageViewState)
        val textViewState: TextView = itemView.findViewById(R.id.textViewState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_state, parent, false)
        return StateViewHolder(view)
    }

    override fun onBindViewHolder(holder: StateViewHolder, position: Int) {
        val state = stateList[position]
        holder.imageViewState.setImageResource(state.image)
        holder.textViewState.text = state.name
    }

    override fun getItemCount(): Int {
        return stateList.size
    }
}

data class State(val image: Int, val name: String)
