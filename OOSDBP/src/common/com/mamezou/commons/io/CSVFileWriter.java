/*
 * CSVFileWriter.java
 * Copyright (C) 2002-2003 Mamezou Co., Ltd. All rights reserved.
 */
package com.mamezou.commons.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * CSVFileWriterクラス
 * CSVファイルを書き込む文字ストリームクラスです。
 * @version 1.0
 * @author Mamezou
 */
public final class CSVFileWriter {

    /** CSVファイルのカンマ文字 */
    private static final String COMMA_STRING = ",";

    /** CSVファイルのファイル */
    private File csvFile = null;

    /** CSVファイルを書き込むためのWriter */
    private BufferedWriter writer = null;

    /** クォーテーション追加の可否 */
    private boolean quoteAdding = false;

    /** クォーテーション */
    private char quoteChar = '"';

    /**
     * コンストラクタ。
     * 指定されたパスを元に、Fileオブジェクトを作成して保持します。
     * @param path 読込み対象のファイルへのパス
     * @throws FileNotFoundException 指定されたファイルが見つからない場合
     */
    public CSVFileWriter(String path) throws FileNotFoundException {
        csvFile = new File(path);
    }

    /**
     * カラムの内容でCSVを上書きします。
     * @param columnsList 書き込むカラムのList
     * @throws java.io.IOException IOException
     */
    public void overwrite(List<String[]> columnsList) throws IOException {
        try {
            initWriter(false);
            writeToCsv(columnsList);
            flush();
        } finally {
            closeQuietly();
        }
    }

    /**
     * カラムの内容をCSVに追記します。
     * @param columns 追記するカラム
     * @throws IOException IOException
     */
    public void append(String[] columns) throws IOException {
        try {
            initWriter(true);
            writeToCsv(columns);
            flush();
        } finally {
            closeQuietly();
        }
    }
    /**
     * Writerを初期化します。
     * @throws IOException Writerの初期化に失敗した場合
     */
    private void initWriter(boolean append) throws IOException {
        writer = new BufferedWriter(new FileWriter(csvFile, append));
    }

    /**
     * Writerをflushします。
     * @throws IOException flushに失敗した場合
     */
    private void flush() throws IOException {
        writer.flush();
    }

    /**
     * Writerを閉じます。
     * 閉じる際に例外が発生した場合でも、例外を投げません。
     * 本来は例外ログを出力するように実装します。
     */
    private void closeQuietly() {
        if (writer == null) {
            return;
        }

        try {
            writer.close();
        } catch (IOException e) {
            // 本来はここで、例外ログ等を出力すべきです。
            // 本演習では何も処理を実施しません。
        } finally {
            writer = null;
        }
    }

    /**
     * Writerを使用してカラムのListをCSVファイルに書き込みます。
     * @param columnsList カラムのList
     * @throws IOException CSVファイルへの書き込みに失敗した場合
     */
    private void writeToCsv(List<String[]> columnsList) throws IOException {
        // 指定された文字列配列Listを順に読み込み、カンマ区切り文字列を作成
        for (String[] columns : columnsList) {
            writeToCsv(columns);
        }
    }

    /**
     * Writerを使用してカラムをCSVファイルに書き込みます。
     * @param columns カラム
     * @throws IOException CSVファイルへの書き込みに失敗した場合
     */
    private void writeToCsv(String[] columns) throws IOException {
        // カンマ区切り文字列に変換
        String line = makeLine(columns);
        // 作成した文字列を書き込み
        writer.write(line);
        // 改行コードを書き込み
        writer.newLine();
    }

    /**
     * 文字列配列を、カンマ区切りの文字列に変換します。
     * @param columns 文字列配列
     * @return カンマ区切りの文字列
     */
    private String makeLine(String[] columns) {
        // 指定された文字列が空配列の場合、から文字列を返す
        if (columns.length == 0) {
            return "";
        }

        // StringBufferを作成し、カンマ区切り文字列を書き込む
        StringBuffer lineSb = new StringBuffer();
        for (int i = 0; i < columns.length - 1; i++) {
            // 1つの文字列を書き込む（必要であればクォーテーションを設定）
            lineSb.append(makeQuoteLineIfRequired(columns[i]));
            // カンマ文字を書き込む
            lineSb.append(COMMA_STRING);
        }
        // 最後の文字列を書き込む
        lineSb.append(makeQuoteLineIfRequired(columns[columns.length - 1]));
        return lineSb.toString();
    }

    /**
     * 必要に応じてクォーテーションを設定する。
     * 具体的には、quoteAddingがtrueの場合、クォーテーションを書き込みます。
     * @param column 文字列
     * @return クォーテーション付き文字列
     */
    private String makeQuoteLineIfRequired(String column) {
        if (!quoteAdding) {
            return column;
        }
        return quoteChar + column + quoteChar;
    }

    //==================================
    // CSVFileWriterの挙動設定メソッド群
    //==================================

    /**
     * 出力される各カラムを括るか否か指定します。
     * デフォルトの括り文字は "(ダブルクォーテーション)です
     * @param isAdded true:括る false:括らない
     * @see #setQuoteChar
     */
    public void setQuoteAdding(boolean isAdded) {
        quoteAdding = isAdded;
    }

    /**
     * 各カラムを括る文字を指定します。
     * デフォルトの括り文字は "(ダブルクォーテーション)です
     * @param quoteChar 括り文字
     */
    public void setQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
    }
}
