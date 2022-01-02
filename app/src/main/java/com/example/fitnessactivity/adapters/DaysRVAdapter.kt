package com.example.fitnessactivity.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnessactivity.R
import com.example.fitnessactivity.colorList
import com.example.fitnessactivity.databinding.StepCounterCalenderLayoutBinding
import com.example.fitnessactivity.isCurrentDate
import java.text.SimpleDateFormat
import java.util.*

class DaysRVAdapter(
    private val fragment: Fragment,
    private val list: ArrayList<String>
) : RecyclerView.Adapter<DaysRVAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysRVAdapter.MyViewHolder {
        return MyViewHolder(
            StepCounterCalenderLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DaysRVAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
//        holder.binding.cartItemRemove.setOnClickListener {

//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val binding: StepCounterCalenderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(day: String) {
            val splitDay = day.split("-")
            if (splitDay[1].isCurrentDate()) setSelectedDay()
            binding.dayName.text = splitDay[0]
            binding.dayDate.text = splitDay[1]
        }

        private fun setSelectedDay() {
            binding.dayCard.setCardBackgroundColor(fragment.requireContext().colorList(R.color.bmiBackgroundColor))
            binding.dayCard.cardElevation = fragment.requireContext().resources.getDimension(R.dimen._5sdp)
            binding.dayDate.setTextColor(ContextCompat.getColor(fragment.requireContext(), R.color.white))
            binding.dayName.setTextColor(ContextCompat.getColor(fragment.requireContext(), R.color.white))
        }


    }
}