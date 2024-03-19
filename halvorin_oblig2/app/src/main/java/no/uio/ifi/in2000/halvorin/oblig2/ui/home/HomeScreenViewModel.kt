package no.uio.ifi.in2000.halvorin.oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.halvorin.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo

data class PartyInfoUiState(
    val parties : List<PartyInfo> = emptyList()
)

class HomeScreenViewModel(
    private val alpacaPartiesRepo: AlpacaPartiesRepository = AlpacaPartiesRepository(),
    //maybe clean these two up a bit
    private val _partyInfoUiState: MutableStateFlow<PartyInfoUiState> = MutableStateFlow(PartyInfoUiState()),
    val partyInfoUiState : StateFlow<PartyInfoUiState> = _partyInfoUiState.asStateFlow()
    ) : ViewModel() {
    init {
        loadPartyInfo()
    }

   private fun loadPartyInfo(){
        viewModelScope.launch (Dispatchers.IO) {
            _partyInfoUiState.update { currentState ->
                val parties = alpacaPartiesRepo.parties()

                currentState.copy(parties = parties)
            }
        }

   }

}