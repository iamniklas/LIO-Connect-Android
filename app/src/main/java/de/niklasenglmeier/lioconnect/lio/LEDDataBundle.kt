package de.niklasenglmeier.lioconnect.lio

import java.util.*

class LEDDataBundle {
    var mBundle: MutableMap<ProcedureBundleFields, Any?> = HashMap()

    operator fun set(_fieldType: ProcedureBundleFields, _object: Any?) {
        mBundle[_fieldType] = _object
    }

    fun hasKey(_field: ProcedureBundleFields): Boolean {
        return mBundle.containsKey(_field)
    }

    operator fun get(_fieldType: ProcedureBundleFields): Any? {
        return mBundle.get(_fieldType)
    }
}