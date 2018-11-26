package com.webtest.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.webtest.utils.ZipUtils;
import com.webtest.utils.SendMailUtils;

public class SendReport {
	
	 /**
	  * @author wangdonghui
	  * @param srcDir 要压缩的文件夹路径
	  * @param filename  压缩后的zip路径
	  * @throws Exception
	  */

	public static void sendReport(String srcDir,String filename) throws Exception {
		try {
			FileOutputStream fos1 = new FileOutputStream(new File(filename));
			ZipUtils.toZip(srcDir, fos1, true);
		} catch (FileNotFoundException e) {
			System.out.println("文件未找到");
			e.printStackTrace();
		}
		
		SendMailUtils.mail(filename,"1941294473@qq.com","uijvgyalzszfcgef","15226538851@163.com");
	}

	
}
