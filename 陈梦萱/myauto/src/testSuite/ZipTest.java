
package testSuite;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;

import java.io.Closeable;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

import java.util.zip.ZipEntry;

import java.util.zip.ZipInputStream;

import java.util.zip.ZipOutputStream;
/**
 * ����ժ��
 * ѹ���ļ�
 * 
 */
 

public  class ZipTest{
    public static void zip(String zipPath,String[] filePaths)
    {
        File target = new File(zipPath);
        // ѹ���ļ���=Դ�ļ���.zip
        String zipName = zipPath;
        if (target.exists())
        {
            target.delete(); // ɾ���ɵ��ļ�
        }
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        try
        {
            fos = new FileOutputStream(target);
            zos = new ZipOutputStream(new BufferedOutputStream(fos));
            // ��Ӷ�Ӧ���ļ�Entry
            addEntry(filePaths, zos);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            IOUtil.closeQuietly(zos, fos);
        }
    }

 
    private static void addEntry(String[] filePaths, ZipOutputStream zos)
        throws IOException
    {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            File tempFile = null;
            try
            {
                for (String filePath : filePaths)
                {
                    tempFile = new File(filePath);
                    fis = new FileInputStream(tempFile);
                    byte[] buffer = new byte[1024 * 10];
                    bis = new BufferedInputStream(fis, buffer.length);
                    int read = 0;
                    zos.putNextEntry(new ZipEntry(tempFile.getName()));
                    while ((read = bis.read(buffer, 0, buffer.length)) != -1)
                    {
                        zos.write(buffer, 0, read);
                    }
                    zos.closeEntry();
                }
            }
            finally
            {
                IOUtil.closeQuietly(bis, fis);
            }
    }
    public static void unzip(String filePath)
    {
        File source = new File(filePath);
        if (source.exists())
        {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try
            {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null)
                {
                    if(entry.isDirectory()){
                        continue;
                    }
                    File target = new File(source.getParent(), entry.getName());
                    if (!target.getParentFile().exists())
                    {
                        // �����ļ���Ŀ¼
                        target.getParentFile().mkdirs();
                    }
                    // д���ļ�
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1)
                    {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            finally
            {
                IOUtil.closeQuietly(zis, bos);
            }
        }
    }
//    public static void main(String[] args) {
//    	String s = "C:\\Users\\xuan\\eclipse-workspace\\myauto\\test-output\\html\\";
// 		String filepath= "C:\\Users\\xuan\\eclipse-workspace\\myauto\\test-output\\1.zip";
// 		ZipTest.zip(filepath,new String[]{s+"index.html",s+"output.html",
//         		s+"overview.html",s+"reportng.css",s+"reportng.js",s+"suite1_test1_results.html",s+"suites.html"});
//			
//	    }
}

 


class IOUtil {

    /**
     * �ر�һ������������
    * 
     * @param closeables
     *            �ɹرյ��������б�
     * @throws IOException
     */
  public static void close(Closeable... closeables) throws IOException {
      if (closeables != null) {
           for (Closeable closeable : closeables) {
               if (closeable != null) {
                   closeable.close();
               }
            }
        }
    }
   /**
     * �ر�һ������������
     * 
    * @param closeables
     *            �ɹرյ��������б�
     */
    public static void closeQuietly(Closeable... closeables) {
        try {
            close(closeables);
        } catch (IOException e) {
            // do nothing
        }
    }
    
}
