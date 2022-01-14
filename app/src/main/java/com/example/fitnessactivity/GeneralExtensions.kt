@file:Suppress("DEPRECATION")

package com.example.fitnessactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
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

fun String.isCurrentDate(): Boolean {
    val c = Calendar.getInstance().time
    val simpleDateFormat = SimpleDateFormat("dd", Locale.getDefault())
    return simpleDateFormat.format(c) == this
}

fun Activity.setWhiteStatusBarColor() {
    val window = window
    val decorView = window.decorView
    val wic = WindowInsetsControllerCompat(window, decorView)
    wic.isAppearanceLightStatusBars = true // true or false as desired.
    // And then you can set any background color to the status bar.
    window.statusBarColor = ContextCompat.getColor(this, R.color.backgroundColor)
}
//fun ImageView.select(context: Context) {
//    this.setColorFilter(ContextCompat.getColor(context, R.color.primaryVariant))
//}
//
//fun ImageView.unSelect(context: Context) {
//    this.setColorFilter(ContextCompat.getColor(context, R.color.darkPrimary))
//}

//
//fun DashboardActivity.getSavedVideoId(): String? {
//    val preferences: SharedPreferences =
//        applicationContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
//    return if (isMcat()) preferences.getString(
//        "MCAT_VIDEO_ID",
//        ""
//    ) else preferences.getString("VIDEO_ID", "")
//}

//
//fun Activity.saveUserState(value: Boolean, phoneNumber: String) {
//    val preferences: SharedPreferences =
//        applicationContext.getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
//    val editor: SharedPreferences.Editor = preferences.edit()
//    editor.putBoolean("USER_STATE", value)
//    editor.putString("PHONE_NUMBER", phoneNumber)
//    editor.apply()
//    editor.commit()
//}

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
