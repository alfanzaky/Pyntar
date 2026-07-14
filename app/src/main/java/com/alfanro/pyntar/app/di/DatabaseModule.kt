package com.alfanro.pyntar.app.di

import android.content.Context
import androidx.room.Room
import com.alfanro.pyntar.core.database.PyntarDatabase
import com.alfanro.pyntar.core.database.dao.CategoryDao
import com.alfanro.pyntar.core.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providePyntarDatabase(
        @ApplicationContext context: Context
    ): PyntarDatabase {
        return Room.databaseBuilder(
            context,
            PyntarDatabase::class.java,
            "pyntar_db"
        )
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(database: PyntarDatabase): TaskDao = database.taskDao()

    @Provides
    @Singleton
    fun provideCategoryDao(database: PyntarDatabase): CategoryDao = database.categoryDao()

    @Provides
    @Singleton
    fun provideUserDao(database: PyntarDatabase): com.alfanro.pyntar.core.database.dao.UserDao = database.userDao()

    @Provides
    @Singleton
    fun provideProductivityLogDao(database: PyntarDatabase): com.alfanro.pyntar.core.database.dao.ProductivityLogDao = database.productivityLogDao()
}
