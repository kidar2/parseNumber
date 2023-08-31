package com.example.demo

import com.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class DemoApplicationTests {

    @Test
    fun parseNumberTest() {

        //допустимые значения

        Assertions.assertEquals(-666.0, Utils.parseNumber("-666 ₽")!!, 0.001)
        Assertions.assertEquals(1_123_666.5, Utils.parseNumber("1,123,666.5 ₽")!!, 0.001)
        Assertions.assertEquals(123_123_666.3, Utils.parseNumber("123,123,666.3 ₽")!!, 0.001)
        Assertions.assertEquals(332.122, Utils.parseNumber("$+332,122")!!, 0.001)
        Assertions.assertEquals(123_332.122, Utils.parseNumber("123 332,122")!!, 0.001)
        Assertions.assertEquals(123_332.12266, Utils.parseNumber("123 332,12266")!!, 0.000001)
        Assertions.assertEquals(1_123_332.12266322, Utils.parseNumber("1 123 332,12266322")!!, 0.00000001)
        Assertions.assertEquals(-332.122, Utils.parseNumber("€-332,122")!!, 0.001)
        Assertions.assertEquals(33.2, Utils.parseNumber("33.2 ₽")!!, 0.001)
        Assertions.assertEquals(332122.0, Utils.parseNumber("332122,00%")!!, 0.001)
        Assertions.assertEquals(332122.0, Utils.parseNumber("332,122.00")!!, 0.001)
        Assertions.assertEquals(0.011, Utils.parseNumber(".011")!!, 0.001)
        Assertions.assertEquals(.011, Utils.parseNumber("$.011")!!, 0.001)
        Assertions.assertEquals(0.0, Utils.parseNumber("-0")!!, 0.001)
        Assertions.assertEquals(1111200.1, Utils.parseNumber("1 111 200.1")!!, 0.001)
        Assertions.assertEquals(1101.111, Utils.parseNumber("1 101,111")!!, 0.001)

        //недопустимые значения
        Assertions.assertNull(Utils.parseNumber("1,121 332.122 ₽"))
        Assertions.assertNull(Utils.parseNumber("--20"))
        Assertions.assertNull(Utils.parseNumber("++10"))
        Assertions.assertNull(Utils.parseNumber("1,332 122 ₽"))
        Assertions.assertNull(Utils.parseNumber("-332€122"))
        Assertions.assertNull(Utils.parseNumber("$332,122 ₽"))
        Assertions.assertNull(Utils.parseNumber("$1,1,1,1.2"))
        Assertions.assertNull(Utils.parseNumber("$1,1,1,1 2"))
        Assertions.assertNull(Utils.parseNumber("$1,1 112"))
        Assertions.assertNull(Utils.parseNumber("$1,1,112"))
        Assertions.assertNull(Utils.parseNumber("$$1.011"))
        Assertions.assertNull(Utils.parseNumber("1.011₽₽"))
        Assertions.assertNull(Utils.parseNumber("1."))
        Assertions.assertNull(Utils.parseNumber("."))
        Assertions.assertNull(Utils.parseNumber("1 . 1"))
        Assertions.assertNull(Utils.parseNumber("1 . 111"))
        Assertions.assertNull(Utils.parseNumber("01. 1"))
        Assertions.assertNull(Utils.parseNumber("10 21,1"))
        Assertions.assertNull(Utils.parseNumber("111.1 200"))
        Assertions.assertNull(Utils.parseNumber("1111 200"))
        Assertions.assertNull(Utils.parseNumber("1.1 111 200"))
        Assertions.assertNull(Utils.parseNumber("100 1,1111200"))
    }

}
