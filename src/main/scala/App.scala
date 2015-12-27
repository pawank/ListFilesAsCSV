//#!/bin/sh
//exec scala "$0" "$@"
//!#
package apps

import ammonite.ops._


object Program {

  def listOfFiles(path:String, extensions:Seq[String]=Seq.empty):Seq[Path] = {
    //val wd = cwd/s"${path}"
    val (prefix,fullPath) = path.indexOf(":") >= 0 match {
      case true => (path.split(":").head,path.split(":").tail.mkString("/"))
      case _ => ("",path)
    }
    val wd:Path = Path(fullPath)
    val listed = extensions.isEmpty match {
      case true => ls.rec! wd
      case false => ls.rec! wd |? (f => extensions.contains(f.ext))
    }
    if (prefix.isEmpty) listed else listed.map(path => Path(prefix + path.toString()))
  }

  def main(args:Array[String]) = {
    val paths = listOfFiles(args(0),args.tail.toSeq)
    paths.foreach(println)
    print("")
  }
}

//Program.main(args)
