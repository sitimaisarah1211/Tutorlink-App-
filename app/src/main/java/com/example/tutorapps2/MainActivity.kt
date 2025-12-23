package com.example.tutorapps2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var btnGoogle: Button
    private lateinit var email: EditText
    private lateinit var txtPass: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Window insets for edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize views
        email = findViewById(R.id.Txtemail)
        txtPass = findViewById(R.id.TxtPass)
        btnLogin = findViewById(R.id.btnlogin)
        btnGoogle = findViewById(R.id.btnGoogle)

        val rgUserType = findViewById<RadioGroup>(R.id.RadioGroup)

        // Login button click listener
        btnLogin.setOnClickListener {
            val emailText = email.text.toString().trim()
            val passwordText = txtPass.text.toString().trim()

            // Track all errors
            val errors = mutableListOf<String>()

            // Check ALL fields
            if (emailText.isEmpty()) {
                errors.add("Email is required")
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
                errors.add("Enter a valid email address")
            }

            if (passwordText.isEmpty()) {
                errors.add("Password is required")
            } else if (passwordText.length < 6) {
                errors.add("Password must be 6+ characters")
            }

            if (rgUserType.checkedRadioButtonId == -1) {
                errors.add("Please select Tutor or Student")
            }

            // Show ALL errors at once or proceed
            if (errors.isNotEmpty()) {
                Toast.makeText(this, errors.joinToString("\n"), Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            // Get user type and login
            val selectedId = rgUserType.checkedRadioButtonId
            val userRole = if (selectedId == R.id.RadioTutor) "tutor" else "student"
            Toast.makeText(this, "Login successful as $userRole!", Toast.LENGTH_SHORT).show()

            // TODO: Your login logic

            // Clear form
            email.text.clear()
            txtPass.text.clear()
            rgUserType.clearCheck()
        }

        // Google button click listener - Simple implementation
        btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google login clicked", Toast.LENGTH_SHORT).show()

            // Simple simulation of Google login (for demo purposes)
            // In real app, you would integrate Firebase Auth or Google Sign-In SDK here

            // Simulate successful login after 2 seconds
            btnGoogle.isEnabled = false
            btnGoogle.text = "Signing in..."

            android.os.Handler().postDelayed({
                Toast.makeText(this, "Google login successful!", Toast.LENGTH_SHORT).show()
                email.text.clear()
                txtPass.text.clear()
                btnGoogle.isEnabled = true
                btnGoogle.text = "Sign in with Google"
            }, 2000)
        }
    }
}