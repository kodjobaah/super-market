package com.main

import com.checkout.{ Checkout, ProcessRule }

object SuperMarket {

  def main(args: Array[String]) {
    OptionReader.read(args) match {
      case Some(options) =>
        val sku = ProcessRule(options.ruleFile)
        val checkout = new Checkout(sku)
        checkout.start()

      case None =>
        sys.exit(3)
    }
  }

}