/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.uritemplate.syntax

import java.util.UUID.randomUUID

import org.scalatest.{Matchers, WordSpec}

class UriTemplateSyntaxSpec extends WordSpec with Matchers with UriTemplateSyntax {

  "templating a URL" should {

    "convert all values to url-encoded strings" when {
      "variables are of different types" in {
        val uuid = randomUUID()
        val integer = 12345
        val boolean = true
        val s1 = "Hi! My name is"
        val s2 = "who?"

        val url = "/resource/{resId}/child/{childId}{?p1,p2,flag}".templated(
          "resId" -> uuid,
          "childId" -> integer,
          "flag" -> boolean,
          "p1" -> s1,
          "p2" -> s2
        )

        url shouldBe s"/resource/$uuid/child/12345?p1=Hi%21%20My%20name%20is&p2=who%3F&flag=true"
      }
    }


    "only produce values in places specified by template placeholders" when {
      "superfluous variables are provided for the template" in {
        val s1 = "Hi! My name is"
        val s2 = "who?"
        val s3 = "My name is"
        val s4 = "huh?"

        val url = "/resource{?p1,p2}".templated(
          "p1" -> s1,
          "p2" -> s2,
          "p3" -> s3,
          "p4" -> s4
        )

        url shouldBe "/resource?p1=Hi%21%20My%20name%20is&p2=who%3F"
        url should include("p2=")
        url should not include "p3="
      }
    }


    "omit empty optional variables" when {
      "there isn't a value to slot into an optional parameter" in {
        val url = "/resource{?op1,op2,op3}".templated(
          "op1" -> Some(1),
          "op2" -> None,
          "op3" -> Some("huh?")
        )

        url shouldBe "/resource?op1=1&op3=huh%3F"
        url should not include "op2="
      }
    }
  }
}
