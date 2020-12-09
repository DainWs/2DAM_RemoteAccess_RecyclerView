package com.joseduarte.direcyclerview_primertrimestre

import android.content.Context
import androidx.annotation.NonNull

class ResourceLoader {

    companion object {
        private lateinit var context: Context

        fun setContext(@NonNull newContext: Context) {
            context = newContext
        }

        fun getContext(): Context {
            return context
        }

        fun getString(id: Int) : String {
            return context.getString(id)
        }

    }

}