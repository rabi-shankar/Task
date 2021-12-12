package com.rabi.task

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun View.gone(){
    if(visibility != View.GONE   || visibility == View.VISIBLE) visibility = View.GONE
}

fun View?.invisible() {
    if(this?.visibility == View.VISIBLE) visibility = View.INVISIBLE
}

fun View.visible(){
    if(visibility == View.INVISIBLE || visibility == View.GONE) visibility = View.VISIBLE
}

fun View.gone2x(){
    visibility = View.GONE
}

fun View.invisible2x(){
    visibility = View.INVISIBLE
}

fun View.visible2x(){
    visibility = View.VISIBLE
}


fun List<View>.goneList(){
    this.forEach(View::gone)
}


fun List<View>.invisibleList(){
    this.forEach(View::invisible)
}

fun List<View>.visibleList(){
    this.forEach(View::visible)
}


fun Context.toast(resource:String , duration: Int = Toast.LENGTH_SHORT ) =
    Toast.makeText(this,resource,duration).show()

fun View.snackBar(resource : String ) =
    Snackbar.make(this, resource,Snackbar.LENGTH_LONG).show()

fun View.snackBar(resource: String, actionText :String = "Report", action:() -> Unit) =
    Snackbar.make(this,resource, Snackbar.LENGTH_LONG).setAction(actionText) { action.invoke() }.show()


