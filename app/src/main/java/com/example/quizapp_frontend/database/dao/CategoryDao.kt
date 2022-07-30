package com.example.quizapp_frontend.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizapp_frontend.model.CategoryEntity

@Dao
interface CategoryDao {
    @Insert
    fun insert(categories: CategoryEntity?)

    @Query("SELECT count(*) from category where category_name = :category_name")
    fun checkIfCategoryExists(category_name: String): Integer

    @Query("UPDATE category SET correctAnswers = correctAnswers+1 where category_name = :category_name")
    fun addCorrectAnswerToCategory(category_name: String)

    @Query("UPDATE category SET wrongAnswers = wrongAnswers+1 where category_name = :category_name")
    fun addWrongAnswerToCategory(category_name: String?)

    @Query("SELECT * from category")
    fun getAllCategories() : LiveData<List<CategoryEntity>>
}