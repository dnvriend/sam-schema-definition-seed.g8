addSbtPlugin("com.github.dnvriend" % "sbt-sam-plugin" % "1.0.20")
addSbtPlugin("com.github.dnvriend" % "sam-schema-plugin" % "1.0.20")

resolvers += Resolver.url("bintray-dnvriend-ivy-sbt-plugins", url("http://dl.bintray.com/dnvriend/sbt-plugins"))(Resolver.ivyStylePatterns)
resolvers += Resolver.bintrayRepo("dnvriend", "maven") 
