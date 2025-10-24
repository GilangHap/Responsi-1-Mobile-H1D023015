# 🔴⚫ CA Osasuna Mobile App - Responsi 1 Mobile

<div align="center">
  <img src="app/src/main/res/drawable/logo_osasuna.png" alt="CA Osasuna Logo" width="120"/>
  
  **Aplikasi Mobile Informasi Tim CA Osasuna**
  
  ![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
  ![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
  ![API](https://img.shields.io/badge/Football%20Data%20API-FF6B35?style=for-the-badge&logo=api&logoColor=white)
</div>

**Nama**: Gilang Happy Dwinugroho 
**NIM**: H1D023015  
**Shift Awal**: C
**Shift Baru**: C

### 1. 📡 Pengambilan Data dari API

```
Football Data API → NetworkClient → FootballApiService → Activity/Fragment
```

#### **a. Network Configuration**
```kotlin
// NetworkClient.kt
object NetworkClient {
    private const val BASE_URL = "https://api.football-data.org/v4/"
    
    private val okHttpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }
}
```

#### **b. API Service Interface**
```kotlin
// FootballApiService.kt
interface FootballApiService {
    @GET("teams/{teamId}")
    fun getTeam(
        @Path("teamId") teamId: Int,
        @Header("X-Auth-Token") authToken: String
    ): Call<TeamResponse>
}
```

### 2. 🔄 Proses Data Processing

#### **a. Data Model Structure**
```
TeamResponse
├── Coach (untuk HeadCoachActivity)
└── Squad: List<Player> (untuk TeamSquadActivity)
    ├── name: String
    ├── position: String  
    ├── nationality: String
    └── dateOfBirth: String
```

#### **b. API Call Implementation**
```kotlin
// Contoh implementasi di TeamSquadActivity.kt
private fun loadSquadData() {
    // 1. Cek koneksi internet
    if (!NetworkUtils.isNetworkAvailable(this)) {
        showError("No internet connection.")
        return
    }
    
    // 2. Panggil API dengan Retrofit
    val call = NetworkClient.footballApiService.getTeam(79, NetworkClient.API_KEY)
    
    // 3. Eksekusi asynchronous call
    call.enqueue(object : Callback<TeamResponse> {
        override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
            if (response.isSuccessful && response.body() != null) {
                val team = response.body()!!
                val squad = team.squad
                
                // 4. Update UI dengan data
                if (squad != null && squad.isNotEmpty()) {
                    playerAdapter.updatePlayers(squad)
                } else {
                    showError("No squad data available.")
                }
            } else {
                showError("Failed to load squad data: ${response.code()}")
            }
        }
        
        override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
            showError("Network error: ${t.message}")
        }
    })
}
```

### 3. 🎨 Penyajian Data di UI

#### **a. Color-Coded Player Cards**
```kotlin
// PlayerAdapter.kt - Sistem pewarnaan berdasarkan posisi
private fun getPositionColor(position: String): Int {
    return when {
        position.contains("Goalkeeper", ignoreCase = true) -> 
            ContextCompat.getColor(context, R.color.goalkeeper_yellow)
        position.contains("Defender", ignoreCase = true) || 
        position.contains("Defence", ignoreCase = true) -> 
            ContextCompat.getColor(context, R.color.defender_blue)
        position.contains("Midfielder", ignoreCase = true) || 
        position.contains("Midfield", ignoreCase = true) -> 
            ContextCompat.getColor(context, R.color.midfielder_green)
        position.contains("Forward", ignoreCase = true) || 
        position.contains("Attacker", ignoreCase = true) || 
        position.contains("Winger", ignoreCase = true) -> 
            ContextCompat.getColor(context, R.color.forward_red)
        else -> ContextCompat.getColor(context, R.color.default_gray)
    }
}
```

#### **b. Interactive Player Details**
```kotlin
// Click handler untuk menampilkan detail pemain
holder.itemView.setOnClickListener {
    val fragment = PlayerDetailFragment(player)
    fragment.show(
        (context as AppCompatActivity).supportFragmentManager, 
        "PlayerDetailFragment"
    )
}
```


## 📹 Demo Video

https://github.com/user-attachments/assets/db5774df-454d-4367-a15d-90d739e05995

---

<div align="center">
  <strong>⚽ Fans MU Sejati ⚽</strong>
</div>

