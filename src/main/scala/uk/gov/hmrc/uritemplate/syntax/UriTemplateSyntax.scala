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

import uritemplate.Syntax._
import uritemplate.URITemplate

trait UriTemplateSyntax {

  implicit class UriTemplateOps(val uriTemplate: String) {
    def templated(first: (String, Any), rest: (String, Any)*): String =
      URITemplate(uriTemplate).expand {
        (first +: rest).collect {
          case (k, Some(v)) => k := v.toString
          case (k, v) if v != None => k := v.toString
        }.toMap
      }
  }

}

object UriTemplateSyntax extends UriTemplateSyntax
