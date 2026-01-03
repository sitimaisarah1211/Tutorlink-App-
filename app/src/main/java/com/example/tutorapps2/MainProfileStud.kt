package com.example.tutorapps2

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton

class MainProfileStud : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_profile_stud)
        
        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Initialize Views
        val tvNameValue = findViewById<TextView>(R.id.tvNameValue)
        val tvMatricValue = findViewById<TextView>(R.id.tvMatricValue)
        val tvStatusValue = findViewById<TextView>(R.id.tvStatusValue)
        val btnLogout = findViewById<MaterialButton>(R.id.btnLogout)
        val btnEditProfile = findViewById<MaterialButton>(R.id.btnEditProfile)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set Profile Data (You can replace this with actual data retrieval logic)
        tvNameValue.text = "Student Name" 
        tvMatricValue.text = "A1234567"
        tvStatusValue.text = "Student"

        // Handle Edit Profile Button
        btnEditProfile.setOnClickListener {
            Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show()
        }

        // Handle Logout
        btnLogout.setOnClickListener {
            // Navigate back to MainActivity (Login Screen)
            val intent = Intent(this, MainActivity::class.java)
            // Clear the back stack so user cannot go back to profile
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Setup Bottom Navigation
        bottomNav.selectedItemId = R.id.nav_profile
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainStudent::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_schedule -> {
                    startActivity(Intent(this, MainScheduleStud::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_favorites -> {
                    startActivity(Intent(this, MainConfessRate::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }
}
