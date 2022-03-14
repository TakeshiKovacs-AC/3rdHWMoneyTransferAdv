fun main() {
    println(payedCommission(3_000_000, CardType.MasterMaestro, 35_000_000))
}

enum class CardType {
    MasterMaestro, VisaMir, VKPay
}

const val LIMIT_MAS_MAE: Int = 7_500_000
const val MAS_MAE_COM_OVER: Double = 0.006
const val VISA_MIR_COM: Double = 0.0075
const val DAY_LIMIT_CARD: Int = 15_000_000
const val MONTH_LIMIT_CARD: Int = 60_000_000
const val ONE_TIME_VK_LIMIT: Int = 1_500_000
const val MONTH_LIMIT_VK: Int = 4_000_000
const val MIN_COM_VISA_MIR: Int = 3_500

fun payedCommission(
    amount: Int,
    cardType: CardType = CardType.VKPay,
    previousSum: Int = 0
): String {

    val totalSum = amount + previousSum
    val realComVisaMir = (amount * VISA_MIR_COM).toInt()

    return when (cardType) {
        CardType.VKPay -> if (amount < ONE_TIME_VK_LIMIT && totalSum < MONTH_LIMIT_VK)
                          printMessage(amount, 0)
                          else cancelTransfer()
        CardType.MasterMaestro -> if (totalSum < LIMIT_MAS_MAE) printMessage(amount, 0)
                                  else if (amount < DAY_LIMIT_CARD && totalSum < MONTH_LIMIT_CARD)
                                  printMessage(amount, (((amount * MAS_MAE_COM_OVER) + 2_000)).toInt())
                                  else cancelTransfer()
        CardType.VisaMir -> if (amount < DAY_LIMIT_CARD && totalSum < MONTH_LIMIT_CARD)
                            printMessage(amount, (if (realComVisaMir > MIN_COM_VISA_MIR) realComVisaMir else MIN_COM_VISA_MIR))
                            else cancelTransfer()
    }
}

fun printMessage(amount: Int, commission: Int): String {
    return "С вашего счёта будет списано ${amount + commission} коп., " +
            "из которых $amount коп. - сумма перевода, а $commission коп. - сумма комиссии!"
}

fun cancelTransfer(): String {
    return "Перевод невозможен, превышен лимит!"
}