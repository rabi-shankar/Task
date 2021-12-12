package com.rabi.task.owner.fragment

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.core.text.toSpanned
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.rabi.task.R
import com.rabi.task.databinding.FragmentDialogInfoBinding

class InfoDialogFragment() : DialogFragment() {

    lateinit var binding: FragmentDialogInfoBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = FragmentDialogInfoBinding.inflate(LayoutInflater.from(context))
            builder.setView(binding.root)
            builder.setCancelable(false)

            with(binding) {
                closeDialog.setOnClickListener { dismiss() }
                binding.address.setOnClickListener { openInWebBrowser(getString(R.string.https_rabi_shankar_github_io_resume)) }
                binding.address.text = getText(R.string.https_rabi_shankar_github_io_resume).toSpanned()
            }
            val dialog: Dialog = builder.create()
            dialog.setCanceledOnTouchOutside(false)
            return@let dialog
        }?: throw IllegalStateException("Activity cannot be null")

    }

    private fun Fragment.openInWebBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}