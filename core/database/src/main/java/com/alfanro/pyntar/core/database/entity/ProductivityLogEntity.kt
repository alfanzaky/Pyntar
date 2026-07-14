package com.alfanro.pyntar.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "productivity_logs",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["userId"]),
        Index(value = ["userId", "date"], unique = true)
    ]
)
data class ProductivityLogEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val userId: String,
    val date: Long, // Use Long for timestamp/date
    val completedTasks: Int = 0,
    val lateTasks: Int = 0,
    val score: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
