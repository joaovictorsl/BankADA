package edu.avaliacao.bank.wallet

import edu.avaliacao.bank.wallet.transaction.Transaction
import edu.avaliacao.bank.wallet.transaction.TransactionResult
import edu.avaliacao.bank.wallet.walletException.InsufficientBalanceException
import java.util.Calendar

abstract class Wallet(
    protected val password: String,
    protected val type: WalletType,
) {
    protected open var balance = 0.0
    protected open val bankStatement = mutableListOf<Transaction>()

    open fun checkBalance(): Double {
        return balance
    }

    open fun checkBankStatement(): List<Transaction> {
        return bankStatement.toMutableList()
    }

    protected fun subtract(msg: String, v: Double): TransactionResult {
        val result = try {
            ensureBalance(v)
            balance -= v
            bankStatement.add(Transaction(msg, -v, Calendar.getInstance()))
            TransactionResult(true, "Operação realizada com sucesso.")
        } catch(e: InsufficientBalanceException) {
            TransactionResult(false, e.message as String)
        }

        return result
    }

    protected fun add(msg: String, v: Double): TransactionResult {
        balance += v
        bankStatement.add(Transaction(msg, v, Calendar.getInstance()))
        return TransactionResult(true, "Operação realizada com sucesso.")
    }

    fun payTicket(msg:String, v: Double): String {
        val result = try {
            ensureBalance(v)
            subtract(msg, v)
            "Operação realizada com sucesso."
        } catch(e: InsufficientBalanceException) {
            e.message as String
        }

        return result
    }

    private fun ensureBalance(v: Double) {
        if (v > balance)
            throw InsufficientBalanceException()
    }
}
