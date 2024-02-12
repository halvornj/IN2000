package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.Test

class AlpacaPartiesRepositoryTest{
    val repo = AlpacaPartiesRepository()


    //siden repoet i oppgave 1 ikke slår sammen men bare videreformidler data fungerer dette
    @Test
    fun propagates_properly(){
        runBlocking {
            assert(repo.parties() == AlpacaPartiesDataSource().getAlpacaPartiesData())
        }
    }

    //part 2
    @Test
    fun correct_id(){
        runBlocking {
            //data copied directly from endpoint
            assert(repo.getPartyInfo(2).name=="AlpacaEast")
            assert(repo.getPartyInfo(4).color=="#b0c0bc")
        }
    }

}