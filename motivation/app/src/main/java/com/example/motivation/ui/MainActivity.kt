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
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val localStorage: LocalStorage by lazy { LocalStorage(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + 20,
                systemBars.top,
                systemBars.right + 20,
                systemBars.bottom
            )
            insets
        }

        getUserName()
    }

    fun getUserName() {
        val name = localStorage.get(Constants.NAME_KEY)
        binding.txtName.text = name
    }
}