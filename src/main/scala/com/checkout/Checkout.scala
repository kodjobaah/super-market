package com.checkout

import com.checkout.model.{ Sku, ValidateItem }

/**
 * Created by baahko01 on 08/03/2017.
 */
class Checkout(skus: Seq[Sku]) {

  val checkoutItems = collection.mutable.Map[String, Int]()
  def start() {

    val validateItem = new ValidateItem(skus)
    println("please enter a sku or -1 to terminate\n")
    var input: String = scala.io.StdIn.readLine()
    while (!input.equalsIgnoreCase("-1")) {

      if (validateItem.isItemValid(input)) {
        val count: Option[Int] = checkoutItems.get(input)
        count match {
          case None =>
            checkoutItems.put(input, 1)
          case Some(cnt) =>
            checkoutItems.put(input, cnt + 1)
        }

      } else {
        println("sku is not valid")

      }
      val total = CalculatePrice.getTotal(checkoutItems.toMap, skus)
      println(s"""total = £$total""")
      println("please enter a sku or -1 to terminate\n")
      input = scala.io.StdIn.readLine()
    }
  }
}
