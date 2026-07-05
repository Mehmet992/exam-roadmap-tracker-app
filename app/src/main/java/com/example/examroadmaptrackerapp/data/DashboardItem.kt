package com.example.examroadmaptrackerapp.data

data class DashboardItem(
    var examName: String = "Unknown Exam",
    var examDate: String? = null,
    var applyStartDate: String? = null,
    var applyEndDate: String? = null,
    var LateApplyDate: String? = null,
    var resultPublishDate: String? = null
)
