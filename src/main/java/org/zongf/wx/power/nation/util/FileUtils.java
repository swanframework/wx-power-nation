package org.zongf.wx.power.nation.util;

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

}
