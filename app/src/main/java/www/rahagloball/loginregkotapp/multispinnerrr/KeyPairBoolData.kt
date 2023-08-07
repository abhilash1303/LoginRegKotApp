package www.rahagloball.loginregkotapp.multispinnerrr


class KeyPairBoolData(
    var idValue: Long,
    var nameValue: String,
    var isSelectedValue: Boolean,
    var dataObject: Any? = null
) {
    fun getId(): Long {
        return idValue
    }

    fun getName(): String {
        return nameValue
    }

    fun isSelected(): Boolean {
        return isSelectedValue
    }

    fun getObject(): Any? {
        return dataObject
    }

    fun setObject(`object`: Any?) {
        this.dataObject = `object`
    }

    override fun toString(): String {
        return nameValue
    }
}

//
//class KeyPairBoolData(
//    var id: Long,
//    var name: String,
//    var isSelected: Boolean,
//    var `object`: Any? = null
//) {
//    fun getId(): Long {
//        return id
//    }
//
//    fun setId(id: Long) {
//        this.id = id
//    }
//
//    fun getName(): String {
//        return name
//    }
//
//    fun setName(name: String) {
//        this.name = name
//    }
//
//    fun isSelected(): Boolean {
//        return isSelected
//    }
//
//    fun setSelected(isSelected: Boolean) {
//        this.isSelected = isSelected
//    }
//
//    fun getObject(): Any? {
//        return `object`
//    }
//
//    fun setObject(`object`: Any?) {
//        this.`object` = `object`
//    }
//
//    override fun toString(): String {
//        return name
//    }
//}


//class KeyPairBoolData {
//    var id: Long = 0
//    var name: String = ""
//    var isSelected: Boolean = false
//    var `object`: Any? = null
//
//    constructor()
//
//    constructor(name: String, isSelected: Boolean) {
//        this.name = name
//        this.isSelected = isSelected
//    }
//
//    fun getObject(): Any? {
//        return `object`
//    }
//
//    fun setObject(`object`: Any?) {
//        this.`object` = `object`
//    }
//
//}
