package org.sahaj.meetingbookingss

sealed interface StringThing {

    fun append(string: String): StringThing = when (this) {
        EmptyStringThing -> NonEmptyStringThing(string, EmptyStringThing)
        is NonEmptyStringThing -> NonEmptyStringThing(head, tail.append(string))
    }

    fun prepend(string: String): StringThing = when (this) {
        EmptyStringThing -> NonEmptyStringThing(string, EmptyStringThing)
        is NonEmptyStringThing -> NonEmptyStringThing(string, this)
    }

    fun get(index: Int): String = when (this) {
        EmptyStringThing -> throw IndexOutOfBoundsException()
        is NonEmptyStringThing -> if (index == 0) head else tail.get(index - 1)
    }

    fun transform(transformFunction: (String) -> String): StringThing //= when (this) {
//        EmptyStringThing -> EmptyStringThing
//        is NonEmptyStringThing -> NonEmptyStringThing(transformFunction(head), tail.transform(transformFunction))
//    }

    fun upper(): StringThing = transform { it.uppercase() }

    fun lower(): StringThing = transform { it.lowercase() }

    fun threes(): StringThing = when (this) {
        EmptyStringThing -> EmptyStringThing
        is NonEmptyStringThing -> if (head.length == 3) NonEmptyStringThing(head, tail.threes()) else tail.threes()
    }

    fun intList(): IntegerListThing = when (this) {
        EmptyStringThing -> EmptyIntegerList
        is NonEmptyStringThing -> NonEmptyIntegerList(head.length, tail.intList())
    }

    fun concater(sep: String): String {
        val getHead: (String) -> String = {it}
        return fn1(getHead, sep)
    }

    fun fn1(getHead: (String) -> String, sep: String) = when (this) {
        EmptyStringThing -> ""
        is NonEmptyStringThing -> "${getHead(head)}$sep${tail.concater(sep)}".trim()
    }

    fun concaterLetters(sep: String): String{
        val getHead: (String) -> Char = { it.first() }
        return fn2(getHead, sep)
    }

    fun fn2(getHead: (String) -> Char, sep: String) = when (this) {
        EmptyStringThing -> ""
        is NonEmptyStringThing -> "${getHead(head)}$sep${tail.concaterLetters(sep)}".trim()
    }
}

data class NonEmptyStringThing(val head: String, val tail: StringThing) : StringThing{
    override fun transform(transformFunction: (String) -> String): StringThing =
        NonEmptyStringThing(transformFunction(head), tail.transform(transformFunction))
}

data object EmptyStringThing : StringThing{
    override fun transform(transformFunction: (String) -> String): StringThing = this
}