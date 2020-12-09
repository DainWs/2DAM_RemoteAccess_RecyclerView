package com.joseduarte.direcyclerview_primertrimestre.models

import androidx.annotation.NonNull
import com.joseduarte.direcyclerview_primertrimestre.R
import com.joseduarte.direcyclerview_primertrimestre.ResourceLoader
import java.net.URLEncoder

class Person() : Models {

    private lateinit var id: String
    private lateinit var name: String
    private lateinit var age: String

    constructor(@NonNull idCuadrado: String, name: String, age: String) : this() {
        this.id = idCuadrado
        this.name = name

        if(age.isEmpty()) {this.age = "0"}
        else{this.age = age}
    }

    fun getPersonId(): String {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getAge(): String {
        return age
    }

    override fun getID(): String {
        return ResourceLoader.getString(R.string.pern_name) + " : " + name
    }

    override fun getData(): String {
        return ResourceLoader.getString(R.string.pern_age) + " : " + age
    }

    override fun convertData(): Array<String> {
        var array: Array<String> = Array<String>(3) { i -> "" }
        array[0] = URLEncoder.encode(id, "UTF8")
        array[1] = URLEncoder.encode(name, "UTF8")
        array[2] = URLEncoder.encode(age, "UTF8")

        return array
    }
}