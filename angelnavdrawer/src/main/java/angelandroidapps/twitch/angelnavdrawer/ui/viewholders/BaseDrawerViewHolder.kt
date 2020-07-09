package angelandroidapps.twitch.angelnavdrawer.ui.viewholders

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import angelandroidapps.twitch.angelnavdrawer.BuildConfig
import angelandroidapps.twitch.angelnavdrawer.R

// * Created by Angel on 9/7/2020 8:16 PM.  
// * Originally created for project "YA Tip Calculator".
// * Copyright (c) 2020 Angel. All rights reserved. 

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class BaseDrawerViewHolder(
        activity: Activity,
        toolbar: Toolbar,
        private val drawerLayout: DrawerLayout
) {

    init {
        val toggle = ActionBarDrawerToggle(
                activity, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )
        toggle.syncState()

        drawerLayout.findViewById<ImageView>(R.id.iv_logo).drawable
        drawerLayout.findViewById<TextView>(R.id.tv_app_title).setText(R.string.app_name)

    }

    fun setupGiveRatings(activity: Activity, parent: View) {
        parent.visibility = View.VISIBLE
        DrawerItemViewHolder(parent) {
            doGiveRatings(activity)
            closeDrawers()
        }.also {
            it.setup(
                    R.drawable.ic_drawer_ratings,
                    R.string.label_give_ratings,
                    R.string.summary_give_ratings
            )
        }
    }

    fun setupShare(activity: Activity, parent: View) {

        parent.visibility = View.VISIBLE
        DrawerItemViewHolder(parent) {
            doShareApp(activity)
            closeDrawers()
        }.also {
            it.setup(
                    R.drawable.ic_drawer_share, R.string.label_share,
                    R.string.summary_share
            )
        }
    }

    fun setupVersion(parent: View) {
        parent.visibility = View.VISIBLE
        DrawerItemViewHolder(parent).also {

            it.setup(R.drawable.ic_drawer_version, R.string.label_version, BuildConfig.VERSION_NAME)
        }
    }

    fun isDrawerOpened() = drawerLayout.isDrawerOpen(GravityCompat.START)
    fun closeDrawers() = drawerLayout.closeDrawers()


    protected fun setupDrawerItem(
            drawerView: View,
            parentResId: Int,
            iconResId: Int,
            titleResId: Int,
            subtitle: String,
            onClickCallback: (() -> Unit)? = null
    ): TextView? {
        val drawerItem1 = drawerView.findViewById<View>(parentResId)
        drawerItem1.visibility = View.VISIBLE
        drawerItem1.findViewById<ImageView>(R.id.iv)
                .setImageResource(iconResId)
        drawerItem1.findViewById<TextView>(R.id.tv_title).setText(titleResId)
        val tvSubtitle = drawerItem1.findViewById<TextView>(R.id.tv_subtitle)
        tvSubtitle.text = subtitle
        if (onClickCallback != null) {
            drawerItem1.setOnClickListener { onClickCallback() }
        }
        return tvSubtitle
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun setupDrawerItem(
            drawerView: View,
            parentResId: Int,
            iconResId: Int,
            titleResId: Int,
            subtitleResId: Int,
            onClickCallback: (() -> Unit)? = null
    ): TextView? {
        val drawerItem1 = drawerView.findViewById<View>(parentResId)
        drawerItem1.visibility = View.VISIBLE

        drawerItem1.findViewById<ImageView>(R.id.iv)
                .setImageResource(iconResId)
        drawerItem1.findViewById<TextView>(R.id.tv_title).setText(titleResId)
        val tvSubtitle = drawerItem1.findViewById<TextView>(R.id.tv_subtitle)
        tvSubtitle.setText(subtitleResId)
        if (onClickCallback != null) {
            drawerItem1.setOnClickListener { onClickCallback() }
        }
        return tvSubtitle
    }


    private fun doGiveRatings(activity: Activity) {
        openAppStore(activity, activity.packageName)

    }

    private fun openAppStore(activity: Activity, appPackageName: String) {

        print("appStore: $appPackageName")
        try {
            activity.startActivity(
                    Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=$appPackageName")
                    )
            )
        } catch (e: Exception) {
            activity.startActivity(
                    Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
            )
        }
    }

    private fun doShareApp(activity: Activity) {
        val appPackageName = activity.packageName
        val shareBody = "https://play.google.com/store/apps/details?id=$appPackageName"

        val appName = activity.getString(R.string.app_name)
        val installMessage = activity.getString(R.string.message_share, appName)
        val shareVia = activity.getString(R.string.message_share_via, appName)

        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(
                Intent.EXTRA_SUBJECT, installMessage
        )

        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)

        print("Share: $sharingIntent")
        activity.startActivity(
                Intent.createChooser(sharingIntent, shareVia)
        )
    }

    companion object {
        private const val TAG = "Angel: drawerVH"
        private fun print(s: String) = Log.d(TAG, s)
    }

}