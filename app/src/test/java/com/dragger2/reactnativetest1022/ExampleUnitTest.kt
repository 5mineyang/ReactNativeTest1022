package com.dragger2.reactnativetest1022

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun changePosition(){
        var a = 10
        var b = 20

        val temp = a
        a = b
        b = a

//        a = a^b
//        b = a^b
//        a = a^b
        println("a=${a},b=${b}")
    }
}
