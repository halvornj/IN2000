package no.uio.ifi.in2000.halvorin.oblig1

fun isPalindrome(tekst:String):Boolean{
    //testene mener et palindrom er case-insensitive :/
    return tekst.lowercase() == tekst.reversed().lowercase()
}