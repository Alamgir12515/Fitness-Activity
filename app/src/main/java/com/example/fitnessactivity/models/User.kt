package com.example.fitnessactivity.models

class User() {
    var name: String? = null
    var age: Int? = null
    var height: Int? = null
    var weight: Int? = null
    var phone: String? = null
    var email: String? = null

    constructor(
        name: String,
        age: Int,
        height: Int,
        weight: Int,
        phone: String,
        email: String
    ) : this() {
        this.name = name
        this.age = age
        this.height = height
        this.weight = weight
        this.phone = phone
        this.email = email
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is User) return false
        return (name == other.name &&
                age == other.age &&
                height == other.height &&
                weight == other.weight &&
                phone == other.phone &&
                email == other.email)
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (age ?: 0)
        result = 31 * result + (height ?: 0)
        result = 31 * result + (weight ?: 0)
        result = 31 * result + (phone?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        return result
    }
}