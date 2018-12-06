package com.dragger2.reactnativetest1022

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.dragger2.reactnativetest1022", appContext.packageName)
    }

    @Test
    fun test(){
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

    fun test2(){

    }
}
