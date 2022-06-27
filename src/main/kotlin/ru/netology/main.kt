package ru.netology

import kotlin.math.roundToInt

enum class Cards { Mastercard, Maestro, Visa, Mir, VKPay }

const val VK_PAY_LIMIT_AT_A_TIME = 15_000_00
const val VK_PAY_LIMIT_AT_MONTH = 40_000_00
const val ONE_CARD_LIMIT_AT_A_TIME = 150_000_00
const val ONE_CARD_LIMIT_AT_MONTH = 600_000_00
const val VISA_MIR_COMMISSION = 0.0075
const val VISA_MIR_MIN_COMMISSION = 35_00
const val MAESTRO_MASTERCARD_LIMIT_AT_MONTH = 75_000_00
const val MAESTRO_MASTERCARD_COMMISSION = 0.0060
const val MAESTRO_MASTERCARD_EXTRA_COMMISSION = 20_00


fun main() {
    println(commission(10_000_00, 5_000_00))
    println(commission(40_000_00, 20_000_00, Cards.Visa))
    println(commission(50_000_00, 200_000_00, Cards.Maestro))
}

fun commission(transferAmount: Int, previousTransfersAmount: Int = 0, card: Cards = Cards.VKPay): Int = when (card) {
    Cards.VKPay -> {
        if (previousTransfersAmount + transferAmount <= VK_PAY_LIMIT_AT_MONTH && transferAmount <= VK_PAY_LIMIT_AT_A_TIME) 0
        else error("Превышен лимит переводов в месяц")
    }
    Cards.Visa, Cards.Mir -> {
        if (previousTransfersAmount + transferAmount <= ONE_CARD_LIMIT_AT_MONTH && transferAmount <= ONE_CARD_LIMIT_AT_A_TIME) {
            if (transferAmount * VISA_MIR_COMMISSION >= VISA_MIR_MIN_COMMISSION) (transferAmount * VISA_MIR_COMMISSION).roundToInt()
            else VISA_MIR_MIN_COMMISSION
        } else {
            error("Превышен лимит переводов в месяц")
        }
    }
    Cards.Mastercard, Cards.Maestro -> {
        if (previousTransfersAmount + transferAmount <= MAESTRO_MASTERCARD_LIMIT_AT_MONTH) 0
        else {
            if (previousTransfersAmount + transferAmount <= ONE_CARD_LIMIT_AT_MONTH
                && transferAmount <= ONE_CARD_LIMIT_AT_A_TIME
            ) (transferAmount * MAESTRO_MASTERCARD_COMMISSION + MAESTRO_MASTERCARD_EXTRA_COMMISSION).roundToInt()
            else error("Превышен лимит переводов в месяц")
        }
    }
    else -> {
        error("Указанный тип карты недоступен для использования")
    }
}
