package com.alfanro.pyntar.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alfanro.pyntar.core.database.entity.ProductivityLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductivityLogDao {
    @Query("SELECT * FROM productivity_logs WHERE userId = :userId ORDER BY date DESC")
    fun getLogsByUser(userId: String): Flow<List<ProductivityLogEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLog(log: ProductivityLogEntity)

    @Update
    suspend fun updateLog(log: ProductivityLogEntity)

    @Delete
    suspend fun deleteLog(log: ProductivityLogEntity)
}
