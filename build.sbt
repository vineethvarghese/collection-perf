uniform.project("collection-perf", "au.com.cba.omnia.collectionperf")

uniformDependencySettings

strictDependencySettings

libraryDependencies := 
  depend.omnia("omnitool-core","1.12.0-20151021050758-700b9d0")

updateOptions := updateOptions.value.withCachedResolution(true)

publishArtifact in Test := true

