package com.example.motivation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.R
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.databinding.ActivityUserBinding
import com.example.motivation.helpers.LocalStorage
import com.example.motivation.utils.Constants

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var localStorage: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        localStorage = LocalStorage(this)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getUserName()
    }

    fun getUserName(){
        val name = localStorage.get(Constants.NAME_KEY)
        binding.txtName.text = name
    }
}