package org.sahaj.meetingbookingss

import kotlin.math.pow

sealed interface IntegerListThing {
    fun append(value: Int): IntegerListThing {
        return when (this) {
            EmptyIntegerList -> NonEmptyIntegerList(value, EmptyIntegerList)
            is NonEmptyIntegerList -> NonEmptyIntegerList(head, tail.append(value))
        }
    }

    fun prepend(value: Int): IntegerListThing {
        return when (this) {
            EmptyIntegerList -> NonEmptyIntegerList(value, EmptyIntegerList)
            is NonEmptyIntegerList -> NonEmptyIntegerList(value, this)
        }
    }

    fun getByIndex(index: Int): Int? {
        return when (this) {
            EmptyIntegerList -> throw IndexOutOfBoundsException()
            is NonEmptyIntegerList -> if (index == 0) head else tail.getByIndex(index - 1)
        }
    }

    fun getPower(power: Int): IntegerListThing = operatorTransform(power) { it.toDouble().pow(power).toInt() }

    fun incrementBy(value: Int): IntegerListThing = operatorTransform(value) { it.plus(value) }

    fun operatorTransform(value: Int, transformFunction: (Int) -> Int): IntegerListThing = when (this) {
        is NonEmptyIntegerList -> NonEmptyIntegerList(
            transformFunction(head), tail.operatorTransform(value, transformFunction)
        )

        EmptyIntegerList -> EmptyIntegerList
    }

    fun sum(): Int? {
        return when (this) {
            EmptyIntegerList -> null
            is NonEmptyIntegerList -> head + (tail.sum() ?: 0)
        }
    }

    fun odds(): IntegerListThing = filterOddEven { it % 2 != 0 }

    fun evens(): IntegerListThing = filterOddEven { it % 2 == 0 }

    fun filterOddEven(filterFunction: (Int) -> Boolean): IntegerListThing = when (this) {
        EmptyIntegerList -> EmptyIntegerList
        is NonEmptyIntegerList -> if (filterFunction(head)) NonEmptyIntegerList(
            head, tail.filterOddEven(filterFunction)
        ) else tail.filterOddEven(
            filterFunction
        )
    }

    fun max(): Int? {
        //val find: (ListThing) -> (Int) = {}
        return when (this) {
            EmptyIntegerList -> null
            is NonEmptyIntegerList -> {
                if (head > (tail.max() ?: Int.MIN_VALUE)) head else tail.max()
            }
        }

    }

    fun min(): Int? = when (this) {
        EmptyIntegerList -> null
        is NonEmptyIntegerList -> if (head < (tail.max() ?: Int.MAX_VALUE)) head else tail.min()
    }
}

data class NonEmptyIntegerList(val head: Int, val tail: IntegerListThing) : IntegerListThing

data object EmptyIntegerList : IntegerListThing