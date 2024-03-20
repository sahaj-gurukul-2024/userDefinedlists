package org.sahaj.meetingbookingss

import kotlin.math.pow

sealed interface IntegerListThing {
    fun append(value: Int): IntegerListThing

    fun prepend(value: Int): IntegerListThing

    fun getByIndex(index: Int): Int

    fun getPower(power: Int): IntegerListThing = operatorTransform(power) { it.toDouble().pow(power).toInt() }

    fun incrementBy(value: Int): IntegerListThing = operatorTransform(value) { it.plus(value) }

    fun operatorTransform(value: Int, transformFunction: (Int) -> Int): IntegerListThing

    fun sum(): Int?

    fun odds(): IntegerListThing = filterOddEven { it % 2 != 0 }

    fun evens(): IntegerListThing = filterOddEven { it % 2 == 0 }

    fun filterOddEven(filterFunction: (Int) -> Boolean): IntegerListThing

    fun max(): Int?

    fun min(): Int?
}

data class NonEmptyIntegerList(val head: Int, val tail: IntegerListThing) : IntegerListThing{
    override fun append(value: Int): IntegerListThing = NonEmptyIntegerList(head, tail.append(value))

    override fun prepend(value: Int): IntegerListThing = NonEmptyIntegerList(value, this)

    override fun getByIndex(index: Int): Int = if (index == 0) head else tail.getByIndex(index - 1)

    override fun operatorTransform(value: Int, transformFunction: (Int) -> Int): IntegerListThing =  NonEmptyIntegerList(
        transformFunction(head), tail.operatorTransform(value, transformFunction)
    )

    override fun sum(): Int = head + (tail.sum() ?: 0)

    override fun filterOddEven(filterFunction: (Int) -> Boolean): IntegerListThing =
        if (filterFunction(head))
            NonEmptyIntegerList(head, tail.filterOddEven(filterFunction))
        else
            tail.filterOddEven(filterFunction)
    override fun max(): Int? = if (head > (tail.max() ?: Int.MIN_VALUE)) head else tail.max()

    override fun min(): Int? = if (head < (tail.min() ?: Int.MAX_VALUE)) head else tail.max()

}

data object EmptyIntegerList : IntegerListThing{
    override fun append(value: Int): IntegerListThing = NonEmptyIntegerList(value, EmptyIntegerList)

    override fun prepend(value: Int): IntegerListThing = NonEmptyIntegerList(value, EmptyIntegerList)

    override fun getByIndex(index: Int): Int =  throw IndexOutOfBoundsException()

    override fun operatorTransform(value: Int, transformFunction: (Int) -> Int): IntegerListThing = EmptyIntegerList

    override fun sum(): Int? = null

    override fun filterOddEven(filterFunction: (Int) -> Boolean): IntegerListThing = EmptyIntegerList

    override fun max(): Int? = null

    override fun min(): Int? = null
}
