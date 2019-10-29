package org.zongf.wx.power.nation;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.zongf.wx.power.nation.util.MokerUtil;
import org.zongf.wx.power.nation.util.RobotUtil;

import javax.imageio.ImageIO;
import javax.sound.midi.Soundbank;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class RobotUtilTest {


    @Test
    public void moveMouse(){
        MokerUtil.clickMenuQuestion();
    }

    @Test
    public void clickReturn(){
        MokerUtil.clickReturn();
    }

    @Test
    public void clickCaputre(){
        MokerUtil.clickCapture();
    }

    @Test
    public void test() throws Exception{
        byte[] bytes = RobotUtil.screenCapture(0, 68, 554, 950);

        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\zong\\Documents\\雷电模拟器\\Pictures\\Screenshots\\123.png"));
        fos.write(bytes);
        fos.close();
    }

    @Test
    public void test2(){
        String str = null;
        System.out.println(StringUtils.isNotEmpty(str));
        System.out.println(StringUtils.isBlank(str));
    }
}
