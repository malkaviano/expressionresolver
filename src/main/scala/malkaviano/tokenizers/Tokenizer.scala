package malkaviano.tokenizers

trait Tokenizer {
  def generate(json: String): Seq[Any]
}
