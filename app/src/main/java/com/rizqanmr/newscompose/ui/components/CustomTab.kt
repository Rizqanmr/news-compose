package com.rizqanmr.newscompose.ui.components

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.rizqanmr.newscompose.R

object CustomTab {
    private var builder: CustomTabsIntent? = null
    private var builderDark: CustomTabsIntent? = null

    fun launch(context: Context, url: String, isDark: Boolean) {
        if (isDark) {
            if (builderDark == null) {
                builderDark = CustomTabsIntent.Builder()
                    .setToolbarColor(
                        ContextCompat.getColor(context, R.color.darkRed)
                    )
                    .setShowTitle(true)
                    .setStartAnimations(
                        context,
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                    )
                    .setExitAnimations(
                        context,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    .build()
            }
            builderDark?.launchUrl(context, Uri.parse(url))
        } else {
            if (builder == null) {
                builder = CustomTabsIntent.Builder()
                    .setToolbarColor(
                        ContextCompat.getColor(context, R.color.lightRed)
                    )
                    .setShowTitle(true)
                    .setStartAnimations(
                        context,
                        R.anim.slide_in_right,
                        R.anim.slide_out_left
                    )
                    .setExitAnimations(
                        context,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    .build()
            }
            builder?.launchUrl(context, Uri.parse(url))
        }
    }
}