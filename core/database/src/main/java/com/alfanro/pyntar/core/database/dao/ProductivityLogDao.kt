package com.alfanro.pyntar.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.alfanro.pyntar.core.database.entity.ProductivityLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductivityLogDao {
    @Query("SELECT * FROM productivity_logs WHERE userId = :userId ORDER BY date DESC")
    fun getLogsByUser(userId: String): Flow<List<ProductivityLogEntity>>

    @Query("SELECT * FROM productivity_logs WHERE userId = :userId AND date = :date LIMIT 1")
    suspend fun getLogByUserAndDate(userId: String, date: Long): ProductivityLogEntity?

    @Upsert
    suspend fun upsertLog(log: ProductivityLogEntity)

    @Update
    suspend fun updateLog(log: ProductivityLogEntity)

    @Delete
    suspend fun deleteLog(log: ProductivityLogEntity)
}
