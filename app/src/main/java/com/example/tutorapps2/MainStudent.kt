package com.example.tutorapps2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainStudent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_student)

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // Setup Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> true
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
                R.id.nav_profile -> {
                    startActivity(Intent(this, MainProfileStud::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                else -> false
            }
        }

        // Populate Class List dynamically
        populateClassList()
    }

    private fun populateClassList() {
        val container = findViewById<LinearLayout>(R.id.announcementContainer)
        if (container == null) return

        // Clear existing views so we don't have duplicates or placeholders
        container.removeAllViews()

        val classes = listOf(
            ClassInfo("435 OOP", "Object Oriented Programming - Room 302", "8:00 AM"),
            ClassInfo("402 Programming I", "Introduction to Programming - Room 101", "10:00 AM"),
            ClassInfo("429 Computer Architecture", "Computer Systems - Room 205", "1:00 PM")
        )

        val inflater = LayoutInflater.from(this)

        for (cls in classes) {
            val view = inflater.inflate(R.layout.item_announcement, container, false)
            
            val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
            val tvDesc = view.findViewById<TextView>(R.id.tvDesc)
            val tvTime = view.findViewById<TextView>(R.id.tvTime)

            tvTitle.text = cls.name
            tvDesc.text = cls.description
            tvTime.text = cls.time

            container.addView(view)
        }
    }

    data class ClassInfo(val name: String, val description: String, val time: String)
}
