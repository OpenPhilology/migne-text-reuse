name := """perseus-citology"""

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies ++= Seq(
  // "no.arktekk" %% "anti-xml" % "0.6.0",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.3.11",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.4"
)

unmanagedJars in Compile ++= {
      val libs = baseDirectory.value / "lib"
      val subs = (libs ** "*") filter { _.isDirectory }
      val targets = ( (subs / "target") ** "*" ) filter {f => f.name.startsWith("scala-") && f.isDirectory}
      ((libs +++ subs +++ targets) ** "*.jar").classpath
}
