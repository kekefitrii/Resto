package com.ike.resto.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ike.resto.R
import com.ike.resto.databinding.ActivityLoginBinding
import com.ike.resto.ui.foodList.FoodsListActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvDirectToSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun login() {
        val email = binding.etEmail.text.toString()
        val pass = binding.etPass.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val intent = Intent(this, FoodsListActivity::class.java)
                startActivity(intent)
                finish()
                Toast.makeText(this, getString(R.string.successfully_loggedin), Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, getString(R.string.log_in_failed, it.exception?.message), Toast.LENGTH_SHORT).show()
        }
    }
}
