package com.example.tutorapps2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tilEmail: TextInputLayout
    private lateinit var tilPass: TextInputLayout
    private lateinit var emailEt: TextInputEditText
    private lateinit var passEt: TextInputEditText
    private lateinit var rgUserType: RadioGroup
    private lateinit var btnLogin: Button
    private lateinit var btnGoogle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val mainView = findViewById<View>(R.id.main)
        
        // Capture original padding from XML to preserve it
        val initialPaddingLeft = mainView.paddingLeft
        val initialPaddingTop = mainView.paddingTop
        val initialPaddingRight = mainView.paddingRight
        val initialPaddingBottom = mainView.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                initialPaddingLeft + systemBars.left,
                initialPaddingTop + systemBars.top,
                initialPaddingRight + systemBars.right,
                initialPaddingBottom + systemBars.bottom
            )
            insets
        }

        initializeViews()
        setupListeners()
    }

    private fun initializeViews() {
        tilEmail = findViewById(R.id.tilEmail)
        tilPass = findViewById(R.id.tilPass)
        emailEt = findViewById(R.id.Txtemail)
        passEt = findViewById(R.id.TxtPass)
        rgUserType = findViewById(R.id.RadioGroup)
        btnLogin = findViewById(R.id.btnlogin)
        btnGoogle = findViewById(R.id.btnGoogle)
    }

    private fun setupListeners() {
        btnLogin.setOnClickListener {
            if (validateForm()) {
                val role = getSelectedRole()
                Toast.makeText(this, "Logging in as $role...", Toast.LENGTH_SHORT).show()
                navigateToRoleActivity(role)
            }
        }

        btnGoogle.setOnClickListener {
            if (validateRoleSelection()) {
                val role = getSelectedRole()
                Toast.makeText(this, "Google Sign-In as $role...", Toast.LENGTH_SHORT).show()
                simulateGoogleLogin(role)
            }
        }
    }

    private fun navigateToRoleActivity(role: String) {
        if (role == "Student") {
            val intent = Intent(this, MainStudent::class.java)
            startActivity(intent)
            finish()
        } else {
            // TODO: Navigate to Tutor Activity
            Toast.makeText(this, "Tutor dashboard not implemented yet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateRoleSelection(): Boolean {
        if (rgUserType.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select Tutor or Student first", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateForm(): Boolean {
        var isValid = true

        // Reset errors
        tilEmail.error = null
        tilPass.error = null

        // Validate Role
        if (!validateRoleSelection()) return false

        // Validate Email
        val email = emailEt.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.error = "Email is required"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.error = "Invalid email address"
            isValid = false
        }

        // Validate Password
        val password = passEt.text.toString().trim()
        if (password.isEmpty()) {
            tilPass.error = "Password is required"
            isValid = false
        } else if (password.length < 6) {
            tilPass.error = "Password must be at least 6 characters"
            isValid = false
        }

        return isValid
    }

    private fun getSelectedRole(): String {
        return if (rgUserType.checkedRadioButtonId == R.id.RadioTutor) "Tutor" else "Student"
    }

    private fun simulateGoogleLogin(role: String) {
        btnGoogle.isEnabled = false
        Handler(Looper.getMainLooper()).postDelayed({
            btnGoogle.isEnabled = true
            Toast.makeText(this, "Google Sign-In Successful!", Toast.LENGTH_SHORT).show()
            navigateToRoleActivity(role)
        }, 2000)
    }
}
