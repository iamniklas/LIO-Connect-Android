package de.niklasenglmeier.lioconnect.lio.colors

import com.google.gson.annotations.SerializedName
import de.niklasenglmeier.lioconnect.lio.ColorChannel
import de.niklasenglmeier.lioconnect.lio.*

class ColorRGB {
    @SerializedName("R")
    var r: Int = 255

    @SerializedName("G")
    var g: Int = 255

    @SerializedName("B")
    var b: Int = 255

    constructor(_r: Int, _g: Int, _b: Int) {
        r = _r
        g = _g
        b = _b
    }

    constructor(_color: ColorRGB) {
        r = _color.r
        g = _color.g
        b = _color.b
    }

    fun filter(_channel: ColorChannel?): ColorRGB {
        return when (_channel) {
            ColorChannel.Red -> ColorRGB(0, g, b)
            ColorChannel.Green -> ColorRGB(r, 0, b)
            ColorChannel.Blue -> ColorRGB(r, g, 0)
            else -> ColorRGB(0, 0, 0)
        }
    }

    fun setChannel(_channel: ColorChannel?, _value: Int) {
        when (_channel) {
            ColorChannel.Red -> r = _value
            ColorChannel.Green -> g = _value
            ColorChannel.Blue -> b = _value
            else -> {
            }
        }
    }

    //Cuts a channel from the lower side - if the channel value is higher than the filter it will be set to 0
    fun cutLow(_channel: ColorChannel?, _filter: Int): ColorRGB {
        var _filter = _filter
        _filter = Math.max(Math.min(_filter, 255), 0)
        return when (_channel) {
            ColorChannel.Red -> ColorRGB(cutLow(r, _filter), g, b)
            ColorChannel.Green -> ColorRGB(r, cutLow(g, _filter), b)
            ColorChannel.Blue -> ColorRGB(r, g, cutLow(b, _filter))
            else -> this
        }
    }

    //Cuts a channel from the higher side - if the channel value is lower than the filter it will be set to 0
    fun cutHigh(_channel: ColorChannel?, _filter: Int): ColorRGB {
        var _filter = _filter
        _filter = Math.max(Math.min(_filter, 255), 0)
        return when (_channel) {
            ColorChannel.Red -> ColorRGB(cutHigh(r, _filter), g, b)
            ColorChannel.Green -> ColorRGB(r, cutHigh(g, _filter), b)
            ColorChannel.Blue -> ColorRGB(r, g, cutHigh(b, _filter))
            else -> this
        }
    }

    fun toRGBA(_alpha: Int): ColorRGBA {
        return ColorRGBA(r, g, b, _alpha)
    }

    fun toHSV(): ColorHSV {
        return ColorHSV(0, 0.0f, 0.0f)
    }

    private fun cutLow(_value: Int, _filter: Int): Int {
        return if (_value >= _filter) {
            0
        } else {
            _value
        }
    }

    private fun cutHigh(_value: Int, _filter: Int): Int {
        return if (_value <= _filter) {
            0
        } else {
            _value
        }
    }

    override fun toString(): String {
        return "$r $g $b"
    }

    companion object {
        val black = ColorRGB(0, 0, 0)
        val white = ColorRGB(255, 255, 255)
        val red = ColorRGB(255, 0, 0)
        val green = ColorRGB(0, 255, 0)
        val blue = ColorRGB(0, 0, 255)
        val orange = ColorRGB(255, 255, 0)
        val magenta = ColorRGB(255, 0, 255)
        val torquoise = ColorRGB(0, 255, 255)
    }
}