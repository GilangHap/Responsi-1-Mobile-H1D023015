package com.unsoed.responsi1mobileh1d023015

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class MenuHelper {

    companion object {
        fun addMenuItem(
            context: Context,
            parent: LinearLayout,
            iconResource: Int,
            text: String,
            clickListener: View.OnClickListener
        ): CardView {
            val inflater = LayoutInflater.from(context)
            val menuItem = inflater.inflate(R.layout.layout_menu, parent, false) as CardView
 
            val iconView = menuItem.findViewById<ImageView>(R.id.menu_icon)
            val textView = menuItem.findViewById<TextView>(R.id.menu_text)
            
            iconView.setImageResource(iconResource)
            textView.text = text
            
            menuItem.setOnClickListener(clickListener)
            
            parent.addView(menuItem)
            
            return menuItem
        }

        fun setupIncludedMenuItem(
            includedView: View,
            iconResource: Int,
            text: String,
            clickListener: View.OnClickListener
        ) {
            val iconView = includedView.findViewById<ImageView>(R.id.menu_icon)
            val textView = includedView.findViewById<TextView>(R.id.menu_text)
            
            iconView.setImageResource(iconResource)
            textView.text = text
            includedView.setOnClickListener(clickListener)
        }

        fun setupMainMenuItems(context: Context, parent: LinearLayout, clickHandler: (String) -> Unit) {
            addMenuItem(context, parent, R.drawable.ball, "Club History") {
                clickHandler("Club History clicked")
            }
            
            addMenuItem(context, parent, R.drawable.manager, "Head Coach") {
                clickHandler("Head Coach clicked")
            }
            
            addMenuItem(context, parent, R.drawable.team, "Team Squad") {
                clickHandler("Team Squad clicked")
            }
        }
    }
}