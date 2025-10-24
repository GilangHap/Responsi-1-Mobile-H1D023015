package com.unsoed.responsi1mobileh1d023015.data.network

import com.unsoed.responsi1mobileh1d023015.data.model.TeamResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface FootballApiService {

    @GET("teams/{teamId}")
    fun getTeam(
        @Path("teamId") teamId: Int,
        @Header("X-Auth-Token") authToken: String
    ): Call<TeamResponse>
}