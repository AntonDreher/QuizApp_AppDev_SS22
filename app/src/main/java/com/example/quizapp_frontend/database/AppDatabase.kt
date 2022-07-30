package com.example.quizapp_frontend.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quizapp_frontend.database.dao.CategoryDao
import com.example.quizapp_frontend.database.dao.QuestionDao
import com.example.quizapp_frontend.model.CategoryEntity
import com.example.quizapp_frontend.model.QuestionEntity


@Database(entities = [QuestionEntity::class, CategoryEntity::class], version=1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun questionDao() : QuestionDao
    abstract fun categoryDao() : CategoryDao
}