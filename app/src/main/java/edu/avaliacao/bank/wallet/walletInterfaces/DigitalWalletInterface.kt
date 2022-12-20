package edu.avaliacao.bank.wallet.walletInterfaces

import edu.avaliacao.bank.client.clientInterfaces.DigitalClientInterface

interface DigitalWalletInterface {
    fun store(msg:String, v: Double): String
    fun invest(msg:String, v: Double): String
    fun pix(msg: String, v: Double, target: DigitalClientInterface): String
    fun payTicket(msg: String, v: Double): String
}