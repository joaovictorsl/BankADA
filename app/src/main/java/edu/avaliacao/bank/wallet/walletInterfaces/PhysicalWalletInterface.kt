package edu.avaliacao.bank.wallet.walletInterfaces

interface PhysicalWalletInterface {
    fun deposit(msg:String, v: Double): String
    fun withdraw(msg:String, v: Double): String
    fun payTicket(msg:String, v: Double): String
}