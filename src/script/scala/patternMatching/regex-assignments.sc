/**
  * 4.12 模式匹配的其他用法
  * 在正则表达式中用模式匹配去解构字符串。
  */
//用于提取列
val cols = """\*|[\w, ]+"""

//用于表
val table = """\w+"""


//用于其他语句
val tail = """.*"""

val selectRE = s"""SELECT\\s*(DISTINCT)?\\s($cols)\\s*FROM\\s($table)\\s*($tail)?;""".r

val selectRE(distinct1, cols1, table1, otherClauses1) = "SELECT DISTINCT * FROM ret_content_http;"

val selectRE(distinct2, cols2, table2, otherClauses2) = "SELECT col1, col2 FROM reg_content_im_chat;"

val selectRE(distinct3, cols3, table3, otherClauses3) = "SELECT DISTINCT col1, col2 FROM reg_content_ftp;"

val selectRE(distinct4, cols4, table4, otherClauses4) = "SELECT DISTINCT col1, col2, col3 FROM reg_read_id WHERE col1 = 'foo';"