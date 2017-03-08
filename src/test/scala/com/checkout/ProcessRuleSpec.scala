package com.checkout

import com.checkout.model.{ Rule, Sku }
import org.scalatest.{ Inside, Matchers, OptionValues, WordSpec }

/**
 * Created by baahko01 on 07/03/2017.
 */
class ProcessRuleSpec extends WordSpec with Matchers with Inside with OptionValues {

  "ProcessRules" should {
    "read the rules from file if rules are valid" in {

      def projectDirectory = new java.io.File(".").getCanonicalPath
      val filename = s"""$projectDirectory/pricing.rules"""

      val rules = ProcessRule(filename)

      rules should have size (4)

      val rule1 = rules(0)
      inside(rule1) {
        case Sku(item, price, rule) =>
          inside(rule.value) {
            case Rule(quantity, price) =>
              quantity should be(3)
              price should be(130.0)
          }
          item should be("A")
          price should be(50.0)
      }

      val rule2 = rules(1)
      inside(rule2) {
        case Sku(item, price, rule) =>
          inside(rule.value) {
            case Rule(quantity, price) =>
              quantity should be(2)
              price should be(45.0)
          }
          item should be("B")
          price should be(30.0)
      }

      val rule3 = rules(2)
      inside(rule3) {
        case Sku(item, price, None) =>
          item should be("C")
          price should be(20.0)
      }

      val rule4 = rules(3)
      inside(rule4) {
        case Sku(item, price, None) =>
          item should be("D")
          price should be(15.0)
      }
    }

    "not read the rules from invalid file" in {

      def projectDirectory = new java.io.File(".").getCanonicalPath
      val filename = s"""$projectDirectory/invalidPrice.rules"""

      val rules = ProcessRule(filename)
      rules should have size (0)
    }
  }
}
