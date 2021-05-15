package com.devrajnish.baseapplication.ui.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devrajnish.baseapplication.model.DataWrapper
import com.devrajnish.baseapplication.model.ExampleResponse
import com.devrajnish.baseapplication.repository.DashboardRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val dashboardRepository: DashboardRepository) : ViewModel() {
    private var _sample = MutableLiveData<DataWrapper<ExampleResponse>>()
    var sampleLiveData: LiveData<DataWrapper<ExampleResponse>> = _sample

    fun getSampleData() {
        viewModelScope.launch {
            try {
                val response = dashboardRepository.getExampleFunc()
                _sample.postValue(DataWrapper(response = response))
                Log.e("response", response.toString())
            } catch (e: Exception) {
                Log.e("response", e.toString())
            }
        }
    }
}