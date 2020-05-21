/*
 * EquipmentDataAccessor.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.dataaccess;

// exceptions パッケージのインポート
// DataFormatException, DataIOException
import com.mamezou.ers.exceptions.DataFormatException;
import com.mamezou.ers.exceptions.DataIOException;

// java.io パッケージのインポート
//    IOException
import java.io.IOException;

// java.util パッケージのインポート
//    ArrayList, List.  (コレクション関連)
import java.util.ArrayList;
import java.util.List;

/**
 * 施設情報操作クラス
 * @author Mamezou
 */
public class EquipmentDataAccessor {

    /** 施設管理ファイルのパス（デフォルト） */
    private static final String DEFAULT_FILE_PATH = "equipment.csv";

    /** 基本情報操作クラスのインスタンスへの参照を保持する */
    private BaseDataAccessor baseAccessor;

    /**
     * コンストラクタです。
     */
    public EquipmentDataAccessor() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * コンストラクタです。
     * @param filePath 施設管理ファイルのパス
     */
    public EquipmentDataAccessor(String filePath) {
        baseAccessor = new BaseDataAccessor(filePath);
    }

    /**
     * 永続化された施設情報を復元し、リストとして返します。<br>
     * 1件の施設情報はStringの配列として復元されます。このString[]を
     * 要素として持つListが永続化されていた全ての施設情報となります。<br>
     * @return 復元された施設情報のList。Listの要素の型は全てString[]。
     *          1つの要素が1件の施設情報を表します。
     * @throws DataIOException 永続化された施設情報の復元に失敗した場合
     */
/* UC02で実装します
    ■■■■ ■■■■■■■ load() ■■■ DataIOException {

        ■■ {
            List<String[]> equipmentDataList = new ArrayList<String[]>();
            ■■■■■■■■■■■■■■■■■■■;
            return equipmentDataList;
        } ■■■ (■■■■■■ e) {
            ■■■ new DataIOException("施設管理ファイルの読み込みに失敗しました。", e);
        }
    }
*/

    /**
     * 登録されている全ての施設情報をListとして返します。<br>
     * @return 復元された施設情報のList。Listの要素の型は全てString[]。
     *          1つの要素が1件の施設情報を表します。
     * @throws DataIOException 永続化された施設情報の復元に失敗した場合
     */
/* UC02で実装します
    public List<String[]> getAllEquipmentData() throws DataIOException {
        return ■■■;
    }
*/

    /**
     * 施設を検索し、施設の情報をString型の配列の参照で返す。<br><br>
     * 引数の施設番号に合致する施設の情報を、施設管理ファイルから検索し
     * String型の配列で返す。なければnullを返す
     * @param  targetEquipmentNumber  検索条件となる施設番号
     * @return 検索条件を満たす施設の情報
     * @throws DataIOException
     *          永続化された施設情報の復元に失敗した場合
     * @throws DataFormatException
     *          永続化された施設情報のデータが不正である場合。(本来、数字で
     *          あるべき箇所にアルファベットが記述されている、など)
     */
/* UC02で実装します
    public String[] findEquipment(int targetEquipmentNumber)
             ■■■ ■■■■■■■■, DataFormatException {

        //=====================================================================
        // 実装のヒント!
        // ・Listに対して繰り返しを行う際は、拡張for文を用いる。
        // ・配列の要素にアクセスする際には、ArrayIndexOutOfBoundsException がスローされる可能性がある。
        // ・文字列を数値に変換する際には、NumberFormatException がスローされる可能性がある。
        //=====================================================================

        ■■ {
            // 施設情報管理ファイルから、登録されている全施設情報を取得する。
            List<String[]> equipmentDataList = ■■■■;

            // 引数の施設番号に合致する施設情報を検索する
            for (■■■■ ■■■■■■■ : ■■■■■■■■■) {

                // 取得した施設情報から施設番号を取り出し、数値に変換する。
                int equipmentNumber = Integer.parseInt(■■■■■■■■■);

                if (■■■■■■■■ == ■■■■■■■■■■■) {
                    // 検索条件の施設番号が、取得した施設情報の施設番号と合致した場合、その施設情報を返す。
                    return equipmentData;
                }
            }

            // 検索条件の施設番号に合致する施設が見つからない場合、nullを返す。
            return null;

        } ■■■ (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new ■■■■■■■■■■(
                        "施設管理ファイルのデータ(施設番号の位置)が不正です。", e);
        } ■■■ (NumberFormatException e) {
            // NumberFormatExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new ■■■■■■■■■■(
                        "施設管理ファイルのデータ(施設番号)の数値変換に失敗しました。", e);
        }
    }
*/

    /**
     * 施設を検索し、施設情報をString型の配列で返す。<br>
     * 引数の施設名に合致する施設の情報を施設管理ファイルから検索し、
     * String型の配列で返す。なければnullを返す。
     * @param targetEquipmentName    検索条件となる施設名
     * @return 引数の施設名に合致する施設情報
     * @throws DataIOException
     *          施設管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException
     *          施設管理ファイルのデータの施設名に問題があった場合。
     */
/* UC04で実装します
    public String[] findEquipment(String targetEquipmentName)
            throws DataIOException, DataFormatException {
    }
*/

    /**
     * 新しく付与するべき施設番号を返す。<br>
     * 施設管理ファイルから、施設情報を読み込み、施設の件数+1の値を返す。
     * 施設番号は1から始まる。
     * @return             登録されている施設の件数+1の値
     * @throws DataIOException 施設管理ファイルの読み込みに失敗した場合
     */
/* UC04で実装します
    public int getNextSeq() throws DataIOException {
    }
*/

    /**
     * 施設情報を登録する。<br>
     * 引数のString型配列に格納されている施設情報を施設管理ファイルに登録する。
     * @param  equipmentData  登録対象の施設情報
     * @throws DataIOException     施設管理ファイルの書き込みに失敗した場合
     */
/* UC04で実装します
    private void save(String[] equipmentData) throws DataIOException {
    }
*/

    /**
     * 施設情報を永続化する。<br>
     * @param  equipmentData  永続化する施設情報
     * @throws DataIOException 施設情報の永続化に失敗した場合
     */
/* UC04で実装します 
    public void addEquipment(String[] equipmentData) throws ■■■■■■■■■ {
    }
*/
}
