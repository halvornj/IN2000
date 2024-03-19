package no.uio.ifi.in2000.halvorin.oblig2.ui.party

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.halvorin.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo
import no.uio.ifi.in2000.halvorin.oblig2.ui.home.PartyInfoUiState


data class PartyUiState(
    //initialize with empty party
    val partyInfo:PartyInfo = PartyInfo("","","","","#ffffff","")
)

class PartyViewModel(
    savedStateHandle: SavedStateHandle,


    ):ViewModel(){
    private val TAG: String = "PartyViewModel"

    private val alpacaPartiesRepo: AlpacaPartiesRepository = AlpacaPartiesRepository()
    //ui-states
    private val _partyUiState: MutableStateFlow<PartyUiState> = MutableStateFlow(PartyUiState())
    val partyUiState:StateFlow<PartyUiState> = _partyUiState.asStateFlow()
    private val id: String = checkNotNull(savedStateHandle["id"])
    init {
        //DEBUG
        Log.d(TAG, id)
        getInfoAboutParty(id)
    }
    private fun getInfoAboutParty(id:String){
        //DEBUG
        Log.d(TAG, id)

        viewModelScope.launch (Dispatchers.IO){
            _partyUiState.update { currentState ->
                val party = alpacaPartiesRepo.getPartyInfo(id)

                currentState.copy(partyInfo = party)
            }
        }
    }
}