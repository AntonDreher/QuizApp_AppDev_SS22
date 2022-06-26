package com.example.quizapp_frontend.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Question")
data class QuestionEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String = "",

    @NonNull
    @ColumnInfo(name = "question")
    var question: String = "",

    @NonNull
    @ColumnInfo(name = "correctAnswer")
    var correctAnswer: String = "",

    @NonNull
    @ColumnInfo(name = "incorrectAnswer1")
    var incorrectAnswer1: String = "",

    @NonNull
    @ColumnInfo(name = "incorrectAnswer2")
    var incorrectAnswer2: String = "",

    @NonNull
    @ColumnInfo(name = "incorrectAnswer3")
    var incorrectAnswer3: String = ""
)