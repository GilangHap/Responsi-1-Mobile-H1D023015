package com.unsoed.responsi1mobileh1d023015

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobileh1d023015.ui.adapter.PlayerAdapter
import com.unsoed.responsi1mobileh1d023015.data.model.Player
import com.unsoed.responsi1mobileh1d023015.data.model.TeamResponse
import com.unsoed.responsi1mobileh1d023015.ui.fragment.PlayerDetailFragment
import com.unsoed.responsi1mobileh1d023015.data.network.NetworkClient
import com.unsoed.responsi1mobileh1d023015.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamSquadActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var playerAdapter: PlayerAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_team_squad)
        
        supportActionBar?.apply {
            title = "Team Squad"
            setDisplayHomeAsUpEnabled(true)
        }
        
        initViews()
        fetchSquadData()
    }
    
    private fun initViews() {
        recyclerView = findViewById(R.id.rv_players)
        
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        playerAdapter = PlayerAdapter { player ->
            showPlayerDetail(player)
        }
        recyclerView.adapter = playerAdapter
    }
    
    private fun showPlayerDetail(player: Player) {
        val fragment = PlayerDetailFragment(player)
        fragment.show(supportFragmentManager, "PlayerDetailFragment")
    }
    
    private fun fetchSquadData() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            showError("No internet connection.")
            return
        }
        
        val call = NetworkClient.footballApiService.getTeam(79, NetworkClient.API_KEY)
        
        call.enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val team = response.body()!!
                    val squad = team.squad
                    
                    if (squad != null && squad.isNotEmpty()) {
                        // Update RecyclerView with squad data
                        playerAdapter.updatePlayers(squad)
                        Log.d("TeamSquadActivity", "Squad data loaded: ${squad.size} players")
                    } else {
                        showError("Squad data not available")
                    }
                } else {
                    showError("Failed to load squad data: ${response.code()}")
                }
            }
            
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                showError("Network error: ${t.message}")
                Log.e("TeamSquadActivity", "Network error", t)
            }
        })
    }
    
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}