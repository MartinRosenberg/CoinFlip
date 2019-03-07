lazy val root = (project in file("."))
  .settings(
    organization := "com.martinbrosenberg",
    name := "CoinFlip",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.8",
    libraryDependencies ++= Seq(
      ("org.typelevel"      %% "cats-effect"  % "1.2.0").withSources().withJavadoc(),
      // Testing
      "com.github.dwickern" %% "scala-nameof" % "1.0.3"  % "provided",
      "org.scalacheck"      %% "scalacheck"   % "1.14.0" % "test",
      "org.scalatest"       %% "scalatest"    % "3.0.5"  % "test",
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-language:postfixOps",
      "-language:higherKinds",
      "-Ypartial-unification",
    )
  )
