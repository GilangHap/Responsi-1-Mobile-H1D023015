package com.unsoed.responsi1mobileh1d023015.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.unsoed.responsi1mobileh1d023015.R
import com.unsoed.responsi1mobileh1d023015.data.model.Player

class PlayerDetailFragment(
    private val player: Player
) : BottomSheetDialogFragment() {
    
    private lateinit var tvPlayerName: TextView
    private lateinit var tvPlayerBirthDate: TextView
    private lateinit var tvPlayerNationality: TextView
    private lateinit var tvPlayerPosition: TextView
    
    override fun getTheme(): Int = R.style.ThemeOverlay_App_BottomSheetDialog
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_detail, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initViews(view)
        loadPlayerData()
    }
    
    private fun initViews(view: View) {
        tvPlayerName = view.findViewById(R.id.tv_detail_player_name)
        tvPlayerBirthDate = view.findViewById(R.id.tv_detail_player_birth_date)
        tvPlayerNationality = view.findViewById(R.id.tv_detail_player_nationality)
        tvPlayerPosition = view.findViewById(R.id.tv_detail_player_position)
    }
    
    private fun loadPlayerData() {
        tvPlayerName.text = player.name ?: "Unknown"
        tvPlayerBirthDate.text = player.dateOfBirth ?: "Unknown"
        tvPlayerNationality.text = player.nationality ?: "Unknown"
        tvPlayerPosition.text = player.position ?: "Unknown"
    }
}