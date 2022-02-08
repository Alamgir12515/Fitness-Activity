package com.example.fitnessactivity.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessactivity.R
import com.example.fitnessactivity.activities.BodyPartActivity
import com.example.fitnessactivity.databinding.ChallengesListItemBinding
import com.example.fitnessactivity.models.CustomChallenge

class ChallengesRVAdapter(
    private val fragment: Fragment,
    private val list: List<CustomChallenge>
) : RecyclerView.Adapter<ChallengesRVAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChallengesRVAdapter.MyViewHolder {
        return MyViewHolder(
            ChallengesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChallengesRVAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(fragment.requireActivity(), BodyPartActivity::class.java).apply {
                putExtra("MyChallenge", list[position])
            }
            fragment.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val binding: ChallengesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(challenge: CustomChallenge) {
            binding.title.text = challenge.title
            val image = if (challenge.isComplete) R.drawable.done2 else R.drawable.in_progress
            Glide.with(fragment).load(image).into(binding.statusImage)
        }
    }
}