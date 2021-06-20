package de.niklasenglmeier.lioconnect

import java.util.*

object ColorDetection {
    var colorMap: HashMap<String?, String?> = object : HashMap<String?, String?>() {
        init {
            put("rot", "128 0 0")
            put("grün", "0 128 0")
            put("blau", "0 0 128")
            put("magenta", "128 0 128")
            put("gelb", "128 128 0")
            put("türkis", "0 128 128")
            put("weiß", "128 128 128")
            put("schwarz", "0 0 0")
        }
    }
}