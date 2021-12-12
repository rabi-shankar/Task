package com.rabi.task

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.text.HtmlCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment

/**
 * Hide the status bar.
 */
fun AppCompatActivity.hideStatusBar(view: View) {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, view).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

fun AppCompatActivity.showStatusBar(view: View) {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, view).show(WindowInsetsCompat.Type.systemBars())

}

fun AppCompatActivity.initToolbar(toolbar: Toolbar, isNeedNavigationIcon: Boolean = false) {
    this.setSupportActionBar(toolbar)
    if (isNeedNavigationIcon) {
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_black_24)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}

fun AppCompatActivity.attachBaseContextUtil(newBase: Context?) {
    val newOverride = Configuration(newBase?.resources?.configuration)
    newOverride.fontScale = 1.0f
    applyOverrideConfiguration(newOverride)
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun String.toSpanned(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

