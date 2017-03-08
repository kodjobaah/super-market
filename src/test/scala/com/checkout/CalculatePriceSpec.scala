package com.checkout

import com.checkout.model.{ Rule, Sku }
import org.scalatest.{ Matchers, WordSpec }

/**
 * Created by baahko01 on 08/03/2017.
 */
class CalculatePriceSpec extends WordSpec with Matchers {
  val sku = Sku("A", 50, Option(Rule(3, 130)))
  val sku1 = Sku("B", 30, Option(Rule(2, 45)))
  val sku2 = Sku("C", 20, None)
  val sku3 = Sku("D", 15, None)
  val skus = Seq(sku, sku1, sku2, sku3)

  "Given a single sku, calculatePrice" should {
    "return 50 for 1 item" in {
      val checkoutItem: Map[String, Int] = Map("A" -> 1)
      val result = CalculatePrice.getTotal(checkoutItem, skus)
      result should equal(.5)
    }

    "return 1.30 for 3 A" in {
      val checkoutItem: Map[String, Int] = Map("A" -> 3)
      val result = CalculatePrice.getTotal(checkoutItem, skus)
      result should equal(1.30)
    }

    "return 0.95 for 2 B and 1 A" in {
      val checkoutItem: Map[String, Int] = Map("A" -> 1, "B" -> 2)
      val result = CalculatePrice.getTotal(checkoutItem, skus)
      result should equal(0.95)
    }

  }
}
