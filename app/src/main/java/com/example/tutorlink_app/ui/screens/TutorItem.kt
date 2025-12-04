package com.example.tutorlink_app.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tutorlink_app.data.model.Tutor

@Composable
fun TutorItem(tutor: Tutor) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = tutor.image),
                contentDescription = tutor.name,
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(text = tutor.name, fontSize = 20.sp)
                Text(text = tutor.coursecode, fontSize = 16.sp)
                Text(text = "Rating: ${tutor.rating}", fontSize = 14.sp)
            }
        }
    }
}