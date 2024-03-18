import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.sahaj.meetingbookingss.EmptyIntegerList
import org.sahaj.meetingbookingss.EmptyStringThing
import org.sahaj.meetingbookingss.NonEmptyIntegerList
import org.sahaj.meetingbookingss.NonEmptyStringThing

class StringListThingTest {

    @Test
    fun `append string`() {
        assertEquals(
            "World",
            NonEmptyStringThing("Hello", EmptyStringThing).append("World").get(1)
        )
    }

    @Test
    fun `prepend string`() {
        assertEquals(
            "Hello",
            NonEmptyStringThing("Hello", EmptyStringThing).prepend("World").get(1)
        )
    }

    @Test
    fun `index checks`() {
        assertEquals(
            "Hello",
            NonEmptyStringThing("Hello", EmptyStringThing).get(0)
        )
        assertThrows<IndexOutOfBoundsException> { NonEmptyStringThing("Hello", EmptyStringThing).get(1) }
    }

    @Test
    fun `get upper`() {
        assertEquals(
            "HELLO",
            NonEmptyStringThing("Hello", EmptyStringThing).upper().get(0)
        )
    }

    @Test
    fun `get lower`() {
        assertEquals(
            "hello",
            NonEmptyStringThing("Hello", EmptyStringThing).lower().get(0)
        )
    }

    @Test
    fun `3 char vals only`() {
        assertEquals(
            NonEmptyStringThing("Try", NonEmptyStringThing("Boo", EmptyStringThing)),
            NonEmptyStringThing(
                "Try",
                NonEmptyStringThing("Boo", NonEmptyStringThing("Hello", EmptyStringThing))
            ).threes()
        )
    }

    @Test
    fun `list of sizes`() {
        assertEquals(
            NonEmptyIntegerList(5, NonEmptyIntegerList(5, EmptyIntegerList)),
            NonEmptyStringThing(
                "Hello",
                NonEmptyStringThing("World", EmptyStringThing)
            ).intList()
        )
    }

    @Test
    fun `string lengths sum`() {
        assertEquals(
            10,
            NonEmptyStringThing("Hello", NonEmptyStringThing("World", EmptyStringThing)).intList().sum()
        )
    }

    @Test
    fun `single string with spaces`() {
        assertEquals(
            "Hello World",
            NonEmptyStringThing(
                "Hello",
                NonEmptyStringThing("World", EmptyStringThing)
            ).concatenateString(" ")
        )
    }

    @Test
    fun `first letters string`() {
        assertEquals(
            "HW",
            NonEmptyStringThing(
                "Hello",
                NonEmptyStringThing("World", EmptyStringThing)
            ).concatenateLetters("")
        )
    }
}