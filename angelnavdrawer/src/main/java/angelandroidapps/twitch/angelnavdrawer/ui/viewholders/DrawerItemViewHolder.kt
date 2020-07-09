package angelandroidapps.twitch.angelnavdrawer.ui.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import angelandroidapps.twitch.angelnavdrawer.R

// * Created by Angel on 9/7/2020 7:54 PM.  
// * Originally created for project "YA Tip Calculator".
// * Copyright (c) 2020 Angel. All rights reserved. 

@Suppress("unused")
class DrawerItemViewHolder(parent: View, onItemClicked: (() -> Unit)? = null) {

    private val iv: ImageView = parent.findViewById(R.id.iv)
    private val tvTitle: TextView = parent.findViewById(R.id.tv_title)
    private val tvSubtitle: TextView = parent.findViewById(R.id.tv_subtitle)

    init {
        if (onItemClicked != null) {
            parent.setOnClickListener { onItemClicked.invoke() }
        }
    }

    fun setup(iconResId: Int, title: Int, subtitle: Int) {
        iv.setImageResource(iconResId)
        tvTitle.setText(title)
        tvSubtitle.setText(subtitle)
    }

    fun setup(iconResId: Int, title: Int, subtitle: String) {
        iv.setImageResource(iconResId)
        tvTitle.setText(title)
        tvSubtitle.text = subtitle
    }

    fun updateSubtitle(subtitle: Int) {
        tvSubtitle.setText(subtitle)
    }

    fun updateSubtitle(subtitle: String) {
        tvSubtitle.text = subtitle
    }
}