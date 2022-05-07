package com.example.projekt3.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.projekt3.R

class PuzzleCompletedDialogFragment(private val completionTime: Long): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(
                resources.getString(R.string.puzzle_completed_message, completionTime)
            ).setPositiveButton(R.string.puzzle_completed_button_message) { dialog, id -> it.finish() }

            val dialog = builder.show()
            val messageText = dialog.findViewById<View>(android.R.id.message) as TextView
            messageText.gravity = Gravity.CENTER
            messageText.textSize = 30f

            dialog
        } ?: error("Activity cannot be null")
    }
}