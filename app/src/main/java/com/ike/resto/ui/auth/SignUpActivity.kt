package com.ike.resto.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ike.resto.R
import com.ike.resto.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSignUp.setOnClickListener { signUpUser() }

        binding.tvDirectToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun signUpUser() {
        val email = binding.etEmail.text.toString().trim()
        val pass = binding.etPass.text.toString().trim()
        val confirmPass = binding.etConfirmPass.text.toString().trim()

        if (email.isBlank() || pass.isBlank() || confirmPass.isBlank()) {
            Toast.makeText(this,
                getString(R.string.email_and_password_can_t_be_blank), Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPass) {
            Toast.makeText(this,
                getString(R.string.password_and_confirm_password_do_not_match), Toast.LENGTH_SHORT)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, getString(R.string.successfully_singed_up), Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.singed_up_failed, it.exception?.message),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
