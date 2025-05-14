package com.example.authentication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.authentication.utils.AppConstants
import com.example.authentication.R
import com.example.authentication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left + binding.main.paddingStart,
                systemBars.top,
                systemBars.right + binding.main.paddingEnd,
                systemBars.bottom
            )
            insets
        }

        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btnLogin) {
            handleLoginButton()
        }
    }

    private fun handleLoginButton() {
        val email = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()

        if (isValid(email, password)) {
            navigateToHome(email)
        } else {
            Toast.makeText(this, "Informe os dados!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValid(email: String, password: String): Boolean {
        return (email.isNotEmpty() && password.isNotEmpty())
    }

    private fun navigateToHome(email: String) {
        val bundle = Bundle()
        bundle.putString(AppConstants.EMAIL, email)

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}