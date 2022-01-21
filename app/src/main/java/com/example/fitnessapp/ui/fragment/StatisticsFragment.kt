package com.example.fitnessapp.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.service.autofill.CharSequenceTransformation
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.fitnessapp.R
import com.example.fitnessapp.other.TrackingUtility
import com.example.fitnessapp.viewmodel.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.github.farshidroohi.ChartEntity
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.lang.Math.round
import java.lang.NullPointerException
import javax.annotation.Nullable
import kotlin.math.roundToInt

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToObserver()

    }

    private fun subscribeToObserver() {
        viewModel.totalTimeRun.observe(viewLifecycleOwner, {
            it?.let {
                val totalTimeRun = TrackingUtility.getFormattedStopWatch(it)
                tvTotalTime.text = totalTimeRun
            }
        })
        viewModel.totalDistance.observe(viewLifecycleOwner, {
            it?.let {
                val km = it / 1000f
                val totalDistance = (km * 10f).roundToInt() / 10f
                val totalDistanceString = "${totalDistance}Km"
                tvTotalDistance.text = totalDistanceString
            }
        })
        viewModel.totalAvgSpeed.observe(viewLifecycleOwner, {
            it?.let {
                val avgSpeed = round(it * 10f) / 10f
                val avgSpeedString = "${avgSpeed}Km/h"
                tvAverageSpeed.text = avgSpeedString
            }
        })
        viewModel.totalCaloriesBurned.observe(viewLifecycleOwner, {
            it?.let {
                val totalCalories = "${it}Kcal"
                tvTotalCalories.text = totalCalories
            }
        })

    }
}