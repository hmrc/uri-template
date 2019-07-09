URI Template
=============
Complete implementation of [RFC-6570 (uri-templates)](http://tools.ietf.org/html/rfc6570) in Scala

[Apache 2 Licenced](http://www.apache.org/licenses/LICENSE-2.0)

Example of usage (see the tests for more)
--------------------------------------------------

```scala
import uritemplate._
import uk.gov.hmrc.uritemplate.syntax.UriTemplateSyntax

object MyApp extends App with UriTemplateSyntax {

  val uriString = URITemplate("http://example.com/hello/{variable}/things{?p1,p2,p3}")
  val expanded = uriString.templated("variable" -> "world", "p1" -> 42, "p2" -> false, "p3" -> "huh?")
  
  println(expanded)
  // http://example.com/hello/world/things?p1=42&p2=false&p3=%3F

}
```

Dependencies
------------

SBT:

```
libraryDependencies += "uk.gov.hmrc" %% "uri-template" % "$version"
```

Maven:

```xml
<dependency>
  <groupId>uk.gov.hmrc</groupId>
  <artifactId>uri-template_${scalaVersion}</artifactId>
  <version>$version</version>
</dependency>
```
