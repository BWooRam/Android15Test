package com.hyundaiht.android15test

import android.util.Log
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class OpenJdkUnitTest {
    private val value = 123

    @Test
    fun testFlag1() {
        val result = String.format("%+d", value)
        println("-------------------------------------------------")
        println("testFlag1 result = $result")
        println("-------------------------------------------------")
        println("")
    }

    @Test
    fun testFlag2() {
        val result = String.format("%(d", -123)
        println("-------------------------------------------------")
        println("testFlag2 result = $result")
        println("-------------------------------------------------")
        println("")
    }

    @Test
    fun testFlag3() {
        val result = String.format("%05d", value)
        println("-------------------------------------------------")
        println("testFlag3 result = $result")
        println("-------------------------------------------------")
        println("")
    }

    @Test
    fun testFlag4() {
        val result1 = String.format("%-5d", value)
        val result2 = String.format("|%-10s|%-10d|%n", "Alice", 123)
        val result3 = String.format("|%10s|%10d|%n", "Alice", 123)
        println("-------------------------------------------------")
        val result4 = kotlin.runCatching {
            String.format("|%010s|%010d|%n", "Alice", 123)
        }.onFailure { error ->
            println("testFlag4 error = $error")
        }.getOrNull()

        println("testFlag4 result1 = $result1, result2 = $result2")
        println("testFlag4 result3 = $result3")
        println("testFlag4 result4 = $result4")
        println("-------------------------------------------------")
        println("")
    }

    @Test
    fun testFlag5() {
        val result = String.format("%,d", 123456789)
        println("-------------------------------------------------")
        println("testFlag5 result = $result")
        println("-------------------------------------------------")
        println("")
    }

    @Test
    fun testFlag6() {
        val result = String.format("%#x", value)
        println("-------------------------------------------------")
        println("testFlag6 result = $result")
        println("-------------------------------------------------")
        println("")
    }

    @Test
    fun testFlag7() {
        println("-------------------------------------------------")
        val numbers = intArrayOf(42, -7, 123, -42, 0)

        for (num in numbers) {
            // % d를 사용해서 양수와 음수를 구분하여 출력
            val result = String.format("|%d|% d|", num, num) // 기본
            println("testFlag7 result1 = $result")
        }
        println("-------------------------------------------------")
        println("")
    }
}