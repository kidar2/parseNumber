package com.example.demo

import com.Utils
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.NumberUtils.parseNumber


class DemoApplicationTests {

    @Test
    fun parseNumberTest() {

        //допустимые значения
        Assertions.assertNotNull(Utils.parseNumber("-666 ₽"))
        Assertions.assertNotNull(Utils.parseNumber("+666 ₽"))
        Assertions.assertNotNull(Utils.parseNumber("1,123,666.5 ₽"))
        Assertions.assertNotNull(Utils.parseNumber("123,123,666.3 ₽"))
        Assertions.assertNotNull(Utils.parseNumber("$+332,122"))
        Assertions.assertNotNull(Utils.parseNumber("123 332,122"))
        Assertions.assertNotNull(Utils.parseNumber("123 332,122666666666666666322"))
        Assertions.assertNotNull(Utils.parseNumber("1 123 332,12266322"))
        Assertions.assertNotNull(Utils.parseNumber("€-332,122"))
        Assertions.assertNotNull(Utils.parseNumber("33.2 ₽"))
        Assertions.assertNotNull(Utils.parseNumber("332122,00%"))
        Assertions.assertNotNull(Utils.parseNumber("332,122.00"))
        Assertions.assertNotNull(Utils.parseNumber(".011"))
        Assertions.assertNotNull(Utils.parseNumber("$.011"))
        Assertions.assertNotNull(Utils.parseNumber("-0"))
        Assertions.assertNotNull(Utils.parseNumber("1 111 200.1"))

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
    }

}
