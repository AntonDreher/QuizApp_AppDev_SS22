package com.example.quizapp_frontend.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizapp_frontend.model.QuestionEntity

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(questions: List<QuestionEntity?>?)

    @Query("DELETE FROM question")
    fun deleteAll()

    @Query("SELECT * FROM question order by RANDOM() LIMIT 1")
    fun getRandomQuestion() : LiveData<QuestionEntity>
}