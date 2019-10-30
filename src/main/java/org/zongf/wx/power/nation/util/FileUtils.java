package org.zongf.wx.power.nation.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zongf
 * @created: 2019-10-27
 * @since 1.0
 */
public class FileUtils {

    /** 获取指定目录下下所有图片路径
     * @param dirPath 目录路径
     * @return List<String>
     * @since 1.0
     * @author zongf
     * @created 2019-10-27
     */
    public static List<String> getFileNames(String dirPath) {
        try {
            return Files.list(Paths.get(dirPath))
                    .map(path -> path.toString())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("获取文件列表失败, 文件目录:" + dirPath, e);
        }
    }

    /** 获取文件字节
     * @param filePath 文件路径
     * @return byte[]
     * @since 1.0
     * @author zongf
     * @created 2019-10-27
     */
    public static byte[] getFileBytes(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** 获取图片字节流, 自动去除头部
     * @param filePath  图片路径
     * @return byte[]  图片的字节数组
     * @since 1.0
     * @author zongf
     * @created 2019-10-30
     */
    public static byte[] getImageBytesWithoutHead(String filePath) {
        int headHeight = 55;
        try {
            // 图片读取路径
            // 1.得到图片的输入流
            FileInputStream input = new FileInputStream(filePath);
            // 2.用工具类ImageIO得到BufferedImage对象,将图片信息放入缓存区
            BufferedImage image = ImageIO.read(input);

            // 3.设置截图图片的(x坐标,y坐标,width宽,height高)信息,并返回截切的新图片,存入缓存区
            BufferedImage result = image.getSubimage(0, headHeight, image.getWidth(), image.getHeight() - headHeight);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(result, "png", bos);

            return bos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
