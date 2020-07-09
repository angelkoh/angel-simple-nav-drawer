package angelandroidapps.twitch.angelnavdrawer.ui.viewholders

import android.app.Activity
import android.view.View
import androidx.annotation.Keep
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import angelandroidapps.twitch.angelnavdrawer.R

// * Created by Angel on 9/7/2020 8:42 PM.  
// * Originally created for project "YA Tip Calculator".
// * Copyright (c) 2020 Angel. All rights reserved. 

@Keep
@Suppress("unused")
class SimpleDrawerViewHolder(
    activity: Activity, toolbar: Toolbar,
    drawerLayout: DrawerLayout
) : BaseDrawerViewHolder(
    activity, toolbar, drawerLayout
) {
    init {
        val parent = activity.findViewById<View>(R.id.drawer_nav_view)
        setupVersion(parent.findViewById(R.id.item_1))
        setupShare(activity, parent.findViewById(R.id.item_2))
        setupGiveRatings(activity, parent.findViewById(R.id.item_3))
    }
}