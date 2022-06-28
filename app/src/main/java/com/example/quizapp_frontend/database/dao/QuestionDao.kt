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

    @Query("SELECT * FROM question where id not in (:excludedIds) order by RANDOM() LIMIT 1")
    fun getRandomQuestion(excludedIds:List<String>) : LiveData<QuestionEntity>
}