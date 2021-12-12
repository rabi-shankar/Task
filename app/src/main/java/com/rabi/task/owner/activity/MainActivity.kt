package com.rabi.task.owner.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rabi.task.*
import com.rabi.task.databinding.ActivityMainBinding
import com.rabi.task.owner.fragment.InfoDialogFragment


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar(binding.toolbar, false)
        with(binding.content) {
            task1.text =  getString(R.string.task_1_description).toSpanned()
            task2.text =  getString(R.string.task_2_description).toSpanned()
            task1.setOnClickListener(this@MainActivity)
            task2.setOnClickListener(this@MainActivity)
        }
    }

    override fun onClick(p0: View?) {
        with(binding.content) {
            when (p0?.id) {
                task1.id -> startActivity(Intent(this@MainActivity, Task1Activity::class.java))
                task2.id -> startActivity(Intent(this@MainActivity, Task2Activity::class.java))
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        attachBaseContextUtil(newBase)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_document -> {
                val url =
                    "https://drive.google.com/file/d/10P_ZG7Mv0NHSGVAS45CiDJMJahW5wFqt/view"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)

            }

            R.id.action_info ->{
                InfoDialogFragment().show(supportFragmentManager, "info dialog")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}