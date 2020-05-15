package gensrv.example

import cats.effect._
import upickle.default._
import eu.seitzal.http4s_upickle.UPickleEntityCodec

case class Entry(id: Int, content: String, done: Boolean)

object Entry {
  implicit val entryRW = macroRW[Entry]
  implicit val entryEC = new UPickleEntityCodec[IO, Entry]
}

case class EntryPartial(content: String, done: Boolean)

object EntryPartial {
  implicit val entryPartialRW = macroRW[EntryPartial]
  implicit val entryPartialEC = new UPickleEntityCodec[IO, EntryPartial]
}
