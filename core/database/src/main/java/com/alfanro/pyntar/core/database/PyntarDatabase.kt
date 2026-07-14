package com.alfanro.pyntar.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfanro.pyntar.core.database.dao.CategoryDao
import com.alfanro.pyntar.core.database.dao.TaskDao
import com.alfanro.pyntar.core.database.entity.CategoryEntity
import com.alfanro.pyntar.core.database.entity.ProductivityLogEntity
import com.alfanro.pyntar.core.database.entity.TaskEntity
import com.alfanro.pyntar.core.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        CategoryEntity::class,
        TaskEntity::class,
        ProductivityLogEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PyntarDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun categoryDao(): CategoryDao
    abstract fun userDao(): com.alfanro.pyntar.core.database.dao.UserDao
    abstract fun productivityLogDao(): com.alfanro.pyntar.core.database.dao.ProductivityLogDao
}
