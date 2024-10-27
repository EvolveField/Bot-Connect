package cn.evole.mods.mcbot.util;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Project: McBot
 * @Author: cnlimiter
 * @CreateTime: 2024/10/27 23:29
 * @Description:
 */
public class CompressUtils {
    /**
     * tar拆包解压
     *
     * @param decompressFilePath
     *            要被解压的压缩文件 全路径
     * @param resultDirPath
     *            解压文件存放绝对路径(目录)
     */
    public static boolean tarDecompression(String decompressFilePath, String resultDirPath) throws IOException {
        TarArchiveInputStream tais = null;
        FileInputStream fis = null;
        try {
            File file = new File(decompressFilePath);
            fis = new FileInputStream(file);
            tais = new TarArchiveInputStream(fis);
            TarArchiveEntry tae = null;
            while ((tae = tais.getNextTarEntry()) != null) {
                BufferedOutputStream bos = null;
                FileOutputStream fos = null;
                try {
                    String dir = resultDirPath + File.separator + tae.getName();// tar档中文件
                    File dirFile = new File(dir);
                    fos = new FileOutputStream(dirFile);
                    bos = new BufferedOutputStream(fos);
                    int count;
                    byte[] data = new byte[1024];
                    while ((count = tais.read(data, 0, 1024)) != -1) {
                        bos.write(data, 0, count);
                    }
                } finally {
                    if (bos != null)
                        bos.close();
                    if (fos != null)
                        fos.close();
                }
            }
        } finally {
            if (tais != null)
                tais.close();
            if (fis != null)
                fis.close();
        }
        return true;
    }
    /**
     * 解压对.tar.gz文件至 .tar文件
     * 说明:我们一般都是对.tar.gz文件进行gzip解压; 进而获得形如.tar文件;再进行解压
     *   注:这里暂时不再深入学习,以后有闲暇时间可深入了解学习
     *
     * @param compressedFilePath
     *            要被解压的压缩文件 全路径
     * @param resultDirPath
     *            解压文件存放绝对路径(目录)
     */
    public static boolean gzipDecompression(String compressedFilePath, String resultDirPath) throws IOException {
        boolean b = false;
        InputStream fin = null;
        BufferedInputStream in = null;
        OutputStream out = null;
        GzipCompressorInputStream gcis = null;
        try {
            Path path_my = Paths.get(resultDirPath);
            out = Files.newOutputStream(path_my);
            fin = Files.newInputStream(Paths.get(compressedFilePath));
            in = new BufferedInputStream(fin);
            gcis = new GzipCompressorInputStream(in);
            final byte[] buffer = new byte[1024];
            int n = 0;
            while (-1 != (n = gcis.read(buffer))) {
                out.write(buffer, 0, n);
            }
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(gcis != null)
                gcis.close();
            if(in != null)
                in.close();
            if(fin != null)
                fin.close();
            if(out != null)
                out.close();
        }
        return b;
    }
    /**
     * zip解压(注:与tar类似)
     *
     * @param decompressFilePath
     *            要被解压的压缩文件 全路径
     * @param resultDirPath
     *            解压文件存放绝对路径(目录)
     */
    public static boolean zipDecompression(String decompressFilePath, String resultDirPath) throws IOException {
        ZipArchiveInputStream zais = null;
        FileInputStream fis = null;
        try {
            File file = new File(decompressFilePath);
            fis = new FileInputStream(file);
            zais = new ZipArchiveInputStream(fis);
            ZipArchiveEntry zae = null;
            while ((zae = zais.getNextZipEntry()) != null) {
                FileOutputStream fos = null;
                BufferedOutputStream bos = null;
                try {
                    String dir = resultDirPath + File.separator + zae.getName();// tar档中文件
                    File dirFile = new File(dir);
                    fos = new FileOutputStream(dirFile);
                    bos = new BufferedOutputStream(fos);
                    int count;
                    byte[] data = new byte[1024];
                    while ((count = zais.read(data, 0, 1024)) != -1) {
                        bos.write(data, 0, count);
                    }
                } finally {
                    if (bos != null)
                        bos.close();
                    if (fos != null)
                        fos.close();
                }
            }
        } finally {
            if (zais != null)
                zais.close();
            if (fis != null)
                fis.close();
        }
        return true;
    }

    public static void tarGzipDecompression(String decompressFilePath, String resultDirPath) throws IOException {
        try (InputStream fi = Files.newInputStream(Path.of(decompressFilePath));
             BufferedInputStream bi = new BufferedInputStream(fi);
             GzipCompressorInputStream gzi = new GzipCompressorInputStream(bi);
             TarArchiveInputStream ti = new TarArchiveInputStream(gzi)) {

            ArchiveEntry entry;
            while ((entry = ti.getNextEntry()) != null) {

                //获取解压文件目录，并判断文件是否损坏
                Path newPath = zipSlipProtect(entry, Path.of(resultDirPath));

                if (entry.isDirectory()) {
                    //创建解压文件目录
                    Files.createDirectories(newPath);
                } else {
                    //再次校验解压文件目录是否存在
                    Path parent = newPath.getParent();
                    if (parent != null) {
                        if (Files.notExists(parent)) {
                            Files.createDirectories(parent);
                        }
                    }
                    // 将解压文件输入到TarArchiveInputStream，输出到磁盘newPath目录
                    Files.copy(ti, newPath, StandardCopyOption.REPLACE_EXISTING);

                }
            }
        }
    }

    private static Path zipSlipProtect(ArchiveEntry entry, Path targetDir)
            throws IOException {

        Path targetDirResolved = targetDir.resolve(entry.getName());
        Path normalizePath = targetDirResolved.normalize();

        if (!normalizePath.startsWith(targetDir)) {
            throw new IOException("压缩文件已被损坏: " + entry.getName());
        }

        return normalizePath;
    }
}
