package de.dariah.reuse.io

import java.io.File
import java.nio.file.Files

import de.dariah.reuse.conf.Configuration

/**
 * IdentityFileTransformer
 */
class IdentityFileTransformer extends Transformer[File] {
  override def transform(f:File,g:File):File = Files.copy(f.toPath, g.toPath).toFile
}