package tw.idv.Seeker_Pool_Merge.common.util;

import java.util.Random;

/**
 * 取得隨機變數
 * @author fong
 *
 */
public class RandCodeUtil {

	public static String getRandomCode(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }

        return sb.toString();
    }
}
