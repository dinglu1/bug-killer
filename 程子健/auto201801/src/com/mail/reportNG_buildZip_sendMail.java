package com.mail;

public class reportNG_buildZip_sendMail {
	public static void main(String[] args) throws Exception{
		//压缩指定文件对象
				String file = "D:\\g3softwaretesting\\practical_training\\test-output\\html";//也可以是文件夹路径
				//待生成的zip包名
				String zipName = "setZip";
				//待生成的zip保存路径
				String zipFilePath = "D:\\";
				//压缩
				buildZip.fileToZip(file , zipFilePath , zipName);
		//将打包文件作为附件发送邮件。
				sendMail s1 = new sendMail();
				s1.setTitle_body("标题","正文");
				s1.Mail();
		
	}

}
