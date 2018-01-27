package bigdata.hive;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class HiveTest {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HiveTest test = new HiveTest();
        String tablename = "idl_limao_address_raw_log";
        String ds = "ds";
        String date = "2016-11-01";
        List<String> columns = test.getColumns();

        String original = "select  count(1) from %s";
        try {
            Connection conn = HiveUtil.getConnection(HiveUtil.DRIVERNAME, HiveUtil.URL, HiveUtil.USERNAME, HiveUtil.PASSWORD);
            Statement stmt = conn.createStatement();
            /*for (String colname : columns) {
                ResultSet rs = stmt.executeQuery(String.format(original, tablename));
                System.out.println(colname + "-->" + rs.getString(1));
            }*/
            ResultSet rs = stmt.executeQuery(String.format(original, tablename));
            while (rs.next()) {
                System.out.println("rs.getString(1)" + "-->" + rs.getString(1));
            }


            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



    }

    /**
     * 获取字段值
     */
    public List<String> getColumns() {
        List<String> colunms = new ArrayList<String>();
        //表结构描述sql, 格式如: desc tablename
        String sql = "desc idl_limao_receiver_relation_raw_agg";
        try {
            Connection conn = HiveUtil.getConnection(HiveUtil.DRIVERNAME, HiveUtil.URL, HiveUtil.USERNAME, HiveUtil.PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            /**
             * 执行后得到的结果形式如下：
             col_name        data_type       comment
             limao_nick              string
             receiver_mobile         string
             receiver_name           string
             tid_num                 int
             ds                      string

             # Partition Information
             # col_name              data_type               comment

             ds                      string
             */
            while (rs.next()) {
                if (null != rs.getString(1)) {
                    if (rs.getString(1).startsWith("#") == false) {
                        colunms.add(rs.getString(1));
                    } else {
                        break;
                    }
                }
            }
            stmt.close();
            conn.close();
            return colunms;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
