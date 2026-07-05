package com.example.examroadmaptrackerapp.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.examroadmaptrackerapp.data.DashboardItem
import com.example.examroadmaptrackerapp.data.RetrofitClient
import com.example.examroadmaptrackerapp.viewModel.TrackerViewModel

@Composable
fun DashboardScreen(viewModel: TrackerViewModel, navController: NavController) {
    var dashboardItems by remember { mutableStateOf<List<DashboardItem>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            dashboardItems = RetrofitClient.instance.getAnnouncements()
            errorMessage = null
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = "Veriler alınırken bir hata oluştu."
        } finally {
            isLoading = false
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Sınav Duyuruları",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(dashboardItems) { item ->
                    DashboardCard(item)
                }
            }
        }
    }
}

@Composable
fun DashboardCard(item: DashboardItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.examName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            item.examDate?.let {
                Text(text = "Sınav Tarihi: $it", style = MaterialTheme.typography.bodyMedium)
            }
            item.applyStartDate?.let {
                Text(text = "Başvuru Başlangıç: $it", style = MaterialTheme.typography.bodySmall)
            }
            item.applyEndDate?.let {
                Text(text = "Başvuru Bitiş: $it", style = MaterialTheme.typography.bodySmall)
            }
            item.LateApplyDate?.let {
                Text(text = "Geç Başvuru: $it", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.error)
            }
            item.resultPublishDate?.let {
                Text(text = "Sonuç Açıklama: $it", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
