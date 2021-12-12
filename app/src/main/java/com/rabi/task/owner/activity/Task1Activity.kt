package com.rabi.task.owner.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.rabi.task.*
import com.rabi.task.databinding.ActivityTask1Binding
import com.rabi.task.db.Pref
import com.rabi.task.model.Section
import com.rabi.task.owner.adapter.SectionAdapter

class Task1Activity : AppCompatActivity() {
    lateinit var binding: ActivityTask1Binding
    private lateinit var  mapper : ObjectMapper
    var list : MutableList<Section> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar(binding.toolbar,true)
        mapper = jacksonObjectMapper()

        val jsonString:String? = Pref(this).getString(Pref.Key.DATA,"")
        if(!jsonString.isNullOrEmpty()){
            val data: List<Section> = mapper.readValue(jsonString)
            list = data.toMutableList()
            Log.e("TAG","List: $list")
        }

        with(binding.content){
            recyclerView.layoutManager = LinearLayoutManager(this@Task1Activity)
            setSectionAdapter(list)
            btnAdd.setOnClickListener {
                btnAdd.gone()
                addSection.visible()
            }



            btnSave.setOnClickListener {
                val secName = sectionEdit.text.toString().trim()
                if (secName.isEmpty()) {
                    sectionEdit.error = "Lecture name is empty"
                } else {
                    binding.progressBar.visible()
                    val s = Section(
                        list.size + 1,
                        secName,
                        emptyList()
                    )

                    list.add(s)
                    addSection.gone()
                    sectionEdit.setText("")
                    setSectionAdapter(list)
                    btnAdd.visible()
                    val json = mapper.writeValueAsString(list)
                    Pref(this@Task1Activity).setString(Pref.Key.DATA,json)
                    binding.progressBar.gone()
                    hideKeyboard()
                }
            }


            btnCancel.setOnClickListener {
                btnAdd.visible()
                addSection.gone()
                hideKeyboard()
            }

        }
    }


    private fun setSectionAdapter(l: MutableList<Section>){
        l.sortBy { it.id }
        if(l.isNotEmpty()){
            binding.content.empty.gone()
            SectionAdapter(l){ action,section, oldSection ->
                when(action){
                    "save" -> {
                        l.remove(oldSection)
                        l.add(section)
                        setSectionAdapter(l)
                        val json = mapper.writeValueAsString(l)
                        Pref(this).setString(Pref.Key.DATA,json)
                    }
                }
            }.also { adapter ->
                binding.content.recyclerView.adapter = adapter

            }
        }else binding.content.empty.visible()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task1, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_section -> {
                with(binding.content){
                    btnAdd.gone()
                    addSection.visible()
                    recyclerView.scrollToPosition(list.size-1)
                    Log.e("TAG","${list.size-1}")
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        attachBaseContextUtil(newBase)
    }
}