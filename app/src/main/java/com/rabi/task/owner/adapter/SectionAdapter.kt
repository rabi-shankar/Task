package com.rabi.task.owner.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rabi.task.R
import com.rabi.task.databinding.ItemSectionBinding
import com.rabi.task.gone
import com.rabi.task.model.Lecture
import com.rabi.task.model.Section
import com.rabi.task.visible

class SectionAdapter(
    private val list: List<Section>,
    private val action: (String, Section, Section) -> Unit
) : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSectionBinding.bind(view)
        fun bind(section: Section) {
            with(binding) {
                val oldSection = section
                sectionText.text =
                    view.context.getString(R.string.section_name, section.id, section.name)

                lectureRecyclerView.layoutManager = LinearLayoutManager(view.context)
                LectureAdapter(section.lectures).also { adapter ->
                    lectureRecyclerView.adapter = adapter
                }

                btnAdd.setOnClickListener {
                    addLecture.visible()
                }

                btnSave.setOnClickListener {
                    val lecName = lectureEdit.text.toString().trim()
                    if (lecName.isEmpty()) {
                        lectureEdit.error = "Lecture name is empty"
                    } else {
                        val nList: MutableList<Lecture> = section.lectures.toMutableList()
                        val l = Lecture(
                            section.lectures.size + 1,
                            lecName,
                            section.id
                        )

                        nList.add(l)
                        addLecture.gone()
                        lectureEdit.setText("")
                        section.lectures = nList
                        action.invoke("save", section, oldSection)
                    }
                }

                btnCancel.setOnClickListener {
                    addLecture.gone()
                    lectureEdit.setText("")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_section, parent, false)
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

}