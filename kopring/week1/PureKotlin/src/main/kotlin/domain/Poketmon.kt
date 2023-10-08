package domain

open class Poketmon(private var name: String?, private var type: PoketmonType) {
    constructor(type: PoketmonType) : this(null, type)
}