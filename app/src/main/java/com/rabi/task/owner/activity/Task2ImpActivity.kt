package com.rabi.task.owner.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rabi.task.databinding.ActivityTask2ImpBinding
import com.rabi.task.initToolbar

class Task2ImpActivity : AppCompatActivity() {
    lateinit var binding: ActivityTask2ImpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask2ImpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar(binding.toolbar,true)
    }
}