package com.example.quizapp_frontend.repository

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.quizapp_frontend.database.AppDatabase
import com.example.quizapp_frontend.database.dao.CategoryDao
import com.example.quizapp_frontend.model.CategoryEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CategoryRepository (private var application: Application){
    private var categoryDao: CategoryDao
    private val database: AppDatabase
    private val DB_NAME = "Database"

    init{
        database = Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME).build()
        categoryDao = database.categoryDao()
    }

    fun addWrongAnswerToCategory(categoryName : String){
        val getDataJob = GlobalScope.async{categoryDao.checkIfCategoryExists(categoryName)}
        getDataJob.invokeOnCompletion { cause ->
            if(cause != null) {
                Log.e("ERROR", cause.toString())
            }else{
                val numberOfValues = getDataJob.getCompleted()
                if(numberOfValues > 0){
                    UpdateWrongAnswerTask(categoryDao).execute(categoryName).get()

                }else{
                    InsertTask(categoryDao).execute(CategoryEntity(categoryName, 0, 1)).get()
                }
            }
        }
    }

    fun addCorrectAnswerToCategory(categoryName : String){
        val getDataJob = GlobalScope.async{categoryDao.checkIfCategoryExists(categoryName)}
        getDataJob.invokeOnCompletion { cause ->
            if(cause != null) {
                Log.e("ERROR", cause.toString())
            }else{
                val numberOfValues = getDataJob.getCompleted()
                if(numberOfValues > 0){
                    UpdateCorrectAnswerTask(categoryDao).execute(categoryName).get()
                }else{
                    InsertTask(categoryDao).execute(CategoryEntity(categoryName, 1, 0)).get()
                }
            }
        }
    }

    fun getCategories() : LiveData<List<CategoryEntity>>{
        return categoryDao.getAllCategories()
    }

    private class UpdateCorrectAnswerTask(categoryDao: CategoryDao) :
        AsyncTask<String?, Void, Void>(){
        private val categoryDao = categoryDao

        override fun doInBackground(vararg categoryName: String?): Void? {
            categoryName.get(0)?.let { categoryDao.addCorrectAnswerToCategory(it) }
            return null
        }
    }

    private class UpdateWrongAnswerTask(categoryDao: CategoryDao) :
        AsyncTask<String?, Void, Void>(){
        private val categoryDao = categoryDao

        override fun doInBackground(vararg categoryName: String?): Void? {
            categoryDao.addWrongAnswerToCategory(categoryName[0])
            return null
        }
    }

    private class InsertTask(categoryDao: CategoryDao) :
        AsyncTask<CategoryEntity?, Void, Void>() {
        private val categoryDao = categoryDao
        override fun doInBackground(vararg categoryEntity :CategoryEntity?): Void? {
            categoryDao.insert(categoryEntity[0])
            return null
        }
    }

    fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
        observe(lifecycleOwner, object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        })
    }
}