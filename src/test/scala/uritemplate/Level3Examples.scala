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

package uritemplate

class Level3Examples extends ExpansionSpec {
  def name = "Level 3 examples"
  
  val variables = Map(
    "var"   := "value",
    "hello" := "Hello World!",
    "empty" := "",
    "path"  := "/foo/bar",
    "x"     := "1024",
    "y"     := "768"
  )
  
  example("String expansion with multiple variables        (Sec 3.2.2)")(
    ("map?{x,y}",             "map?1024,768"),
    ("{x,hello,y}",           "1024,Hello%20World%21,768")
  )
  
  example("+ Reserved expansion with multiple variables    (Sec 3.2.3)")(
    ("{+x,hello,y}",          "1024,Hello%20World!,768"),
    ("{+path,x}/here",        "/foo/bar,1024/here")
  )
  
  example("# Fragment expansion with multiple variables    (Sec 3.2.4)")(
    ("{#x,hello,y}",          "#1024,Hello%20World!,768"),
    ("{#path,x}/here",        "#/foo/bar,1024/here")
  )
  
  example(". Label expansion, dot-prefixed                 (Sec 3.2.5)")(
    ("X{.var}",               "X.value"),
    ("X{.x,y}",               "X.1024.768")
  )
  
  example("/ Path segments, slash-prefixed                 (Sec 3.2.6)")(
    ("{/var}",                "/value"),
    ("{/var,x}/here",         "/value/1024/here")
  )
  
  example("; Path-style parameters, semicolon-prefixed     (Sec 3.2.7)")(
    ("{;x,y}",                ";x=1024;y=768"),
    ("{;x,y,empty}",          ";x=1024;y=768;empty")
  )
  
  example("? Form-style query, ampersand-separated         (Sec 3.2.8)")(
    ("{?x,y}",                "?x=1024&y=768"),
    ("{?x,y,empty}",          "?x=1024&y=768&empty=")
  )
  
  example("& Form-style query continuation                 (Sec 3.2.9)")(
    ("?fixed=yes{&x}",        "?fixed=yes&x=1024"),
    ("{&x,y,empty}",          "&x=1024&y=768&empty=")
  )
}