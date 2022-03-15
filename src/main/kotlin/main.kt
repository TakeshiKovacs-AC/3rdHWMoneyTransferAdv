import kotlin.math.roundToInt

fun main() {
    val amount = 3_000_000
    val commission: Int = payedCommission(amount, CardType.VisaMir, 50_000_000)
    println(when (commission) {
             -1 -> cancelTransfer()
             0 -> printMessage(amount, 0)
             else -> printMessage(amount, commission)
    })
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
): Int {
    val totalSum = amount + previousSum
    val realComVisaMir = (amount * VISA_MIR_COM).roundToInt()

    return when (cardType) {
        CardType.VKPay -> if (amount < ONE_TIME_VK_LIMIT && totalSum < MONTH_LIMIT_VK) 0
                          else -1
        CardType.MasterMaestro -> if (totalSum < LIMIT_MAS_MAE) 0
                                  else if (amount < DAY_LIMIT_CARD && totalSum < MONTH_LIMIT_CARD) (((amount * MAS_MAE_COM_OVER) + 2_000).roundToInt())
                                  else -1
        CardType.VisaMir -> if (amount < DAY_LIMIT_CARD && totalSum < MONTH_LIMIT_CARD) if (realComVisaMir > MIN_COM_VISA_MIR) realComVisaMir else MIN_COM_VISA_MIR
                            else -1
    }
}

fun printMessage(amount: Int, commission: Int): String {
    return "С вашего счёта будет списано ${amount + commission} коп., " +
            "из которых $amount коп. - сумма перевода, а $commission коп. - сумма комиссии!"
}

fun cancelTransfer(): String {
    return "Перевод невозможен, превышен лимит!"
}