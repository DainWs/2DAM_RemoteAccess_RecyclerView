package com.joseduarte.direcyclerview_primertrimestre.ui

import android.content.Context
import android.graphics.drawable.TransitionDrawable
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.joseduarte.direcyclerview_primertrimestre.R
import com.joseduarte.direcyclerview_primertrimestre.ResourceLoader
import com.joseduarte.direcyclerview_primertrimestre.models.Models
import com.joseduarte.direcyclerview_primertrimestre.models.Person as Person

class MyItemRecyclerViewAdapter(
    private var values: List<Models>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.fragment_item, parent, false)
        this.parent = parent
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.idView.text = item.getID()
        holder.contentView.text = item.getData()
        holder.itemView.setOnTouchListener({ view: View, e: MotionEvent ->
            this.onTouchClickListener(holder.itemView, e, item)
        })
    }

    fun update(values: List<Models>) {
        this.values = values
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.item_identifier)
        val contentView: TextView = view.findViewById(R.id.item_description)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }


    private lateinit var transition: TransitionDrawable
    private var lastTouchTime: Long = 0
    private var lastTouchDurationTime: Long = 0
    fun onTouchClickListener(v: View, e: MotionEvent, item: Models): Boolean {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {

            transition = v.getBackground() as TransitionDrawable
            transition.startTransition(1000)
            lastTouchTime = System.currentTimeMillis()

        } else if (e.getAction() == MotionEvent.ACTION_UP || e.getAction() == MotionEvent.ACTION_CANCEL) {

            transition.resetTransition()
            lastTouchDurationTime = System.currentTimeMillis() - lastTouchTime
            if (lastTouchDurationTime > 1000) {
                onClick(item as Person)
            }
        }

        return true
    }

    fun onClick(m: Person) {
        var t: Toast = Toast.makeText(
            ResourceLoader.getContext(),
            ResourceLoader.getString(R.string.pern_id) + m.getPersonId(),
            Toast.LENGTH_LONG
        )
        t.show()
    }

}