package br.com.anascimento.spotify

import org.junit.Assert
import org.junit.Test

/**
 * Created by andre.nascimento on 19/09/2017.
 */
class KotlinAnnulableTest {

    fun returnLenghtString(param: String?) : Int? {
        return param?.length
    }

    fun returnList(param: String?) : List<String>? {
        return null
    }

    @Test
    fun testCheck_null() {
        val lenght = returnLenghtString(null)
        Assert.assertNull(lenght)
    }

    @Test
    fun testCheck_notNull() {
        val lenght = returnLenghtString("android")
        Assert.assertNotNull(lenght)
    }
}