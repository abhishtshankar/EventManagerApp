package com.abhisht28.eventmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventRVAdapter(private val context: Context, val listener: IEventAdapter): RecyclerView.Adapter<EventRVAdapter.EventViewHolder>() {

    val allEvents =ArrayList<Event>()

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView  = itemView.findViewById<TextView>(R.id.text)
        val textView1 = itemView.findViewById<TextView>(R.id.text_details)
        val textView2 = itemView.findViewById<TextView>(R.id.date_Details)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
         val viewHolder = EventViewHolder(LayoutInflater.from(context).inflate(R.layout.item_event, parent, false))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allEvents[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentEvent = allEvents[position]
        holder.textView.text = currentEvent.text
        holder.textView1.text = currentEvent.textDetails.toString()
        holder.textView2.text = currentEvent.dateDetails.toString()

    }

    override fun getItemCount(): Int {
        return allEvents.size
    }
    fun updateList(newList: List<Event>){
        allEvents.clear()
        allEvents.addAll(newList)

        notifyDataSetChanged()
    }
}
interface IEventAdapter{
    fun onItemClicked(event: Event)
}