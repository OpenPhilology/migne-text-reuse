package de.dariah.reuse.align

import java.io.{File, FileWriter}
import de.dariah.reuse.conf.Configuration
import edu.umass.ciir.RecursiveAlignmentTool

import scala.collection.JavaConversions._

/**
 * RETASAligner
 */
class RETASAligner extends Aligner[File] {
  // export alignments
  def writeResults(file:File,tokens:Tuple2[List[String],List[String]]) = {
    val wrt = new FileWriter(file)
    wrt.write(tokens._1.mkString("\t")+"\n"+tokens._2.mkString("\t"))
    wrt.flush
    wrt.close
  }

  override def align(src: File, trg: File, tmpDir:File): File = {
    val array = RecursiveAlignmentTool.processSingleJob_getAlignedSequence(src.getAbsolutePath,trg.getAbsolutePath,"","")
    val unzipped = array.map(a => (a.getReference,a.getCandidate)).unzip
    val srcID = src.getName.reverse.dropWhile(_!='.').reverse
    val trgID = trg.getName.reverse.dropWhile(_!='.').reverse
    val resID = srcID+"@"+trgID
    val resTmpFile = new File(tmpDir,resID+".txt")
    writeResults(resTmpFile,(unzipped._1.toList,unzipped._2.toList))
    resTmpFile
  }
}
