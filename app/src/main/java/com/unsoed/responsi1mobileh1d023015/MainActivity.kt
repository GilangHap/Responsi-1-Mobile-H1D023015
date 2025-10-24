package com.unsoed.responsi1mobileh1d023015

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupMenuItems()
    }

    private fun setupMenuItems() {
        MenuHelper.setupIncludedMenuItem(
            findViewById(R.id.layout_club_history),
            R.drawable.ball,
            getString(R.string.club_history)
        ) { 
            val intent = Intent(this, ClubHistoryActivity::class.java)
            startActivity(intent)
        }

        MenuHelper.setupIncludedMenuItem(
            findViewById(R.id.layout_head_coach),
            R.drawable.manager,
            getString(R.string.head_coach)
        ) { 
            val intent = Intent(this, HeadCoachActivity::class.java)
            startActivity(intent)
        }

        MenuHelper.setupIncludedMenuItem(
            findViewById(R.id.layout_team_squad),
            R.drawable.team,
            getString(R.string.team_squad)
        ) { 
            val intent = Intent(this, TeamSquadActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}