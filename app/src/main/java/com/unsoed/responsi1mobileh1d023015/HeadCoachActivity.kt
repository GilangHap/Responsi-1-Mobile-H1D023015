package com.unsoed.responsi1mobileh1d023015

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.unsoed.responsi1mobileh1d023015.data.model.TeamResponse
import com.unsoed.responsi1mobileh1d023015.data.network.NetworkClient
import com.unsoed.responsi1mobileh1d023015.utils.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HeadCoachActivity : AppCompatActivity() {
    
    private lateinit var tvCoachName: TextView
    private lateinit var tvCoachBirthDate: TextView
    private lateinit var tvCoachNationality: TextView
    private lateinit var ivCoachPhoto: ImageView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_head_coach)
        
        supportActionBar?.apply {
            title = "Head Coach"
            setDisplayHomeAsUpEnabled(true)
        }
        
        initViews()
        fetchCoachData()
    }
    
    private fun initViews() {
        tvCoachName = findViewById(R.id.tv_coach_name)
        tvCoachBirthDate = findViewById(R.id.tv_coach_birth_date)
        tvCoachNationality = findViewById(R.id.tv_coach_nationality)
        ivCoachPhoto = findViewById(R.id.iv_coach_photo)
    }
    
    private fun fetchCoachData() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            showError("No internet connection.")
            return
        }
        
        val call = NetworkClient.footballApiService.getTeam(79, NetworkClient.API_KEY)
        
        call.enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val team = response.body()!!
                    val coach = team.coach
                    
                    if (coach != null) {
                        tvCoachName.text = coach.name ?: "Unknown"
                        tvCoachBirthDate.text = coach.dateOfBirth ?: "Unknown"
                        tvCoachNationality.text = coach.nationality ?: "Unknown"
                        
                        Log.d("HeadCoachActivity", "Coach data loaded: ${coach.name}")
                    } else {
                        showError("Coach data not available")
                    }
                } else {
                    showError("Failed to load coach data: ${response.code()}")
                }
            }
            
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                showError("Network error: ${t.message}")
                Log.e("HeadCoachActivity", "Network error", t)
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