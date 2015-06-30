package de.dariah.reuse.io

import de.dariah.reuse.conf.Configuration

/**
 * IdentityStringTransformer
 */
class IdentityStringTransformer extends Transformer[String] {
  override def transform(s: String,t:String=null): String = s
}
