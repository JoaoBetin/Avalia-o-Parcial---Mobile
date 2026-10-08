package com.example.adotapet.model

data class ChecklistItem(val id: String, val title: String, val note: String? = null)

data class Checklist(
    val id: String,
    val title: String,
    val description: String,
    val items: List<ChecklistItem>
)
