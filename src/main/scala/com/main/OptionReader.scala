package com.main

import scopt.OptionParser

case class CheckoutOptions(
  ruleFile: String = ""
)

object OptionReader {

  def read(args: Array[String]): Option[CheckoutOptions] = {
    val parser = new OptionParser[CheckoutOptions]("Checkout") {

      head("checkout")

      opt[String]('f', "ruleFile") required () valueName "ruleFie" action { (x, c) =>
        c.copy(ruleFile = x)
      } text "rules file"
    }

    parser.parse(args, CheckoutOptions())
  }
}

