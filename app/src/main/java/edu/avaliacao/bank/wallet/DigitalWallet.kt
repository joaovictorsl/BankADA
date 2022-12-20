package edu.avaliacao.bank.wallet

import edu.avaliacao.bank.client.clientInterfaces.DigitalClientInterface
import edu.avaliacao.bank.wallet.walletInterfaces.DigitalWalletInterface

class DigitalWallet(password: String) : DigitalWalletInterface, Wallet(password, WalletType.DIGITAL) {
    override fun store(msg:String, v: Double): String {
        return add(msg, v).response
    }

    override fun invest(msg:String, v: Double): String {
        return subtract(msg, v).response
    }

    override fun pix(msg: String, v: Double, target: DigitalClientInterface): String {
        val result = subtract("Pix realizado para: $target", v)
        if (result.success)
            target.store("Pix recebido de: $this", v)

        return result.response
    }
}