package no.uio.ifi.in2000.halvorin.oblig1

import android.graphics.drawable.Icon
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.platform.coreshims.SoftwareKeyboardControllerCompat
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import no.uio.ifi.in2000.halvorin.oblig1.ui.theme.Halvorin_oblig1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Halvorin_oblig1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PalindromeChecker()
                }
            }
        }
    }
}

@Suppress("SpellCheckingInspection")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PalindromeChecker(modifier:Modifier = Modifier){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally){
        var text by remember { mutableStateOf("") }
        var answer by remember { mutableStateOf("") }

        val keyboardController = LocalSoftwareKeyboardController.current
        fun submit(s:String){
            keyboardController?.hide()
            //TODO replace log w/ Palindrome-call, and set output to some textbox
            Log.d("DEBUG", s)
            answer = "teksten er${if(!isPalindrome(s)){" ikke"}else ""} et palindrom!" //<- litt goofy
        }

        Row (horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically){
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Skriv inn") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { submit(text) })
            )
            Button(onClick = {submit(text) }) {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "")
            }
        }
        Text(text = answer)
    }


}

@Preview(showSystemUi = true)
@Composable
fun PalindromeCheckerPreview() {
    Halvorin_oblig1Theme {
        PalindromeChecker()
    }
}