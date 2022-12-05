package com.designsystem.snackbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.designsystem.R
import com.google.android.material.snackbar.ContentViewCallback

internal class AppSnackbarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defaultStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defaultStyle), ContentViewCallback {

    var iconView: ImageView
    var textView: TextView
    var actionView: TextView

    init {
        View.inflate(context, R.layout.item_app_snackbar, this)
        clipToPadding = false
        iconView = findViewById(R.id.snack_bar_icon)
        textView = findViewById(R.id.snack_bar_text)
        actionView = findViewById(R.id.snack_bar_action)

    }

    fun setSnackView(icon: Drawable?, snackText: String, snackActionText: String?, snackAction: ((View) -> Unit)?){
        textView.text = snackText
        if(icon != null) snackWithIcon(icon)
        if(snackActionText != null && snackAction != null) snackWithAction(snackActionText,snackAction)
    }

    private fun snackWithIcon(icon: Drawable){
        iconView.visibility = VISIBLE
        iconView.setImageDrawable(icon)
    }

    private fun snackWithAction(snackActionText: String, snackAction: (View) -> Unit){
        actionView.visibility = VISIBLE
        val spannableString = SpannableString(snackActionText)
        spannableString.setSpan(UnderlineSpan(),0,spannableString.length,0)
        actionView.text = spannableString
        actionView.setOnClickListener(snackAction)
    }

    override fun animateContentIn(delay: Int, duration: Int) {}

    override fun animateContentOut(delay: Int, duration: Int) {}

}