package de.dariah.reuse.align

/**
 * Aligner
 */
trait Aligner[K] {
  def align(src:K,trg:K,res:K):K
}
