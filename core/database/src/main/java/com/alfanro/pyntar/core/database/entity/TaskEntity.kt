package com.alfanro.pyntar.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            // When category is deleted, task becomes uncategorized rather than being deleted
            onDelete = ForeignKey.SET_NULL 
        )
    ],
    indices = [Index(value = ["categoryId"])]
)
data class TaskEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val isCompleted: Boolean = false,
    val categoryId: String? = null,
    val dueDate: Long? = null,
    val priority: Int = 0, // 0: None, 1: Low, 2: Medium, 3: High
    val createdAt: Long = System.currentTimeMillis()
)
