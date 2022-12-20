package edu.avaliacao.bank.wallet

import edu.avaliacao.bank.client.clientInterfaces.DigitalClientInterface
import edu.avaliacao.bank.wallet.transaction.Transaction
import edu.avaliacao.bank.wallet.walletInterfaces.DigitalWalletInterface
import edu.avaliacao.bank.wallet.walletInterfaces.PhysicalWalletInterface
import edu.avaliacao.bank.merge

class PremiumWallet(password: String) : DigitalWalletInterface, PhysicalWalletInterface, Wallet(password, WalletType.PREMIUM) {
    override var balance: Double = 0.0

    private val digital = DigitalWallet(password)
    private val physical = PhysicalWallet(password)

    override fun checkBalance(): Double {
        return digital.checkBalance() + physical.checkBalance()
    }

    override fun checkBankStatement(): List<Transaction> {
        return merge(digital.checkBankStatement(), physical.checkBankStatement())
    }

    override fun store(msg: String, v: Double): String {
        return digital.store(msg, v)
    }

    override fun invest(msg: String, v: Double): String {
        return digital.invest(msg, v)
    }

    override fun pix(msg: String, v: Double, target: DigitalClientInterface): String {
        return digital.pix(msg, v, target)
    }

    override fun deposit(msg: String, v: Double): String {
        return physical.deposit(msg, v)
    }

    override fun withdraw(msg: String, v: Double): String {
        return physical.withdraw(msg, v)
    }
}