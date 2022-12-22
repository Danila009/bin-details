package ru.pgk63.focusstart.ui.screens.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.pgk63.focusstart.data.database.user.model.RequestBinHistory
import ru.pgk63.focusstart.data.database.user.repository.RequestBinHistoryRepository
import ru.pgk63.focusstart.data.network.bin.model.BinDetails
import ru.pgk63.focusstart.data.network.bin.repository.BinRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val binRepository: BinRepository,
    private val requestBinHistoryRepository: RequestBinHistoryRepository
): ViewModel() {

    private val _responseBinDetails = MutableStateFlow<BinDetails?>(null)
    val responseBinDetails = _responseBinDetails.filterNotNull()

    val requestBinHistory = requestBinHistoryRepository.getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, listOf())

    fun getBinDetails(bin:String){
        viewModelScope.launch {
            try {
                requestBinHistoryRepository.add(RequestBinHistory(name = bin))

                _responseBinDetails.value = binRepository.getDetails(bin)
            }catch (e:Exception){
                Log.e("getBinDetails",e.message.toString())
            }
        }
    }
}