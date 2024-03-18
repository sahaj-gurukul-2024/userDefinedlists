package org.sahaj.meetingbookingss

sealed interface StringThing {

    fun append(string: String): StringThing

    fun prepend(string: String): StringThing

    fun get(index: Int): String

    fun transform(transformFunction: (String) -> String): StringThing

    fun upper(): StringThing = transform { it.uppercase() }

    fun lower(): StringThing = transform { it.lowercase() }

    fun threes(): StringThing

    fun intList(): IntegerListThing

    fun concatenateString(sep: String): String = processStrings(sep) { it }

    fun concatenateLetters(sep: String): String = processStrings(sep) { it.take(1) }

    fun processStrings(sep: String,processFunction: (String) -> String): String
}

data class NonEmptyStringThing(val head: String, val tail: StringThing) : StringThing{
    override fun append(string: String): StringThing =
        NonEmptyStringThing(head, tail.append(string))

    override fun prepend(string: String): StringThing =
        NonEmptyStringThing(string, this)

    override fun get(index: Int): String =
        if (index == 0) head else tail.get(index - 1)

    override fun transform(transformFunction: (String) -> String): StringThing =
        NonEmptyStringThing(transformFunction(head), tail.transform(transformFunction))

    override fun intList(): IntegerListThing =
        NonEmptyIntegerList(head.length, tail.intList())

    override fun threes(): StringThing =
        if (head.length == 3) NonEmptyStringThing(head, tail.threes()) else tail.threes()

    override fun processStrings(sep: String,processFunction: (String) -> String): String =
        "${processFunction(head)}$sep${tail.processStrings(sep,processFunction)}".trim()
}

data object EmptyStringThing : StringThing{
    override fun append(string: String): StringThing =
        NonEmptyStringThing(string, EmptyStringThing)

    override fun prepend(string: String): StringThing =
        NonEmptyStringThing(string, EmptyStringThing)

    override fun get(index: Int) =
        throw IndexOutOfBoundsException()

    override fun transform(transformFunction: (String) -> String): StringThing =
        EmptyStringThing

    override fun intList(): IntegerListThing =
        EmptyIntegerList

    override fun threes(): StringThing =
        EmptyStringThing

    override fun processStrings(sep: String,processFunction: (String) -> String): String = ""
}