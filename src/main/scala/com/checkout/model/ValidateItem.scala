package com.checkout.model

/**
 * Created by baahko01 on 08/03/2017.
 */
class ValidateItem(skus: Seq[Sku]) {
  def isItemValid(item: String): Boolean = {
    skus.filter(_.item.equalsIgnoreCase(item)).size == 1
  }
}
