package ru.netology

import org.junit.Test
import org.junit.Assert.*

class MainKtTest {

    @Test
    fun should_calculate_commission_for_Maestro() {
        // arrange
        val transferAmount = 3_000_00
        val previousTransferAmount = 11_700_00
        val card = Cards.Maestro
        val expectedCommission = 0

        // act
        val result = commission(transferAmount, previousTransferAmount, card)

        // assert
        assertEquals(expectedCommission, result)
    }

    @Test
    fun should_calculate_not_null_commission_for_Maestro() {
        // arrange
        val transferAmount = 3_000_00
        val previousTransferAmount = 76_900_00
        val card = Cards.Maestro
        val expectedCommission = 38_00

        // act
        val result = commission(transferAmount, previousTransferAmount, card)

        // assert
        assertEquals(expectedCommission, result)
    }

    @Test(expected = Exception::class)
    fun should_calculate_commission_limit_exceeded_for_Maestro() {
        // arrange
        val transferAmount = 101_000_00
        val previousTransferAmount = 500_000_00
        val card = Cards.Maestro

        // act
        commission(transferAmount, previousTransferAmount, card)
    }

    @Test
    fun should_calculate_commission_for_VKPay() {
        // arrange
        val transferAmount = 11_000_00
        val previousTransferAmount = 4_000_00
        val card = Cards.VKPay
        val expectedCommission = 0

        // act
        val result = commission(transferAmount, previousTransferAmount, card)

        // assert
        assertEquals(expectedCommission, result)
    }

    @Test(expected = Exception::class)
    fun should_calculate_limit_exceeded_for_VKPay() {
        // arrange
        val transferAmount = 2_500_00
        val previousTransferAmount = 38_000_00
        val card = Cards.VKPay

        // act
        commission(transferAmount, previousTransferAmount, card)

    }

    @Test
    fun should_calculate_commission_for_Mir() {
        // arrange
        val transferAmount = 145_000_35
        val previousTransferAmount = 127_892_00
        val card = Cards.Mir
        val expectedCommission = 108_750

        // act
        val result = commission(transferAmount, previousTransferAmount, card)

        // assert
        assertEquals(expectedCommission, result)
    }

    @Test
    fun should_calculate_min_commission_for_Mir() {
        // arrange
        val transferAmount = 300_00
        val previousTransferAmount = 100_000_00
        val card = Cards.Mir
        val expectedCommission = 35_00

        // act
        val result = commission(transferAmount, previousTransferAmount, card)

        // assert
        assertEquals(expectedCommission, result)
    }

    @Test(expected = Exception::class)
    fun should_calculate_commission_limit_exceeded_for_Mir() {
        // arrange
        val transferAmount = 101_000_00
        val previousTransferAmount = 500_000_00
        val card = Cards.Mir

        // act
        commission(transferAmount, previousTransferAmount, card)
    }
}
