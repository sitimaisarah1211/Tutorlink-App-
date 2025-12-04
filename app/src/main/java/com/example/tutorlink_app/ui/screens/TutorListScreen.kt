package com.example.tutorlink_app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tutorlink_app.data.sample.SampleData
import com.example.tutorlink_app.ui.screens.TutorItem

@Composable
fun TutorListScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(SampleData.tutorList) { tutor ->
            TutorItem(tutor = tutor)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}