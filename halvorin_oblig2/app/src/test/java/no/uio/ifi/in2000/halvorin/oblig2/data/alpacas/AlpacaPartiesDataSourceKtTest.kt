package no.uio.ifi.in2000.halvorin.oblig2.data.alpacas

import android.webkit.URLUtil
import androidx.core.net.toUri
import androidx.core.text.isDigitsOnly
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.decodeURLPart
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import java.net.URL

class AlpacaPartiesDataSourceKtTest{
    private val dataSource = AlpacaPartiesDataSource()
    @Test
    fun response_NotEmpty(){
        runBlocking {
            val data = dataSource.getAlpacaPartiesData()

            assert(data.isNotEmpty()) { "expected size>0, got ${data.size}" } //kotlin really got a method for everything, huh
        }
    }
    @Test
    fun correct_entryCount(){
        runBlocking {
            assert(dataSource.getAlpacaPartiesData().size == 4)
        }
    }
    @Test
    fun valid_imageURLs(){
        fun isValidURL(s:String):Boolean {
            return try {
                URL(s)
                true
            } catch (e: Exception) {
                false
            }
        }
        runBlocking {
            assert(isValidURL(dataSource.getAlpacaPartiesData()[0].img))
        }
    }
    @Test
    fun valid_ids(){
        fun isDigitsOnly(s:String):Boolean {
            return try {
                s.toInt()
                true
            } catch (e: Exception) {
                false
            }
        }
        runBlocking{
            assert(isDigitsOnly(dataSource.getAlpacaPartiesData()[0].id))
        }
    }
}

