/*
 * BaseDataAccessor.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.dataaccess;

// commons.io パッケージに含まれるクラスのインポート
//   CSVFileReader, CSVFileWriter
import java.io.IOException;
import java.util.List;

import com.mamezou.commons.io.CSVFileReader;
import com.mamezou.commons.io.CSVFileWriter;

/**
 * 基本情報操作クラス<br>
 * 基本的なデータアクセス処理メソッドを提供する。
 * このクラスの全てメソッドは同一パッケージ内のクラス
 * からのみアクセス可能です。
 * @author Mamezou
 */
class BaseDataAccessor {

    /** アクセスするファイルへのパス */
    private String filePath;

    /**
     * コンストラクタ
     * @param csvFilePath ファイルパス
     */
    BaseDataAccessor(String csvFilePath) {
        setFilePath(csvFilePath);
    }

    /**
     * アクセス対象ファイルへのパスを返す
     * @return アクセス対象ファイルへのパス
     */
    String getFilePath() {
        return filePath;
    }

    /**
     * アクセス対象ファイルへのパスを設定する。
     * @param csvFilePath アクセス対象ファイルへのパス
     */
    void setFilePath(String csvFilePath) {
        filePath = csvFilePath;
    }

    /**
     * 永続化された情報を取得し、読み込んだ情報の件数を
     * 返します。<br>
     * 永続化された情報1件はString[]として復元され、Listの
     * 1要素として格納されます。
     * @param  dataList  復元された情報(Stringの配列)を格納するList
     * @return           読み込んだユーザアカウント情報の件数
     * @throws IOException
     *          永続化された情報の復元に失敗した場合
     */
    int load(List<String[]> dataList) throws IOException {
        // CSVFileReaderを作成
        CSVFileReader fileReader = new CSVFileReader(filePath);

        // CSVFileReaderからCSVに登録されているデータを取得
        List<String[]> readDataList = fileReader.read();

        // 引数に渡されたListに格納
        dataList.addAll(readDataList);

        // CSVから読み込んだ件数を返す
        return readDataList.size();
    }

    /**
     * 文字列型の配列情報をCSVファイルに追加保存する。<br>
     * 引数のString型配列に格納されている情報を
     * ファイル(getFilePath()で得られるパス)に追加保存する。
     * @param  targetData 保存対象の情報(Stringの配列)
     * @throws IOException
     *          指定された情報の永続化に失敗した場合
     */
/* UC03で実装します
    void save(String[] targetData) throws IOException {
        // CSVFileWriterを作成
        CSVFileWriter writer = ■■■■■■■■■■■■■■;

        // CSVFileWriterでCSVにデータを追記
        ■■■■■■■■■■■■■;
    }
*/
}
