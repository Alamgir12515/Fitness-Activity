package com.example.fitnessactivity.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessactivity.addCardViewShadow
import com.example.fitnessactivity.databinding.AboutUsItemLayoutBinding
import com.example.fitnessactivity.models.IntroPageItem

class AboutUsRVAdapter(
    private val activity: Activity,
    private val list: List<IntroPageItem>
) : RecyclerView.Adapter<AboutUsRVAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AboutUsRVAdapter.MyViewHolder {
        return MyViewHolder(
            AboutUsItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AboutUsRVAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val binding: AboutUsItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(page: IntroPageItem) {
            binding.title.text = page.title
            binding.description.text = page.description
            Glide.with(activity).load(page.imageResource).into(binding.image)
            activity.addCardViewShadow(binding.aboutUsCardView)
        }
    }
}