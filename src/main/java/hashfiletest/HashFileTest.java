package hashfiletest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.codec.digest.DigestUtils;

public class HashFileTest {

    public static String hash_of_File(File buffile, String tip) {
        if (!buffile.isFile()) {
            JOptionPane.showMessageDialog(null, "not a file !");
            return "";
        }
        try (FileInputStream fis = new FileInputStream(buffile);
                BufferedInputStream bis = new BufferedInputStream(fis);) {
            switch (tip) {
                case "md2":    return DigestUtils.md2Hex(bis);
                case "md5":    return DigestUtils.md5Hex(bis);
                case "sha1":   return DigestUtils.sha1Hex(bis);
                case "sha256": return DigestUtils.sha256Hex(bis);
                case "sha384": return DigestUtils.sha384Hex(bis);
                case "sha512": return DigestUtils.sha512Hex(bis);
                default:       return "wrong hash type";
            }
        } catch (FileNotFoundException fex) {
        } catch (IOException ex) {        }
        return "";
    }

    public static void main(String[] args) {
        String tip = JOptionPane.showInputDialog("input Hash Type:");
        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            JOptionPane.showMessageDialog(null, hash_of_File(file, tip));
        }
    }

}
