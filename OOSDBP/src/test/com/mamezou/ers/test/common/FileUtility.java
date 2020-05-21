/*
 * FileUtility.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CsvTestCaseで使用する、ファイルコピー用のユーティリティクラスです。
 * @author Mamezou
 */
public class FileUtility {

    /**
     * ファイルをコピーします。
     * @param src コピー元ファイルの抽象パス
     * @param dest コピー先ファイルの抽象パス
     * @throws IOException ファイルIOエラー
     */
    public static void copyFile(final File src, final File dest)
            throws IOException {
        BufferedReader in = null;
        BufferedWriter out = null;
        try {
            in = new BufferedReader(new FileReader(src));
            out = new BufferedWriter(new FileWriter(dest));
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                out.write(line);
                out.newLine();
            }
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("Readerのcloseに失敗しました。確認してください。");
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.out.println("Writerのcloseに失敗しました。。確認してください。");
                e.printStackTrace();
            }
        }
    }

    public static void copyFiles(List<File[]> filePairList) throws IOException {
        for (File[] filePair : filePairList) {
            copyFile(filePair[1], filePair[0]);
        }
    }

    public static List<File[]> backupFile(String[] filePaths) throws IOException {
        List<File[]> files = new ArrayList<File[]>(filePaths.length);
        for (String filePath : filePaths) {
            File original = new File(filePath);
            File dest = backupFile(original);
            files.add(new File[]{original, dest});
        }
        return files;
    }

    /**
     * CSVファイルのバックアップを取る
     * @param targetFile バックアップ対象のファイルの抽象パス
     * @throws IOException バックアップに失敗した場合
     * @return バックアップされたファイルの抽象パス
     */
    public static File backupFile(File targetFile) throws IOException {
        File original = targetFile;
        File backup = File.createTempFile("bu_", "", original.getParentFile());
        copyFile(original, backup);
        return backup;
    }

}
