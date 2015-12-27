//#!/bin/sh
//exec scala "$0" "$@"
//!#
package apps

import ammonite.ops._


object Program {

  def listOfFiles(path:String, extensions:Seq[String]=Seq.empty):Seq[Path] = {
    //val wd = cwd/s"${path}"
    val wd:Path = Path(path)
    val listed = extensions.isEmpty match {
      case true => ls.rec! wd
      case false => ls.rec! wd |? (f => extensions.contains(f.ext))
    }
    listed.foreach(println)
    listed
  }

  def main(args:Array[String]) = {
    listOfFiles(args(0),args.tail.toSeq)
    print("")
  }
}

//Program.main(args)
