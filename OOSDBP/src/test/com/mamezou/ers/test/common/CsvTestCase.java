/*
 * CsvTestCase.java
 * Copyright (C) 2005 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.ers.test.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;

import com.mamezou.commons.io.CSVFileReader;

/**
 * CSVファイルによる永続化のテストを行うためのTestCaseクラス.
 * 本クラスを継承して実装することで、テスト用のCSVファイルのバックアップと
 * レストアが各テストメソッドの実行前後に行われます.
 *
 * dataaccessパッケージのテストは本クラスを継承してテストケースを
 * 作成すること。
 *
 * @author Mamezou
 */
public abstract class CsvTestCase {

    private File originalFile = null;
    private File backupFile = null;

    /**
     * テストケースで使用するファイルをバックアップする.
     * 対象となるCSVファイルは、{{@link #getCsvFilePath()} の戻り値として
     * 返されるファイルパスのものとなります.
     *
     * @see #getCsvFilePath()
     * @throws Exception 何らかの原因により、バックアップに失敗した場合.
     */
    @Before
    public final void backupFile() throws Exception {
        originalFile = new File(getCsvFilePath());
        backupFile = FileUtility.backupFile(new File(getCsvFilePath()));
    }

    /**
     * バックアップしたファイルをレストアする.
     *
     * @throws Exception 何らかの原因により、レストアに失敗した場合.
     */
    @After
    public final void restoreFile() throws Exception {
        FileUtility.copyFile(backupFile, originalFile);
    }

    /**
     * テストで使用するCSVファイルへのフルパスを返します.
     *
     * @return テストで使用するCSVファイルへのフルパス
     */
    protected abstract String getCsvFilePath();

    /**
     * テスト対象のCSVファイルの全レコードを取得する(読み込む).
     *
     * Listの要素はCSVファイルに含まれる行となります.
     * 1行の各カラムがString[]の要素となります.
     *
     * @return CSVファイルの1行分を要素とするList
     * @throws IOException
     */
    protected final List<String[]> getAllRecords() throws IOException {
        CSVFileReader reader = new CSVFileReader(getCsvFilePath());
        return reader.read();
    }
}
