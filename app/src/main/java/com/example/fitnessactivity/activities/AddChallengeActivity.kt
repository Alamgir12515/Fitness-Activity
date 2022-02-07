package com.example.fitnessactivity.activities

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.fitnessactivity.adapters.OrderExpandableListAdapter
import com.example.fitnessactivity.databinding.ActivityAddChallengeBinding
import com.example.fitnessactivity.databinding.ExercisesListItemBinding
import com.example.fitnessactivity.makeGone
import com.example.fitnessactivity.makeVisible
import com.example.fitnessactivity.misc.GlobalSingleton
import com.example.fitnessactivity.models.Exercise
import com.example.fitnessactivity.printLog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class AddChallengeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddChallengeBinding

    private lateinit var headerList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddChallengeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        headerList = arrayListOf("Leg", "Chest", "Shoulder", "Abs", "Back", "Arms")
        fetchData()
        setupList()
    }

    private fun fetchData() {
        getDataByBodyPart(0)
    }


    private fun getDataByBodyPart(index: Int) {
        val ref = FirebaseDatabase.getInstance().getReference("Exercises").child(headerList[index])
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Exercise>()
                for (postSnapshot in snapshot.children) {
                    postSnapshot.getValue<Exercise>()?.let { list.add(it) }
                }
                "Calllled".printLog("df")
                GlobalSingleton.childData[headerList[index]] = list
                setupList()
                if (index < headerList.size - 1) {
                    getDataByBodyPart(index + 1)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("loadPost:onCancelled", error.toException().toString())
            }

        })
    }

    private fun setupList() {
        runOnUiThread {
            if (GlobalSingleton.childData.size != 6) {
                binding.progressBar2.makeVisible()
                binding.expandableListView.makeGone()
            } else {
                binding.progressBar2.makeGone()
                binding.expandableListView.makeVisible()
                val adapter = OrderExpandableListAdapter(
                    this,
                    ArrayList(GlobalSingleton.childData.map { it.key }),
                    GlobalSingleton.childData
                )
                binding.expandableListView.setAdapter(adapter)
            }
            "GlobalSingleton.childData".printLog(GlobalSingleton.childData.size)
            binding.expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
//                showProduct(GlobalSingleton.childData[headerList[0]]!![childPosition])
                true
            }
        }
    }


}