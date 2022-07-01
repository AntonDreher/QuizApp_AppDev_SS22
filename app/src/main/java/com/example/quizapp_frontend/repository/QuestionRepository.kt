package com.example.quizapp_frontend.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.quizapp_frontend.api.QuestionResponse
import com.example.quizapp_frontend.api.QuizApi
import com.example.quizapp_frontend.constants.ApiConstants
import com.example.quizapp_frontend.database.AppDatabase
import com.example.quizapp_frontend.database.dao.QuestionDao
import com.example.quizapp_frontend.model.QuestionEntity
import com.example.quizapp_frontend.viewmodel.NavigationViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO wait for async task
class QuestionRepository(application: Application) {
    private var questionDao: QuestionDao
    private val database: AppDatabase
    private val DB_NAME = "Database"

    init{
        database = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME).build()
        questionDao = database.questionDao()
    }
    fun insert(questionsResponse : List<QuestionResponse>): AsyncTask<List<QuestionEntity?>?, Void?, Void?>? {
        val questionsToInsert = convertQuestionResponseToDbObject(questionsResponse)
        return InsertAsyncTask(questionDao).execute(questionsToInsert)
    }

    private fun deleteAll(): AsyncTask<Void?, Void?, Void?>?{
        return DeleteAsyncTask(questionDao).execute()
    }

    private fun convertQuestionResponseToDbObject(questionsToConvert : List<QuestionResponse>) : List<QuestionEntity>{
        val convertedQuestions = mutableListOf<QuestionEntity>()
        for(currentQuestion in questionsToConvert){
            convertedQuestions.add(
                QuestionEntity(
                currentQuestion.id,
                currentQuestion.question,
                currentQuestion.correctAnswer,
                currentQuestion.incorrectAnswers[0],
                currentQuestion.incorrectAnswers[1],
                currentQuestion.incorrectAnswers[2])
            )
        }
        return convertedQuestions
    }

    fun getRandomQuestion(excludedIds:List<String>) : LiveData<QuestionEntity>{
        return questionDao.getRandomQuestion(excludedIds)
    }

    fun updateQuestions() {
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: QuizApi = retrofit.create(QuizApi::class.java)
        val call: Call<List<QuestionResponse>> = api.getQuestions()
        call.enqueue(object : Callback<List<QuestionResponse>> {
            override fun onResponse(call: Call<List<QuestionResponse>>, response: Response<List<QuestionResponse>>) {
                if (response.isSuccessful) {
                    deleteAll()?.get()
                    response.body()?.let {
                        insert(it)?.get()
                    }
                }
            }
            override fun onFailure(call: Call<List<QuestionResponse>>, t: Throwable) {
                Log.d("main", "onFailure: " + t.message)
            }
        })
    }

    private class InsertAsyncTask(questionDao: QuestionDao) :
        AsyncTask<List<QuestionEntity?>?, Void?, Void?>() {
        private val questionDao = questionDao

        override fun doInBackground(vararg lists: List<QuestionEntity?>?): Void? {
            questionDao.insert(lists[0])
            return null
        }
    }

    private class DeleteAsyncTask(questionDao: QuestionDao) :
        AsyncTask<Void?, Void?, Void?>() {
        private val questionDao = questionDao

        override fun doInBackground(vararg p0: Void?): Void? {
            questionDao.deleteAll()
            return null
        }
    }
}