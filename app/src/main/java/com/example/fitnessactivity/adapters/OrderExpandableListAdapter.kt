package com.example.fitnessactivity.adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.fitnessactivity.R
import com.example.fitnessactivity.databinding.ExercisesListItemBinding
import com.example.fitnessactivity.databinding.ListChildItemBinding
import com.example.fitnessactivity.databinding.ListHeaderItemBinding
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.printLog

class OrderExpandableListAdapter(
    val context: Context,
    val dataHeader: ArrayList<String>,
    val childData: HashMap<String, ArrayList<Exercise>>
) :
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return dataHeader.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childData[dataHeader[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return dataHeader[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childData[dataHeader[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertedView = convertView
        val headerTitle = getGroup(groupPosition) as String
        val binding = ListHeaderItemBinding.inflate(LayoutInflater.from(context), null, false)
        if (convertedView == null) {
            convertedView = binding.root
        }
        binding.title.text = headerTitle
//        val total = getTotalCount(childData[dataHeader[0]])
        return convertedView
    }


    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertedView = convertView
        val child = getChild(groupPosition, childPosition) as Exercise
        val binding = ListChildItemBinding.inflate(LayoutInflater.from(context), null, false)
        if (convertedView == null) {
            convertedView = binding.root
        }
        binding.title.text = child.name
        binding.root.setOnClickListener {
            showProduct(child)
        }
        return convertedView
    }

    private fun showProduct(exercise: Exercise) {
        val dialogView = Dialog(context)
        val binding = ExercisesListItemBinding.inflate(LayoutInflater.from(context), null, false)
        dialogView.setContentView(binding.root)
        "image".printLog(exercise.image)
        Glide.with(binding.image).load(exercise.image).into(binding.image)
        binding.title.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.title.text = exercise.name
        dialogView.window?.decorView?.setBackgroundResource(android.R.color.transparent)
        dialogView.show()
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}