package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.motivation.R
import com.example.motivation.databinding.ActivityUserBinding
import com.example.motivation.helpers.LocalStorage
import com.example.motivation.utils.Constants

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding
    private lateinit var localStorage: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        localStorage = LocalStorage(this)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListeners()
        checkUserName()
    }

    override fun onClick(view: View) {
        if ( R.id.btn_save == view.id ){
            handleSaveButton()
        }
    }

    private fun checkUserName(){
        val name = localStorage.get(Constants.NAME_KEY)
        if ( name.isNotEmpty()) {
            navigate()
        }
    }

    private fun setListeners(){
        binding.btnSave.setOnClickListener(this)
    }

    private fun handleSaveButton(){
        val name = binding.edtName.text.toString()
        localStorage.set(Constants.NAME_KEY,name)
        navigate()
    }

    private fun navigate(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // clear from activities stacks
    }
}