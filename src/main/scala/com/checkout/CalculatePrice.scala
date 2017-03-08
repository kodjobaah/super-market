package com.checkout

import com.checkout.model.Sku

/**
 * Created by baahko01 on 08/03/2017.
 */
object CalculatePrice {

  def getTotal(checkoutItems: Map[String, Int], rules: Seq[Sku]): Double = {
    checkoutItems.foldLeft(0.0) {
      case (total, (item, quantity)) =>
        val sku: Seq[Sku] = rules.filter(_.item.equalsIgnoreCase(item))
        val price = sku match {
          case s :: Nil =>

            s.rule match {
              case None =>
                (quantity * (s.price / 100))
              case Some(rule) =>
                val special: Int = quantity / rule.quantity
                val specialPrice: Double = special * (rule.price / 100)
                val leftOver = quantity % rule.quantity
                val leftOverPrice: Double = leftOver * (s.price / 100)
                specialPrice + leftOverPrice
            }
          case _ => 0
        }
        total + price
    }
  }
}
