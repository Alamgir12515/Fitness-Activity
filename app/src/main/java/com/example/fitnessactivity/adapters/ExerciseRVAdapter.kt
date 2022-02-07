package com.example.fitnessactivity.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessactivity.activities.ExerciseActivity
import com.example.fitnessactivity.addCardViewShadow
import com.example.fitnessactivity.databinding.ExercisesListItemBinding
import com.example.fitnessactivity.models.BodyPart
import com.example.fitnessactivity.models.Exercise

class ExerciseRVAdapter(
    private val activity: Activity,
    private val list: List<Exercise>
) : RecyclerView.Adapter<ExerciseRVAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseRVAdapter.MyViewHolder {
        return MyViewHolder(
            ExercisesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExerciseRVAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            activity.startActivity(Intent(activity, ExerciseActivity::class.java).apply {
                putExtra("THE_EXERCISE", list[position])
//                putExtra("THE_PART", bodyPart)
            })
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val binding: ExercisesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exercise: Exercise) {
            Glide.with(binding.image).load(exercise.image).into(binding.image)
            activity.addCardViewShadow(binding.root)
            binding.title.text = exercise.name
        }
    }
}