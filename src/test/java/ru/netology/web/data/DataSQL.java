package ru.netology.web.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSQL {
    private static QueryRunner runner = new QueryRunner();

    public static Connection runConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://192.168.99.101:3306/app", "app", "pass");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static String getPaymentStatus() {
        val paymentStatus = "SELECT status FROM payment_data WHERE created >= DATE_SUB(NOW(), INTERVAL 3 SECOND);";
        try (
                val conn = runConnection();
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(paymentStatus)) {
                if (rs.next()) {
                    val status = rs.getString("status");
                    return status;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getCreditStatus() {
        val select = "SELECT status FROM credit_data WHERE created  >= DATE_SUB(NOW(), INTERVAL 3 SECOND);";
        try (
                val conn = runConnection();
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(select)) {
                if (rs.next()) {
                    val status = rs.getString("status");
                    return status;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getOrderPaymentId() {
        val select = "SELECT payment_id FROM order_data WHERE created >= DATE_SUB(NOW(), INTERVAL 3 SECOND);";
        try (
                val conn = runConnection();
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(select)) {
                if (rs.next()) {
                    val getPaymentId = rs.getString("payment_id");
                    return getPaymentId;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getOrderCreditId() {
        val select = "SELECT credit_id FROM order_data WHERE created >= DATE_SUB(NOW(), INTERVAL 3 SECOND);";
        try (
                val conn = runConnection();
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(select)) {
                if (rs.next()) {
                    val getCreditId = rs.getString("credit_id");
                    return getCreditId;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getCreditId() {
        val select = "SELECT bank_id FROM credit_data WHERE created >= DATE_SUB(NOW(), INTERVAL 3 SECOND);";
        try (
                val conn = runConnection();
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(select)) {
                if (rs.next()) {
                    val getBankId = rs.getString("bank_id");
                    return getBankId;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String getPaymentId() {
        val select = "SELECT transaction_id FROM payment_data WHERE created  >= DATE_SUB(NOW(), INTERVAL 3 SECOND);";
        try (
                val conn = runConnection();
                val countStmt = conn.createStatement();
        ) {
            try (val rs = countStmt.executeQuery(select)) {
                if (rs.next()) {
                    val getTransactionId = rs.getString("transaction_id");
                    return getTransactionId;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void cleanTables() {
        val deleteCreditData = "DELETE FROM credit_data";
        val deleteOrderData = "DELETE FROM order_data";
        val deletePaymentData = "DELETE FROM payment_data";
        try (
                val conn = runConnection();
        ) {
            runner.update(conn, deleteCreditData);
            runner.update(conn, deletePaymentData);
            runner.update(conn, deleteOrderData);
            System.out.println("Tables are clean");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
