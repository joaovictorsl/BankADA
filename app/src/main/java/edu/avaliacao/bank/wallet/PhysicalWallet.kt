package edu.avaliacao.bank.wallet

import edu.avaliacao.bank.wallet.walletInterfaces.PhysicalWalletInterface

class PhysicalWallet(password: String) : PhysicalWalletInterface, Wallet(password, WalletType.PHYSICAL) {
    override fun deposit(msg:String, v: Double): String {
        return add(msg, v).response
    }

    override fun withdraw(msg:String, v: Double): String {
        return subtract(msg, v).response
    }
}