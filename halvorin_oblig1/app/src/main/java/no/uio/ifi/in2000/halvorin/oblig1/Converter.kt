package no.uio.ifi.in2000.halvorin.oblig1

import kotlin.math.roundToInt


fun converter(verdi:Int, unit:ConverterUnits):Int{
    //antar fra oppgaveteksten for del 2 at vi konverterer fra unit til liter.
    val conversionRates = mapOf<ConverterUnits, Double>(ConverterUnits.OUNCE to 0.02957,
                                            ConverterUnits.CUP to 0.23659,
                                            ConverterUnits.GALLON to 3.78541,
                                            ConverterUnits.HOGSHEAD to 238.481)

    //non-null, fordi alle ConverterUnits er keys i conversionRates
    return (conversionRates[unit]!! *verdi).roundToInt()
}