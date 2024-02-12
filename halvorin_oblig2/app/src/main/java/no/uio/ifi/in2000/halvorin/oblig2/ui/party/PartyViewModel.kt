package no.uio.ifi.in2000.halvorin.oblig2.ui.party

import androidx.lifecycle.ViewModel
import no.uio.ifi.in2000.halvorin.oblig2.data.alpacas.AlpacaPartiesRepository


class PartyViewModel(
    private val alpacaPartiesRepo: AlpacaPartiesRepository = AlpacaPartiesRepository(),
    //ui-states

):ViewModel(){
    init {
        //TODO how to get id from screen to viewModel
        //getInfoAboutParty()
    }


    private fun getInfoAboutParty(id:String){

    }
}