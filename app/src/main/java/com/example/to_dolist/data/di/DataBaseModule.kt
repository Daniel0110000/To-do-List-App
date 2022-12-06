package com.example.to_dolist.data.di

import android.content.Context
import androidx.room.Room
import com.example.to_dolist.data.room.TaskDao
import com.example.to_dolist.data.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providerTaskDao(@ApplicationContext context: Context): TaskDao{
        return Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "task_database"
        ).build().getTaskDao()
    }
}