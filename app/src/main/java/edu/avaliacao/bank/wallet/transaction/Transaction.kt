package edu.avaliacao.bank.wallet.transaction

import java.text.SimpleDateFormat
import java.util.Calendar

data class Transaction(val msg: String, val value: Double, val time: Calendar) : Comparable<Transaction> {
    override fun compareTo(other: Transaction): Int {
        return time.compareTo(other.time)
    }

    override fun toString(): String {
        val formatted = "${time.get(Calendar.DAY_OF_MONTH)}/${time.get(Calendar.MONTH)}/${time.get(Calendar.YEAR)} | ${time.get(Calendar.HOUR)}:${time.get(Calendar.MINUTE)}:${time.get(Calendar.SECOND)}:${time.get(Calendar.MILLISECOND)}"
        return "$msg | $value | $formatted"
    }
}
