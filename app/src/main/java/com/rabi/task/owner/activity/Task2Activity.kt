package com.rabi.task.owner.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.rabi.task.*
import com.rabi.task.databinding.ActivityTask2Binding
import java.lang.NumberFormatException

class Task2Activity : AppCompatActivity() {
    lateinit var binding: ActivityTask2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar(binding.toolbar,true)



        with(binding.content){
            seriesView.text = 23.getSeries()
            btnEnter.setOnClickListener {
                hideKeyboard()
                indexHint.text = getString(R.string.processing)
                val index = indexEdit.text.toString().trim()
                if(index.isNotEmpty()){
                    try {
                        number.text = index.toInt().getSeriesNumber().toString()
                        seriesView.text = index.toInt().getSeries()
                    }catch (e:NumberFormatException){
                        indexEdit.setText("")
                        indexEdit.error = "Index range(0,10000)"
                        Log.e("TAG", e.message.toString())
                    }
                    indexHint.text = getString(R.string.index_0_to)
                }
            }
        }

    }

    private fun Int.getSeriesNumber(): Int{
        val x = (this + 1)*(this + 1)
        return if((this + 1) % 2 == 0) x - 1 else x + 1
    }

    private fun Int.getSeries(): String {
        var  l = "2"
        (1..this).forEach { index ->
            l = "$l, ${index.getSeriesNumber()}"
        }
        return "$l ..."
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        attachBaseContextUtil(newBase)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.task2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_impl -> startActivity(Intent(this, Task2ImpActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}