URI Template
=============
Complete implementation of [RFC-6570 (uri-templates)](http://tools.ietf.org/html/rfc6570) in Scala

Copyright 2012 Jon-Anders Teigen

[Apache 2 Licenced](http://www.apache.org/licenses/LICENSE-2.0)

## Example of usage (see the tests for more)

```scala
import uk.gov.hmrc.uritemplate.syntax.UriTemplateSyntax

object MyApp extends App with UriTemplateSyntax {

  val uriString: String = "http://example.com/hello/{variable}/things{?p1,p2,p3}"
  val templatedUri = uriString.templated("variable" -> "world", "p1" -> 42, "p2" -> false, "p3" -> "huh?")

  assert(templatedUri == "http://example.com/hello/world/things?p1=42&p2=false&p3=huh%3F")

}
```

## Add to your project's build

**SBT**

```
resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"

libraryDependencies += "uk.gov.hmrc" %% "uri-template" % "$version"
```
