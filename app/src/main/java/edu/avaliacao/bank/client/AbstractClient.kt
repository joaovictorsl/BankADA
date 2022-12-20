package edu.avaliacao.bank.client

import edu.avaliacao.bank.asString
import edu.avaliacao.bank.wallet.DigitalWallet
import edu.avaliacao.bank.wallet.PhysicalWallet
import edu.avaliacao.bank.wallet.PremiumWallet
import edu.avaliacao.bank.wallet.Wallet

abstract class AbstractClient(
    val name: String,
    val lastName: String,
    protected val cpf: String,
    private val password: String,
    val plan: Plan
) {
    protected val wallet: Wallet = when(plan) {
        Plan.DIGITAL -> DigitalWallet(password)
        Plan.PHYSICAL -> PhysicalWallet(password)
        Plan.PREMIUM -> PremiumWallet(password)
    }

    fun checkBalance(): Double {
        return wallet.checkBalance()
    }

    fun checkBankStatement(): String {
        return wallet.checkBankStatement().asString()
    }

    fun isCorrectPassword(p: String): Boolean {
        return password == p
    }

    override fun equals(other: Any?): Boolean {
        if (other == null)
            return false
        if (other !is AbstractClient)
            return false

        return other.cpf == cpf
    }

}
