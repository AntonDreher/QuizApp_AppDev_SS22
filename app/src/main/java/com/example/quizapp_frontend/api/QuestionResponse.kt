package com.example.quizapp_frontend.api

import com.google.gson.annotations.SerializedName

class QuestionResponse {
    @SerializedName("id")
    var id: String = ""

    @SerializedName("question")
    var question: String = ""

    @SerializedName("correctAnswer")
    var correctAnswer: String = ""


    @SerializedName("incorrectAnswers")
    var incorrectAnswers : List<String> = emptyList()

    @SerializedName("category")
    var category : String = ""
}