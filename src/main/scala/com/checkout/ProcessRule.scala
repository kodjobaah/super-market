package com.checkout

import com.checkout.model.{ Rule, Sku }

import scala.io.Source

object ProcessRule {

  def apply(pathToRuleFile: String): Seq[Sku] = {
    try {
      Source.fromFile(pathToRuleFile).getLines().map { line =>
        var elements = line.split(",")
        val item = elements(0)
        val price = elements(1)
        val rule = if (elements.length > 2) {
          val priceRule: Array[String] = elements(2).split("for")
          Option(Rule(priceRule(0).trim.toInt, priceRule(1).trim.toInt))
        } else {
          None
        }
        Sku(item.trim, price.trim.toInt, rule)
      }.toList
    } catch {
      case ex: Exception =>
        Seq.empty[Sku]
    }
  }

}

