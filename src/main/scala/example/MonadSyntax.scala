package example

import reflect.macros.blackbox.Context
import cats._

object MonadSyntax {
  def actM[F[_], A](body: A): F[A] = macro internal.ActMImpl.actMImpl[F, A]
  implicit def toMonadOp[F[_], A](fa: F[A])(implicit ev: Monad[F]): MonadOp[F, A] = new MonadOp(fa)
  implicit def toFuncOp[A, B](fa: A => B)(implicit ev: Monad[A => ?]): MonadOp[A => ?, B] = new MonadOp[A => ?, B](fa)
}

class MonadOp[F[_], A](fa: F[A]) {
  def next: A = sys.error(s"next was not macro'ed away!")
}