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
	  * @param srcDir Ҫѹ�����ļ���·��
	  * @param filename  ѹ�����zip·��
	  * @throws Exception
	  */

	public static void sendReport(String srcDir,String filename) throws Exception {
		try {
			FileOutputStream fos1 = new FileOutputStream(new File(filename));
			ZipUtils.toZip(srcDir, fos1, true);
		} catch (FileNotFoundException e) {
			System.out.println("�ļ�δ�ҵ�");
			e.printStackTrace();
		}
		
		SendMailUtils.mail(filename,"1941294473@qq.com","uijvgyalzszfcgef","15226538851@163.com");
	}

	
}
