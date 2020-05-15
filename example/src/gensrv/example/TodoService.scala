package gensrv.example

import gensrv._
import cats._
import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.dsl.io._
import doobie._
import doobie.implicits._

object TodoService extends GenService {

  val routes = (xa, config) => {

    case GET -> Root =>
      Ok("I am a ToDo service")

    case GET -> Root / "entry" / IntVar(entryId) =>
      sql"SELECT * FROM entries WHERE id = $entryId"
        .query[Entry]
        .unique
        .transact(xa)
        .flatMap(Ok(_))

    case rq @ POST -> Root / "entry" =>
      rq.as[EntryPartial]
        .flatMap(entry =>
          sql"INSERT INTO entries (content, done) VALUES (${entry.content}, ${entry.done})"
            .update
            .withUniqueGeneratedKeys[Entry]("id", "content", "done")
            .transact(xa))
        .flatMap(Ok(_))

  }

}
