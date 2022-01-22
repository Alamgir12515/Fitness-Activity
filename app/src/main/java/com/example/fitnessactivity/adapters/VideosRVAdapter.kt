package com.example.fitnessactivity.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitnessactivity.addCardViewShadow
import com.example.fitnessactivity.databinding.VideoListItemBinding
import com.example.fitnessactivity.models.VideoModel
import com.example.fitnessactivity.printLog


class VideosRVAdapter(
    private val fragment: Fragment,
    private val list: List<VideoModel>
) : RecyclerView.Adapter<VideosRVAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideosRVAdapter.MyViewHolder {
        return MyViewHolder(
            VideoListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VideosRVAdapter.MyViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            "thumbnail".printLog(list[position].thumbnail)
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(list[position].link))
            startActivity(fragment.requireContext(), browserIntent, null)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(val binding: VideoListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(video: VideoModel) {
            fragment.activity?.addCardViewShadow(binding.root)
            Glide.with(binding.image).load(video.thumbnail).into(binding.image)
            binding.title.text = video.title
        }
    }
}