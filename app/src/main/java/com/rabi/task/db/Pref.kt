package com.rabi.task.db

import android.content.Context
import com.rabi.task.BuildConfig

class Pref(context: Context) {

    enum class Key(private val code :String) {
        DATA("$id.data");
        fun code() = code
    }

    companion object{
        private const val id = BuildConfig.APPLICATION_ID
    }

    private var pref  = context.getSharedPreferences("$id.initPref_key", Context.MODE_PRIVATE)

    fun getString(key : Key, default: String = ""): String?{
        return pref.getString("$id.${key.code()}_KEY",default)
    }

    fun setString(key : Key, value: String = ""){
        val appearance = pref.edit()
        appearance.putString("$id.${key.code()}_KEY",value)
        appearance.apply()
    }

    fun removeString(key : Key){
        val appearance = pref.edit()
        appearance.remove("$id.${key.code()}_KEY")
        appearance.apply()
    }
}