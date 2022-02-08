package com.example.fitnessactivity.adapters

import android.app.Dialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.fitnessactivity.R
import com.example.fitnessactivity.activities.AddChallengeActivity
import com.example.fitnessactivity.databinding.ExercisesListItemBinding
import com.example.fitnessactivity.databinding.ListChildItemBinding
import com.example.fitnessactivity.databinding.ListHeaderItemBinding
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.printLog

class OrderExpandableListAdapter(
    val activity: AddChallengeActivity,
    private val dataHeader: ArrayList<String>,
    private val childData: HashMap<String, ArrayList<Exercise>>
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
        val headerTitle = getGroup(groupPosition) as String
        val binding = ListHeaderItemBinding.inflate(LayoutInflater.from(activity), null, false)
        val selectedCount = getSelectedCount(childData[headerTitle])
        binding.selectedCount.text = selectedCount.toString()
        binding.title.text = headerTitle
        return binding.root
    }

    private fun getSelectedCount(arrayList: ArrayList<Exercise>?): Int {
        var count = 0
        arrayList?.forEach {
            if (activity.selectedExercises!!.contains(it)) count += 1
        }
        return count
    }


    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val child = getChild(groupPosition, childPosition) as Exercise
        val binding = ListChildItemBinding.inflate(LayoutInflater.from(activity), null, false)
        binding.title.text = child.name
        binding.checkBox.isChecked = activity.selectedExercises!!.contains(child)
        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                activity.selectedExercises?.add(child)
            } else {
                activity.selectedExercises?.remove(child)
            }
            activity.updateCount()
            notifyDataSetChanged()
        }
        binding.root.setOnClickListener {
            showProduct(child)
        }
        return binding.root
    }

    private fun showProduct(exercise: Exercise) {
        val dialogView = Dialog(activity)
        val binding = ExercisesListItemBinding.inflate(LayoutInflater.from(activity), null, false)
        dialogView.setContentView(binding.root)
        "image".printLog(exercise.image)
        Glide.with(binding.image).load(exercise.image).into(binding.image)
        binding.title.setTextColor(ContextCompat.getColor(activity, R.color.white))
        binding.title.text = exercise.name
        dialogView.window?.decorView?.setBackgroundResource(android.R.color.transparent)
        dialogView.show()
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}