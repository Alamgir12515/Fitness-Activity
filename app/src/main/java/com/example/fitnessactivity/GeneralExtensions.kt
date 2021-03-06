@file:Suppress("DEPRECATION")

package com.example.fitnessactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.example.fitnessactivity.models.BmiCategory
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

fun Float.getBmiCategory(): BmiCategory {
    return when {
        this < 18.5 -> BmiCategory.Underweight
        this in 18.5..24.9 -> BmiCategory.Normal
        this in 25.0..29.9 -> BmiCategory.Overweight
        else -> BmiCategory.Obese
    }
}

fun Fragment.getColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(requireContext(), color)
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.toggleVisibility(show: Boolean) {
    if (show) this.makeVisible() else this.makeGone()
}

fun Activity.showToastShort(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToastShort(msg: String) {
    Toast.makeText(this.requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun String.showToastLong(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun String.printLog(text: Any?) {
    if (BuildConfig.DEBUG) {
        Log.e(this, if (text !is String) text.toString() else text)
    }
}

fun Activity.addCardViewShadow(card: CardView?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        card?.cardElevation = resources.getDimension(R.dimen._6sdp)
        card?.outlineAmbientShadowColor =
            ContextCompat.getColor(this, R.color.cardShadowColor)
        card?.outlineSpotShadowColor =
            ContextCompat.getColor(this, R.color.cardShadowColor)
    }
}

fun Activity.contactMessenger() {
    val messengerUrl: String = if (isMessengerAppInstalled()) {
        "fb-messenger://user/alam.chandio.148/"
    } else {
        "https://www.messenger.com/t/alam.chandio.148/"
    }
    val messengerIntent = Intent(Intent.ACTION_VIEW)
    messengerIntent.data = Uri.parse(messengerUrl)
    startActivity(messengerIntent)
}

fun NestedScrollView.smoothScrollTo(view: View) {
    var distance = view.top
    var viewParent = view.parent
    //traverses 10 times
    for (i in 0..9) {
        if ((viewParent as View) === this) break
        distance += (viewParent as View).top
        viewParent = viewParent.getParent()
    }
    smoothScrollTo(0, distance)
}

fun Activity.addCardViewShadow(
    card: CardView?,
    elevation: Float = resources.getDimension(R.dimen._6sdp)
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        card?.cardElevation = elevation
        card?.outlineAmbientShadowColor =
            ContextCompat.getColor(this, R.color.cardShadowColor)
        card?.outlineSpotShadowColor =
            ContextCompat.getColor(this, R.color.cardShadowColor)
    }
}

fun Activity.isMessengerAppInstalled(): Boolean {
    return try {
        applicationContext.packageManager.getApplicationInfo(
            "com.facebook.orca",
            0
        )
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Activity.getWhatsappIntent(): Intent {
    val maqsadContactNumber = "923468954833"
    try {
        packageManager.getPackageInfo("com.whatsapp", 0)
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://api.whatsapp.com/send?phone=$maqsadContactNumber")
        )
    } catch (e: java.lang.Exception) {
        return when (Build.VERSION.SDK_INT > 21) {
            true -> {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=$maqsadContactNumber")
                )
            }
            false -> {
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                )
            }
        }
    }
}

fun Activity.makeStatusBarTransparent() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        statusBarColor = Color.TRANSPARENT
    }
}

fun Activity.setDarkStatusBarColor(@ColorRes color: Int) {
    val window = window
    val decorView = window.decorView
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = false // true or false as desired.
    // And then you can set any background color to the status bar.
    window.statusBarColor = ContextCompat.getColor(this, color)
}

fun Context.colorList(@ColorRes id: Int): ColorStateList {
    return ColorStateList.valueOf(ContextCompat.getColor(this, id))
}

fun Fragment.getCurrentData(): String {
    val c = Calendar.getInstance().time
    val simpleDateFormat = SimpleDateFormat("E-dd", Locale.getDefault())
    return simpleDateFormat.format(c)
}

fun String.isCurrentDay(): Boolean {
    val c = Calendar.getInstance().time
    val simpleDateFormat = SimpleDateFormat("dd", Locale.getDefault())
    return simpleDateFormat.format(c) == this
}

fun String.isCurrentDate(): Boolean {
    val c = Calendar.getInstance().time
    val simpleDateFormat = SimpleDateFormat("E-dd", Locale.getDefault())
    return simpleDateFormat.format(c) == this
}

fun Activity.setWhiteStatusBarColor(@ColorRes id: Int = R.color.backgroundColor) {
    val window = window
    val decorView = window.decorView
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = true // true or false as desired.
    // And then you can set any background color to the status bar.
    window.statusBarColor = ContextCompat.getColor(this, id)
}

fun Long.toDateString(): String {
    return SimpleDateFormat(
        "dd MMM yyyy",
        Locale.US
    ).format(Date(this))
}

fun Activity.enableDisableViewGroup(viewGroup: ViewGroup, enabled: Boolean) {
    val childCount = viewGroup.childCount
    for (i in 0 until childCount) {
        val view = viewGroup.getChildAt(i)
        view.isEnabled = enabled
        if (view is ViewGroup) {
            enableDisableViewGroup(view, enabled)
        }
    }
}

fun TabLayout.enableDisable(enabled: Boolean) {
    val tabStrip = this.getChildAt(0) as LinearLayout
    tabStrip.isEnabled = enabled
    for (i in 0 until tabStrip.childCount) {
        tabStrip.getChildAt(i).isClickable = enabled
    }
}

fun Activity.closeApp() {
    Toast.makeText(this, "Closing App", Toast.LENGTH_LONG).show()
    Handler(Looper.getMainLooper()).postDelayed({
        finishAffinity()
        exitProcess(0)
    }, 500)
}


fun Activity.changeStatusBarColor(isLight: Boolean = true) {
    if (isLight) {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Log.e("UI_MODE_NIGHT_NO", "Light")
                window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                Log.e("UI_MODE_NIGHT_YES", "Dark")
                window.decorView.systemUiVisibility =
                    window.decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
        window.statusBarColor = ContextCompat.getColor(
            this,
            R.color.backgroundColor
        ) // set status background white
    } else {
        ///TExt dark
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val decorView: View = window.decorView //set status background black
        decorView.systemUiVisibility =
            decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() //set status text  light
    }
}

fun Activity.pxToDp(px: Float): Float {
    return px / this.resources.displayMetrics.density
}

fun Activity.dpToPx(dp: Int): Float {
    return dp * this.resources.displayMetrics.density
}

fun Activity.sdpToPx(sdp: Float): Float {
    return sdp * this.resources.displayMetrics.density
}

fun String.countOccurrences(ch: Char): Int {
    return this.filter { it == ch }.count()
}

fun ScrollView.scrollToBottom() {
    val lastChild = getChildAt(childCount - 1)
    val bottom = lastChild.bottom + paddingBottom
    val delta = bottom - (scrollY + height)
    smoothScrollTo(0, delta)
//    smoothScrollBy(0, delta)
}

/*
fun  Activity.changeStatusBarColor(isLight: Boolean) {
    if (!isLight) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;//  set status text dark
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorAccentPrimary);// set status background white
    } else {
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary);
        val decorView = window.decorView //set status background black
        decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv() //set status text  light
    }
}*/
