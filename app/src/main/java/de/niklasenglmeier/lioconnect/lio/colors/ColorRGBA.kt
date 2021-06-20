package de.niklasenglmeier.lioconnect.lio.colors

class ColorRGBA(_r: Int, _g: Int, _b: Int, _a: Int) {
    var r = 255
    var g = 255
    var b = 255
    var a = 255
    fun toRGB(): ColorRGB {
        return ColorRGB(
            (255 - a) * ColorRGB.black.r + a * r,
            (255 - a) * ColorRGB.black.g + a * g,
            (255 - a) * ColorRGB.black.b + a * b
        )
    }

    fun toRGB(_baseColor: ColorRGB): ColorRGB {
        return ColorRGB(
            ((1 - a / 255.0f) * _baseColor.r + a / 255.0f * r).toInt(),
            ((1 - a / 255.0f) * _baseColor.g + a / 255.0f * g).toInt(),
            ((1 - a / 255.0f) * _baseColor.b + a / 255.0f * b).toInt()
        )
    }

    override fun toString(): String {
        return String.format("R%d G%d B%d A%d", r, g, b, a)
    }

    init {
        r = _r
        g = _g
        b = _b
        a = _a
    }
}