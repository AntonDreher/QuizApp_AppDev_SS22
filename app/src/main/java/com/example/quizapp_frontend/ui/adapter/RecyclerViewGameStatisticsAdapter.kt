package com.example.quizapp_frontend.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp_frontend.R
import com.example.quizapp_frontend.model.CategoryEntity

class RecyclerViewGameStatisticsAdapter(private val categories: List<CategoryEntity>) : RecyclerView.Adapter<RecyclerViewGameStatisticsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView = itemView.findViewById<TextView>(R.id.categoryNameStatisticTextView)
        val correctAnswersTextView = itemView.findViewById<TextView>(R.id.numberOfCorrectAnswersStatisticTextView)
        val wrongAnswersTextView = itemView.findViewById<TextView>(R.id.numberOfWrongAnswersStatisticTextView)
        val ratioProgressBar = itemView.findViewById<ProgressBar>(R.id.ratioCorrectWrongAnswersProgress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.game_statistics_layout, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category: CategoryEntity = categories.get(position)
        holder.categoryNameTextView.setText(category.category_name)
        holder.correctAnswersTextView.setText(category.correctAnswers.toString())
        holder.wrongAnswersTextView.setText(category.wrongAnswers.toString())
        if(category.correctAnswers != 0) {
            val ratio = category.correctAnswers.toDouble()/(category.correctAnswers.toDouble()+category.wrongAnswers.toDouble())
            holder.ratioProgressBar.progress = (ratio*100).toInt()
        }else{
            holder.ratioProgressBar.progress = 0
        }
        if(holder.ratioProgressBar.progress > 75){
            holder.ratioProgressBar.progressTintList = ColorStateList.valueOf(Color.GREEN)
        }else if(holder.ratioProgressBar.progress > 50){
            holder.ratioProgressBar.progressTintList = ColorStateList.valueOf(Color.YELLOW)
        }else if(holder.ratioProgressBar.progress > 25){
            holder.ratioProgressBar.progressTintList = ColorStateList.valueOf(Color.rgb(255, 165,0))
        }else{
            holder.ratioProgressBar.progressTintList = ColorStateList.valueOf(Color.RED)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}