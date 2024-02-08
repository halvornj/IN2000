package no.uio.ifi.in2000.halvorin.oblig2.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import no.uio.ifi.in2000.halvorin.oblig2.data.alpacas.AlpacaPartiesRepository
import no.uio.ifi.in2000.halvorin.oblig2.model.alpacas.PartyInfo

class HomeScreenViewModel(private val alpacaPartiesRepo: AlpacaPartiesRepository = AlpacaPartiesRepository()) : ViewModel() {
    init {
        val parties : Deferred<List<PartyInfo>> =  viewModelScope.async {
            AlpacaPartiesRepository().parties()
        }
    }

}