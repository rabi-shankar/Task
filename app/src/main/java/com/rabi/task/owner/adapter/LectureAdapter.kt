package com.rabi.task.owner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabi.task.R
import com.rabi.task.databinding.ItemLectureBinding
import com.rabi.task.model.Lecture

class LectureAdapter(
    private val list: List<Lecture>
) : RecyclerView.Adapter<LectureAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemLectureBinding.bind(view)
        fun bind(lecture: Lecture) {
            with(binding) {
                name.text = lecture.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_lecture, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

}