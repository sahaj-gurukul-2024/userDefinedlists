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

    fun transform(transformFunction: (String) -> String): StringThing = when (this) {
        EmptyStringThing -> EmptyStringThing
        is NonEmptyStringThing -> NonEmptyStringThing(transformFunction(head), tail.transform(transformFunction))
    }

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

    fun combine(): String = when (this) {
        EmptyStringThing -> ""
        is NonEmptyStringThing -> {
            val tailCombine = tail.combine()
            if (tail is NonEmptyStringThing) "$head $tailCombine" else "$head$tailCombine"
        }
    }

    fun combineLetters(): String = when (this) {
        EmptyStringThing -> ""
        is NonEmptyStringThing -> {
            val tailFirst = tail.combineLetters()
            val headFirst = head.first()
            if (tail is NonEmptyStringThing) "$headFirst$tailFirst" else headFirst.toString()
        }
    }
}

data class NonEmptyStringThing(val head: String, val tail: StringThing) : StringThing

data object EmptyStringThing : StringThing