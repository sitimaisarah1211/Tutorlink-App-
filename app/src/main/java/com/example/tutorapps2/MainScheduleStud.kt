package com.example.tutorapps2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import java.util.Calendar
import java.util.Locale

class MainScheduleStud : AppCompatActivity() {

    private lateinit var btnDate: MaterialButton
    private lateinit var btnTime: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_schedule_stud)
        
        // Handle Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        initializeDropdowns()
        setupListeners()
    }

    private fun initializeDropdowns() {
        // Tutor Name Dropdown
        val tutorNames = listOf("Dr. Smith", "Ms. Johnson", "Mr. Williams", "Prof. Brown")
        val tutorAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, tutorNames)
        findViewById<AutoCompleteTextView>(R.id.TutorName).setAdapter(tutorAdapter)

        // Course Code Dropdown
        val courseCodes = listOf("435 OOP Object Oriented Programming", "402 Programming I", "429 Computer Architecture")
        val courseAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, courseCodes)
        findViewById<AutoCompleteTextView>(R.id.CourseCode).setAdapter(courseAdapter)

        // Students Count Dropdown
        val studentCounts = listOf("1", "2", "3", "4", "5+")
        val countAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, studentCounts)
        findViewById<AutoCompleteTextView>(R.id.autoStudentsCount).setAdapter(countAdapter)
    }

    private fun setupListeners() {
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)

        // Date Picker
        btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = String.format(Locale.getDefault(), "%s %d, %d", getMonthName(selectedMonth), selectedDay, selectedYear)
                btnDate.text = selectedDate
            }, year, month, day).show()
        }

        // Time Picker
        btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val amPm = if (selectedHour >= 12) "PM" else "AM"
                val hour12 = if (selectedHour > 12) selectedHour - 12 else if (selectedHour == 0) 12 else selectedHour
                val selectedTime = String.format(Locale.getDefault(), "%d:%02d %s", hour12, selectedMinute, amPm)
                btnTime.text = selectedTime
            }, hour, minute, false).show()
        }

        // Submit Button Action
        btnSubmit.setOnClickListener {
            Toast.makeText(this, "Appointment Submitted Successfully!", Toast.LENGTH_SHORT).show()
            // Reset fields or navigate elsewhere if needed
        }

        // Bottom Navigation
        bottomNav.selectedItemId = R.id.nav_schedule
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainStudent::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                    true
                }
                R.id.nav_schedule -> true
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
    }

    private fun getMonthName(month: Int): String {
        return java.text.DateFormatSymbols().shortMonths[month]
    }
}