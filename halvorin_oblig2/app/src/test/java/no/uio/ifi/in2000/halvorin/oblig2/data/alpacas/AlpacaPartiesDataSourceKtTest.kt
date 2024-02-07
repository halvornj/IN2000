package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import android.webkit.URLUtil
import androidx.core.net.toUri
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.decodeURLPart
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class AlpacaPartiesDataSourceKtTest{
    @Test
    fun response_NotEmpty(){
        runBlocking {
            val data = getAlpacaPartiesData()

            assert(data.isNotEmpty()) { "expected size>0, got ${data.size}" } //kotlin really got a method for everything, huh
        }
    }
    @Test
    fun correct_entryCount(){
        runBlocking {
            assert(getAlpacaPartiesData().size == 4)
        }
    }
    @Test
    fun valid_imageURLs(){
        runBlocking {
            assert(URLUtil.isValidUrl(getAlpacaPartiesData()[0].img))
        }
    }
}
