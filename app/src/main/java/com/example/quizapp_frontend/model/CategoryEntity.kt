package com.example.quizapp_frontend.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Category")
class CategoryEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="category_name")
    var category_name:String = "",

    @NonNull
    @ColumnInfo(name="correctAnswers")
    var correctAnswers:Int = 0,

    @NonNull
    @ColumnInfo(name="wrongAnswers")
    var wrongAnswers:Int = 0
)