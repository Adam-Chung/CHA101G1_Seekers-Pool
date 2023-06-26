package tw.idv.Seeker_Pool_Merge.article.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Img {

    public static void main(String[] args) {
        int arNo = 17; // 文章編號，需要修改
        String imagePath = "C:\\專題\\mySQL\\job02.jpg"; // 圖片文件路徑

        try {
            // 建立與資料庫的連線
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/article?serverTimezone=Asia/Taipei", "root", "QAZ0743");

            // 準備 SQL INSERT 語句
            String query = "UPDATE ARTICLE SET AR_IMG = ? WHERE AR_NO = ?";
            PreparedStatement pstmt = con.prepareStatement(query);

            // 讀取圖片文件
            File imageFile = new File(imagePath);
            InputStream imageInputStream = new FileInputStream(imageFile);

            // 設定參數並執行更新
            pstmt.setBinaryStream(1, imageInputStream);
            pstmt.setInt(2, arNo);
            pstmt.executeUpdate();

            // 關閉資源
            pstmt.close();
            imageInputStream.close();
            con.close();

            System.out.println("圖片已成功存入資料庫");
        } catch (SQLException | IOException e) {
            System.out.println("存入圖片時發生錯誤：" + e.getMessage());
        }
    }
}
