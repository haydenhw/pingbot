package example

import java.sql.{Connection, DriverManager, ResultSet}

object Hello extends App {
  classOf[org.postgresql.Driver]
  val con_str = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=postgres"
  val conn = DriverManager.getConnection(con_str)
  try {
    val stm = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

    val rs = stm.executeQuery("SELECT * from todos")

    while(rs.next) {
      println(rs.getString("text"))
    }
 } finally {
     conn.close()
  }
}

