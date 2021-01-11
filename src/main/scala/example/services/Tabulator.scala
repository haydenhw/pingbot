package example.services

object Tabulator {
  val ColumnPadding = 4;
  def format(table: Seq[Seq[Any]]) = table match {
    case Seq() => ""
    case _ =>
      val sizes = for (row <- table) yield for (cell <- row) yield if (cell == null) 0 else cell.toString.length
      val colSizes = for (col <- sizes.transpose) yield col.max
      val rows = for (row <- table) yield formatRow(row, colSizes)
      formatRows(rows)
  }

  def formatRows(rows: Seq[String]): String = (rows.head :: rows.tail.toList ::: Nil).mkString("\n")

  def formatRow(row: Seq[Any], colSizes: Seq[Int]): String = {
    val cells = for ((item, size) <- row.zip(colSizes)) yield if (size == 0) "" else ("%" + "-" + size + "s").format(item)
    cells.mkString(" " * ColumnPadding)
  }
}
