package com.unsoed.responsi1mobileh1d023015.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023015.R
import com.unsoed.responsi1mobileh1d023015.data.model.Player

class PlayerAdapter(private val onPlayerClick: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {

    private var players: List<Player> = emptyList()

    fun updatePlayers(newPlayers: List<Player>) {
        players = newPlayers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_player, parent, false)
        return PlayerViewHolder(view, onPlayerClick)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int = players.size

    class PlayerViewHolder(itemView: View, private val onPlayerClick: (Player) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val cardView: CardView = itemView.findViewById(R.id.card_player)
        private val tvPlayerName: TextView = itemView.findViewById(R.id.tv_player_name)
        private val tvPlayerNationality: TextView = itemView.findViewById(R.id.tv_player_nationality)

        fun bind(player: Player) {
            tvPlayerName.text = player.name ?: "Unknown"
            tvPlayerNationality.text = player.nationality ?: "Unknown"

            // Set background color based on position
            val backgroundColor = getPositionColor(player.position)
            cardView.setCardBackgroundColor(backgroundColor)

            // Set click listener
            cardView.setOnClickListener {
                onPlayerClick(player)
            }
        }

        private fun getPositionColor(position: String?): Int {
            return when {
                position?.contains("Goalkeeper", ignoreCase = true) == true ->
                    Color.parseColor("#FFD700") // Kuning

                position?.contains("Defence", ignoreCase = true) == true ||
                position?.contains("Defender", ignoreCase = true) == true ||
                position?.contains("Centre-Back", ignoreCase = true) == true ||
                position?.contains("Left-Back", ignoreCase = true) == true ||
                position?.contains("Right-Back", ignoreCase = true) == true ->
                    Color.parseColor("#4169E1") // Biru

                position?.contains("Midfield", ignoreCase = true) == true ||
                position?.contains("Midfielder", ignoreCase = true) == true ||
                position?.contains("Defensive Midfield", ignoreCase = true) == true ||
                position?.contains("Central Midfield", ignoreCase = true) == true ||
                position?.contains("Attacking Midfield", ignoreCase = true) == true ->
                    Color.parseColor("#32CD32") // Hijau

                position?.contains("Forward", ignoreCase = true) == true ||
                position?.contains("Offence", ignoreCase = true) == true ||
                position?.contains("Winger", ignoreCase = true) == true ||
                position?.contains("Left Winger", ignoreCase = true) == true ||
                position?.contains("Right Winger", ignoreCase = true) == true ||
                position?.contains("Centre-Forward", ignoreCase = true) == true ||
                position?.contains("Striker", ignoreCase = true) == true ->
                    Color.parseColor("#FF4500") // Merah

                else -> Color.parseColor("#CCCCCC") // Abu-abu untuk posisi tidak dikenal
            }
        }
    }
}