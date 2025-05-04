lazy val root = (project in file("."))
  .settings(
    organization     := "com.martinbrosenberg",
    name             := "CoinFlip",
    idePackagePrefix := Some("com.martinbrosenberg.coinflip"),
    version          := "0.0.1-SNAPSHOT",
    scalaVersion     := "3.6.4",
    libraryDependencies ++= Seq(
      ("org.typelevel"      %% "cats-effect"  % "3.6.1").withSources().withJavadoc(),
      // Testing
      "com.github.dwickern" %% "scala-nameof" % "5.0.0"  % "provided",
      "org.scalacheck"      %% "scalacheck"   % "1.18.1" % "test",
      "org.scalatest"       %% "scalatest"    % "3.2.19" % "test"
    ),
    scalacOptions ++= Seq(
      "-deprecation",
      "-feature",
      "-language:postfixOps",
      "-language:higherKinds",
      "-no-indent",
      "-old-syntax",
      "-unchecked"
    )
  )
