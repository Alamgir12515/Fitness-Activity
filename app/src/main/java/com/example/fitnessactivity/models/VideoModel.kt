package com.example.fitnessactivity.models

class VideoModel(val title: String, val link: String) {
    public var thumbnail = ""
    init {
        val temp = "https://img.youtube.com/vi/{vID}/0.jpg"
        thumbnail = temp.replace("{vID}", link.split("/").last())
    }
}