package de.dariah.reuse

import java.io.{File, FileWriter}

import de.dariah.reuse.conf.Configuration
import de.dariah.reuse.io.Transformer

/**
 * Reuse
 */
object Reuse {

  def main(args:Array[String]) = {

    val config = new Configuration(args(0))
    assert(config.selfCheck)

    // flatten and copy files
    def fileTransform(src:File,trg:File,trans:Transformer[String]) = {
      val inputString = scala.io.Source.fromFile(src).mkString
      val wrt = new FileWriter(trg)
      wrt.write(trans.transform(inputString,null))
      wrt.flush
      wrt.close
      trg
    }

    // transform input files
    val sourceTmpFiles = config.sourceFiles.map(f => config.importer.transform(f,new File(config.sourceTmp,f.getName)))
    val targetTmpFiles = config.targetFiles.map(f => config.importer.transform(f,new File(config.targetTmp,f.getName)))

    // make pairs of tmp files
    val tmpPairs = targetTmpFiles.map(sourceTmpFiles.zipAll(Nil,new File("."),_)).flatten

    // align pairs
    val resultTmpFiles = tmpPairs.map(p => config.aligner.align(p._1,p._2,config.resultTmp))
    resultTmpFiles.foreach(f => config.exporter.transform(f,new File(config.resultFiles,f.getName)))

  }

}
