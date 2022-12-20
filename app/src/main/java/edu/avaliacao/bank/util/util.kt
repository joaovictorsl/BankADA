package edu.avaliacao.bank

import edu.avaliacao.bank.wallet.transaction.Transaction

fun <T : Comparable<T>> merge(left: List<T>, right: List<T>): List<T> {
    var indexLeft = 0
    var indexRight = 0
    val newList : MutableList<T> = mutableListOf()

    while (indexLeft < left.count() && indexRight < right.count()) {
        if (left[indexLeft] <= right[indexRight]) {
            newList.add(left[indexLeft])
            indexLeft++
        } else {
            newList.add(right[indexRight])
            indexRight++
        }
    }

    while (indexLeft < left.size) {
        newList.add(left[indexLeft])
        indexLeft++
    }

    while (indexRight < right.size) {
        newList.add(right[indexRight])
        indexRight++
    }

    return newList
}

fun String.isNumberOnly(): Boolean {
    val numbers = "1234567890"

    for(char in this) {
        if (char !in numbers)
            return false
    }

    return true
}

fun List<Transaction>.asString(): String {
    var result = if (this.isEmpty()) "Não há transações." else ""

    this.forEachIndexed { idx, t ->
        result += "${idx + 1} - $t${if (idx == this.size - 1) "" else "\n"}"
    }

    return result
}
