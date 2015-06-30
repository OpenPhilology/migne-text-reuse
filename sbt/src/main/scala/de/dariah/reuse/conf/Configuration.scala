package de.dariah.reuse.conf

import java.io.File
import java.text.SimpleDateFormat
import de.dariah.reuse.align.Aligner
import de.dariah.reuse.io.Transformer

import scala.xml.XML

/**
 * CONFIG
 * runID, source, target, res, tmp & derivates
 */
class Configuration(filename:String) {

  val xml = XML.load(filename)

  // initializing
  val runID = if ((xml \ "id").text!="") (xml \ "id").text
              else new SimpleDateFormat("yyyy-MM-dd.HH-mm-ss").format(new java.util.Date)

  // acquire source and target files and tmp directory
  val sourceFiles = new File((xml \ "files" \ "source").text).listFiles
  val targetFiles = new File((xml \ "files" \ "target").text).listFiles
  val resultFiles = new File((xml \ "files" \ "result").text + s"/${runID}")
  val sourceTmp = new File((xml \ "files" \ "temporary").text + s"/${runID}/src")
  val targetTmp = new File((xml \ "files" \ "temporary").text + s"/${runID}/trg")
  val resultTmp = new File((xml \ "files" \ "temporary").text + s"/${runID}/res")

  val importer = Class.forName((xml \ "classes" \ "import").text).newInstance.asInstanceOf[Transformer[File]]
  val aligner = Class.forName((xml \ "classes" \ "alignment").text).newInstance.asInstanceOf[Aligner[File]]
  val exporter = Class.forName((xml \ "classes" \ "export").text).newInstance.asInstanceOf[Transformer[File]]

  def selfCheck = sourceFiles.foldLeft(true)(_ && _.exists) && sourceFiles.foldLeft(true)(_ && _.exists) && resultFiles.mkdirs && sourceTmp.mkdirs && targetTmp.mkdirs && resultTmp.mkdirs

}