package stringcalculator.ca

import org.junit.Assert.assertEquals
import org.junit.Test


class StringCalculatorUnitTest {

    @Test
    fun `addition with one separator is correct`() {
        val result = Utils.add("//@1@2@3")
        assertEquals(6, result)
    }

    @Test
    fun `addition with two separators is correct`() {
        val result = Utils.add("//@1@2@3//$1$2$3")
        assertEquals(12, result)
    }

    @Test
    fun `empty string should return zero`() {
        val result = Utils.add("")
        assertEquals(0, result)
    }

    @Test
    fun `negative values should return minus one that represents exception`() {
        val result = Utils.add("//@1@-1@-5@-6@10@50")
        assertEquals(-1, result)
    }

    @Test
    fun `negative values should populate the exception list`() {
        val result = Utils.add("//@1@-1@-5@-6@10@50")
        val exceptions = Utils.exceptions
        assertEquals("[-1, -5, -6]", exceptions.toString())
    }

    @Test
    fun `values greater than one thousand should be ignored`() {
        val result = Utils.add("//@1@@50@1001@6")
        assertEquals(57, result)
    }

    @Test
    fun `break lines should be ignored`() {
        val result = Utils.add("//@1@@50@1001\n@6")
        assertEquals(57, result)
    }
}
