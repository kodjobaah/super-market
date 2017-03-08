package com.checkout

import com.checkout.model.{ Rule, Sku, ValidateItem }
import org.scalatest.{ Matchers, WordSpec }

/**
 * Created by baahko01 on 08/03/2017.
 */
class ValidateItemSpec extends WordSpec with Matchers {

  val sku = Sku("A", 50, Option(Rule(3, 130)))
  val sku1 = Sku("B", 30, Option(Rule(2, 145)))
  val sku2 = Sku("C", 20, None)
  val sku3 = Sku("D", 15, None)
  val skus = Seq(sku, sku1, sku2, sku3)

  val validateItem = new ValidateItem(skus)
  "ValidateItems" should {

    "return true when item is valid" in {
      validateItem.isItemValid("A") should be equals (true)
    }

    "return false when item is not valid" in {
      validateItem.isItemValid("K") should be equals (false)
    }

  }

}
