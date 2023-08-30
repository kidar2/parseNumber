package com

import java.util.*

object Utils {

    /**
     * На вход строка с различным форматированием числа с валютой,
     * разделителем разрядов или разделителем целой и дробной части
     * Не допускаются разделители разрядов в дробной части
     * Не допускается несколько валют
     *
     * Возвращает null, если строка недопустима для преобразования в число, иначе возвращает само число
     *
     */
    fun parseNumber(str: String): Double? {

        var str = str.trim()

        var hasSpec = false;

        listOf('₽', '€', '$', '%').forEach {
            if (str.startsWith(it)) {
                if (hasSpec)
                    return null;
                hasSpec = true
                str = str.substring(1, str.length)
            }

            if (str.endsWith(it)) {
                if (hasSpec)
                    return null;
                hasSpec = true
                str = str.substring(0, str.length - 1)
            }
        }

        str = str.trim()

        var isPlus = true;

        if (str.startsWith('-')) {
            isPlus = false
            str = str.substring(1, str.length)
        } else if (str.startsWith('+')) {
            str = str.substring(1, str.length)
        }

        if (str.isEmpty())
            return null;

        val digits = LinkedList<Char>();
        val fractionDigits = LinkedList<Char>();

        var thousandSeparator: Char? = null
        var decimalSeparator: Char? = null
        var currentDigits = fractionDigits
        var prevIsDigit = false;

        for (n in str.length - 1 downTo 0)
        {
            val it = str[n];
            when (it) {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {

                    if (thousandSeparator != null && currentDigits.size % 3 == 0)
                    {
                        if (str[n + 1] != thousandSeparator)
                         return null;
                    }

                    currentDigits.add(0, it);
                    prevIsDigit = true

                }
                ',', '.' -> {

                    if (!prevIsDigit)
                        return null

                    if (decimalSeparator == null) {
                        decimalSeparator = it;
                        currentDigits = digits;

                        if (thousandSeparator != null)
                            return null;
                    }
                    else if (decimalSeparator == it)
                        return null;
                    else {
                        if (thousandSeparator == null) {
                            if (currentDigits.isNotEmpty() && currentDigits.size % 3 == 0)
                                thousandSeparator = it;
                            else
                                return null;
                        }
                        else if (thousandSeparator != it)
                            return null;
                        else if (currentDigits.isEmpty() && currentDigits.size % 3 != 0)
                            return null;
                    }
                    prevIsDigit = false;
                }
                ' ' -> {
                    if (!prevIsDigit)
                        return null
                    if (thousandSeparator == null)
                    {
                        if (currentDigits.isNotEmpty() && currentDigits.size % 3 == 0)
                            thousandSeparator = it;
                        else
                            return null;
                    }
                    else if (thousandSeparator != it)
                        return null;
                    else if (currentDigits.isEmpty() && currentDigits.size % 3 != 0)
                        return null;
                    prevIsDigit = false;
                }

                else -> return null;
            }
        }

        if (digits.isEmpty() && fractionDigits.isEmpty())
            return null;


        val res = if (digits.isEmpty()) {
            if (currentDigits.isEmpty())
                ".${fractionDigits.joinToString("")}".toDouble()
            else
                currentDigits.joinToString("").toDouble();
        }
        else
            "${digits.joinToString("")}.${fractionDigits.joinToString("")}".toDouble()

        var r = if (isPlus) res else -res;

        println(r)

        return r;
    }
}
