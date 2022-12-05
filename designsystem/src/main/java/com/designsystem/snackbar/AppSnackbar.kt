package com.designsystem.snackbar

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.designsystem.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class AppSnackbar private constructor(
    parent: ViewGroup,
    content: AppSnackbarView
) : BaseTransientBottomBar<AppSnackbar>(parent, content, content) {

    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {
        fun make(viewGroup: ViewGroup,snackText : String, snackIcon: Drawable?, snackActionText: String?, snackAction: ((View) -> Unit)?) : AppSnackbar{
            val customView = LayoutInflater.from(viewGroup.context).inflate(R.layout.app_snackbar,viewGroup,false) as AppSnackbarView

            customView.setSnackView(snackIcon,snackText,snackActionText,snackAction)

            return AppSnackbar(viewGroup,customView).setDuration(Snackbar.LENGTH_INDEFINITE)
        }
    }

}