package au.com.cba.omnia.collectionperf

import scala.util.Random
import scala.collection.immutable.Range

import java.nio.file.Path
import java.nio.file.FileSystems

import scalaz._, Scalaz._

import au.com.cba.omnia.omnitool.Result

object Hdfs {
  val tmpDir = s"""${System.getProperty("java.io.tmpdir")}/collectionperf/files""" //inline?
  val fs     = FileSystems.getDefault
  
  def toPath(n: Int) = fs.getPath(tmpDir, n.toString)

  def paths: Result[List[Path]] = for {
    nums  <- Result.ok(Range(1, 50000).toList)
    paths <- Result.safe(nums.map(toPath _))
  } yield paths
}

object ViewHive {

  def doWithPath(p: Path): Result[Unit] = {
    Result.ok(())
  }

  def writeToTable(num: Int): Result[Unit] = for {
    p1s <- Hdfs.paths
    _   <- Result.safe(Thread.sleep(1000))
    p2s <- Hdfs.paths.map(Hdfs.toPath(1000000) :: _)
    ps   = p2s.diff(p1s)
    -   <- ps.map(doWithPath(_)).sequence
  } yield ()
}

object CollectionPerf extends App {
  for {
    _ <- Result.ok(println("Starting program...."))
    r <- Result.ok(Range(1, 2000).toList)
      _ <- Result.ok(println("Ending program...."))
  } yield ()
}