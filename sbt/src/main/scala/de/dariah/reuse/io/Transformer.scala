package de.dariah.reuse.io

/**
 * Transformer
 */
trait Transformer[K] {
  def transform(s:K,t:K):K
}
