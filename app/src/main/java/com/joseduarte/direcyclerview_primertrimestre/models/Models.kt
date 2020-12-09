package com.joseduarte.direcyclerview_primertrimestre.models

import java.io.Serializable

interface Models : Serializable {
    fun getID(): String
    fun getData(): String
    fun convertData(): Array<String>
}