import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    fun payedCommission_MasterAndMaestro_Before_75_000() {
        //arrange
        val inputAmount = 1_000_000
        val card = CardType.MasterMaestro
        val lastSum = 4_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(0, result)
    }

    @Test
    fun payedCommission_MasterAndMaestro_Over_75_000() {
        //arrange
        val inputAmount = 4_000_000
        val card = CardType.MasterMaestro
        val lastSum = 4_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(26000, result)
    }

    @Test
    fun payedCommission_MasterAndMaestro_MaxSum() {
        //arrange
        val inputAmount = 4_000_000
        val card = CardType.MasterMaestro
        val lastSum = 57_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(-1, result)
    }

    @Test
    fun payedCommission_MasterAndMaestro_OverDayLimit() {
        //arrange
        val inputAmount = 40_000_000
        val card = CardType.MasterMaestro
        val lastSum = 0

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(67695, result)
    }

    @Test
    fun payedCommission_VKPay_MaxSum() {
        //arrange
        val inputAmount = 1_000_000
        val card = CardType.VKPay
        val lastSum = 4_500_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(-1, result)
    }

    @Test
    fun payedCommission_VKPay_OverDayLimit() {
        //arrange
        val inputAmount = 2_000_000
        val card = CardType.VKPay
        val lastSum = 0

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(-1, result)
    }

    @Test
    fun payedCommission_VKPay_NoComm() {
        //arrange
        val inputAmount = 1_000_000
        val card = CardType.VKPay
        val lastSum = 1_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(0, result)
    }

    @Test
    fun payedCommission_VisaMir_MinCom() {
        //arrange
        val inputAmount = 100_000
        val card = CardType.VisaMir
        val lastSum = 1_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(3500, result)
    }

    @Test
    fun payedCommission_VisaMir_OverMinCom() {
        //arrange
        val inputAmount = 1_000_000
        val card = CardType.VisaMir
        val lastSum = 1_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(7500, result)
    }

    @Test
    fun payedCommission_VisaMir_OverDayLimit() {
        //arrange
        val inputAmount = 20_000_000
        val card = CardType.VisaMir
        val lastSum = 1_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(-1, result)
    }

    @Test
    fun payedCommission_VisaMir_MaxSum() {
        //arrange
        val inputAmount = 10_000_000
        val card = CardType.VisaMir
        val lastSum = 70_000_000

        //act
        val result = payedCommission(
            amount = inputAmount,
            cardType = card,
            previousSum = lastSum
        )

        //assert
        assertEquals(-1, result)
    }
}