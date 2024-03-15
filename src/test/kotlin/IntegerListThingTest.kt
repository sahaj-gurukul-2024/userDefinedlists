import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sahaj.meetingbookingss.EmptyList
import org.sahaj.meetingbookingss.NonEmptyList
import kotlin.test.assertEquals

class ListThingTest {

    @Test
    fun `append to list`() {
        assertEquals(
            NonEmptyList(1, NonEmptyList(2, EmptyList)),
            NonEmptyList(1, EmptyList).append(2)
        )
    }

    @Test
    fun `prepend to list`() {
        assertEquals(
            NonEmptyList(2, NonEmptyList(1, EmptyList)),
            NonEmptyList(1, EmptyList).prepend(2)
        )
    }

    @Test
    fun `Index checks`() {
        assertEquals(1, NonEmptyList(1, EmptyList).getByIndex(0))
        assertThrows<IndexOutOfBoundsException> { NonEmptyList(1, EmptyList).getByIndex(2) }
    }

    @Test
    fun `get square list`() {
        assertEquals(
            NonEmptyList(4, NonEmptyList(9, EmptyList)),
            NonEmptyList(2, NonEmptyList(3, EmptyList)).getPower(2)
        )
    }

    @Test
    fun `get cube list`() {
        assertEquals(
            NonEmptyList(8, NonEmptyList(27, EmptyList)),
            NonEmptyList(2, NonEmptyList(3, EmptyList)).getPower(3)
        )
    }

    @Test
    fun `increment list`() {
        assertEquals(
            NonEmptyList(1, NonEmptyList(2, EmptyList)),
            NonEmptyList(0, NonEmptyList(1, EmptyList)).incrementBy(1)
        )
    }

    @Test
    fun `odd list`() {
        assertEquals(
            NonEmptyList(1, NonEmptyList(3, EmptyList)),
            NonEmptyList(1, NonEmptyList(3, EmptyList)).odds()
        )
    }

    @Test
    fun `even list`() {
        assertEquals(
            NonEmptyList(2, NonEmptyList(4, EmptyList)),
            NonEmptyList(2, NonEmptyList(4, NonEmptyList(5, EmptyList))).evens()
        )
    }
    @Test
    fun `sum of list`() {
        assertEquals(
            6,
            NonEmptyList(2, NonEmptyList(4, EmptyList)).sum()
        )
    }
    @Test
    fun `max of list`() {
        assertEquals(
            4,
            NonEmptyList(2, NonEmptyList(4, EmptyList)).max()
        )
    }
    @Test
    fun `min of list`() {
        assertEquals(
            2,
            NonEmptyList(4, NonEmptyList(2, EmptyList)).min()
        )
    }
}