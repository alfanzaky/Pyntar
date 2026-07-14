package com.alfanro.pyntar.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

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
    val id: String,               // deterministic: "$userId-$date"
    val userId: String,
    val date: Long,               // epoch milliseconds (start of day)
    val completedTasks: Int = 0,
    val lateTasks: Int = 0,
    val score: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        /** Build a stable PK so that @Upsert can correctly match on primary key. */
        fun createId(userId: String, date: Long): String = "$userId-$date"
    }
}
