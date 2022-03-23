package com.example.fitnessactivity.models

class Day() {
    var date: String? = null
    var steps: Int? = null

    constructor(date: String, steps: Int) : this() {
        this.date = date
        this.steps = steps
    }
}