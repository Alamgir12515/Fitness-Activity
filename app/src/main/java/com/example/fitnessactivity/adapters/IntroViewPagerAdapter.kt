package com.example.fitnessactivity.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.fitnessactivity.R
import com.example.fitnessactivity.models.IntroPageItem

class IntroViewPagerAdapter(val context: Context, private val mList: List<IntroPageItem>) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen: View = inflater.inflate(R.layout.intro_page, null)
        val img: ImageView = layoutScreen.findViewById(R.id.intro_img)
        val title: TextView = layoutScreen.findViewById(R.id.intro_title)
        val description: TextView = layoutScreen.findViewById(R.id.intro_description)
        Glide.with(context).load(mList[position].imageResource).into(img)
        title.text = mList[position].title
        description.text = mList[position].description
        container.addView(layoutScreen)
        return layoutScreen
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}