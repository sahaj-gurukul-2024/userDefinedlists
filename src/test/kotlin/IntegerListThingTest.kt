import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sahaj.meetingbookingss.EmptyIntegerList
import org.sahaj.meetingbookingss.NonEmptyIntegerList
import kotlin.test.assertEquals

class IntegerListThingTest {

    @Test
    fun `append to list`() {
        assertEquals(
            NonEmptyIntegerList(1, NonEmptyIntegerList(2, EmptyIntegerList)),
            NonEmptyIntegerList(1, EmptyIntegerList).append(2)
        )
    }

    @Test
    fun `prepend to list`() {
        assertEquals(
            NonEmptyIntegerList(2, NonEmptyIntegerList(1, EmptyIntegerList)),
            NonEmptyIntegerList(1, EmptyIntegerList).prepend(2)
        )
    }

    @Test
    fun `Index checks`() {
        assertEquals(1, NonEmptyIntegerList(1, EmptyIntegerList).getByIndex(0))
        assertThrows<IndexOutOfBoundsException> { NonEmptyIntegerList(1, EmptyIntegerList).getByIndex(2) }
    }

    @Test
    fun `get square list`() {
        assertEquals(
            NonEmptyIntegerList(4, NonEmptyIntegerList(9, EmptyIntegerList)),
            NonEmptyIntegerList(2, NonEmptyIntegerList(3, EmptyIntegerList)).getPower(2)
        )
    }

    @Test
    fun `get cube list`() {
        assertEquals(
            NonEmptyIntegerList(8, NonEmptyIntegerList(27, EmptyIntegerList)),
            NonEmptyIntegerList(2, NonEmptyIntegerList(3, EmptyIntegerList)).getPower(3)
        )
    }

    @Test
    fun `increment list`() {
        assertEquals(
            NonEmptyIntegerList(1, NonEmptyIntegerList(2, EmptyIntegerList)),
            NonEmptyIntegerList(0, NonEmptyIntegerList(1, EmptyIntegerList)).incrementBy(1)
        )
    }

    @Test
    fun `odd list`() {
        assertEquals(
            NonEmptyIntegerList(1, NonEmptyIntegerList(3, EmptyIntegerList)),
            NonEmptyIntegerList(1, NonEmptyIntegerList(3, EmptyIntegerList)).odds()
        )
    }

    @Test
    fun `even list`() {
        assertEquals(
            NonEmptyIntegerList(2, NonEmptyIntegerList(4, EmptyIntegerList)),
            NonEmptyIntegerList(2, NonEmptyIntegerList(4, NonEmptyIntegerList(5, EmptyIntegerList))).evens()
        )
    }

    @Test
    fun `sum of list`() {
        assertEquals(
            6,
            NonEmptyIntegerList(2, NonEmptyIntegerList(4, EmptyIntegerList)).sum()
        )
    }

    @Test
    fun `max of list`() {
        assertEquals(
            4,
            NonEmptyIntegerList(2, NonEmptyIntegerList(4, EmptyIntegerList)).max()
        )
    }

    @Test
    fun `min of list`() {
        assertEquals(
            2,
            NonEmptyIntegerList(4, NonEmptyIntegerList(2, EmptyIntegerList)).min()
        )
    }
}