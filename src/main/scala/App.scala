//#!/bin/sh
//exec scala "$0" "$@"
//!#
package apps

import ammonite.ops._


object Program {
  def main(args:Array[String]) = {
    val wd = cwd/s"${args(0)}"
    val listed = ls! wd
    println(listed)
  }
}

//Program.main(args)
