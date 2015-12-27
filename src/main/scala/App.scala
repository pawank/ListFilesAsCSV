//#!/bin/sh
//exec scala "$0" "$@"
//!#
package apps

import java.io.File

import ammonite.ops._
import com.github.tototoshi.csv._
//Examples of usage
//<Program> /dummy/path:target jpg png
//<Program> /full/path/of/the/target
object Program {

  def writePathsToCSV(paths:Seq[Path]):Option[String] = {
    val filename = "output.csv"
    val f = new File(filename)
    val writer = CSVWriter.open(f)
    paths.foreach(path => {
      writer.writeRow(List(path.toString()))
    })
    writer.close()
    Some(filename)
  }

  def listOfFiles(path:String, extensions:Seq[String]=Seq.empty):Seq[Path] = {
    //val wd = cwd/s"${path}"

    val (prefix,fullPath) = path.indexOf(":") >= 0 match {
      case true => (path.split(":").head,path.split(":").tail.mkString("/"))
      case _ => ("",path)
    }
    val cwdPath = cwd.toString()
    val isAbsPath = fullPath.startsWith("/")
    val wd:Path = if (isAbsPath) Path(fullPath) else cwd/s"${fullPath}"
    val listed = extensions.isEmpty match {
      case true => ls.rec! wd
      case false => ls.rec! wd |? (f => extensions.contains(f.ext))
    }

    def makeCorrectPath(path:String):String = if (isAbsPath) path else path.replaceAll(cwdPath,"")

    if (prefix.isEmpty) listed.map(path => Path(makeCorrectPath(path.toString()))) else listed.map(path => Path(makeCorrectPath(prefix + path.toString())))
  }

  def main(args:Array[String]) = {
    val paths = listOfFiles(args(0),args.tail.toSeq)
    paths.foreach(println)
    writePathsToCSV(paths)
    print("")
  }
}

//Program.main(args)
