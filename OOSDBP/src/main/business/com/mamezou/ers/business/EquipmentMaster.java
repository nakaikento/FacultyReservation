/*
 * EquipmentMaster.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.business;

// com.mamezou.ers.dataaccessパッケージのインポート
//     EquipmentDataAccessor
import com.mamezou.ers.dataaccess.EquipmentDataAccessor;
// com.mamezou.ers.exceptionsパッケージのインポート
//     DataFormatException, DataIOException
import com.mamezou.ers.exceptions.DataFormatException;

/**
 * 施設管理クラス<br>
 * 施設データと施設インスタンスを管理するクラス。
 * 施設の登録と検索に関するメソッドを持つ。
 * @author Mamezou
 */
public class EquipmentMaster {

    /** 施設情報アクセスクラスのインスタンスの参照を格納する変数 */
    private EquipmentDataAccessor equipmentDataAccessor;

    /**
     * デフォルトコンストラクタ。
     * 属性equipmentDataAccessorを初期化する。
     */
    public EquipmentMaster() {
        // EquipmentDataAccessorインスタンスを生成し、
        // 参照をequipmentDataAccessorに代入する。
        equipmentDataAccessor = new EquipmentDataAccessor();
    }

    /**
     * コンストラクタ。
     * @param dataAccessor 使用するEquipmentDataAccessor
     */
    public EquipmentMaster(EquipmentDataAccessor dataAccessor) {
        equipmentDataAccessor = dataAccessor;
    }

    /**
     * String[]型として表される施設情報をEquipment型に変換する。<br>
     * @param equipmentData 変換対象となる施設情報
     * @return 引数の施設情報と同じデータをもつ施設インスタンス
     * @throws DataFormatException
     *          指定された施設情報に不正なデータが含まれている場合。
     */
    private Equipment convertToEquipment(String[] equipmentData)
            throws DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・配列の要素にアクセスにおいてArrayIndexOutOfBoundsExceptionがスローされる可能性がある。
        // ・文字列から数値への変換においてNumberFormatExceptionがスローされる可能性がある。
        //======================================================================
        try {
            // 取得した施設情報から施設番号を取り出し、数値に変換する。
            int number = Integer.parseInt(equipmentData[0]);

            // 取得した施設情報から、施設名を取り出す。
            String equipmentName = equipmentData[1];

            // 取得した施設情報から定員数を取り出し、数値に変換する。
            int capacity = Integer.parseInt(equipmentData[2]);

            // 取得した施設情報から、内線番号を取り出す。
            String extensionNumber = equipmentData[3];

            // 取得した施設情報をもとに、施設インスタンスを生成する
            Equipment anEquipment = new Equipment(number, equipmentName,
                    capacity, extensionNumber);

            return anEquipment;

        } catch (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException("施設管理ファイルの構成が不正です。", e);

        } catch (NumberFormatException e) {
            // NumberFormatExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException(
                        "施設管理ファイルのデータ(施設番号または定員数)の"
                      + "数値変換に失敗しました。", e);
        }
    }

    /**
     * 登録されている全ての施設を取得します。<br>
     * システムに登録されている全ての施設を要素として持つリストを返します。
     * 施設が1件も登録されていない場合、空のリストを返します。
     *
     * @return 全ての施設情報。Listの要素の型はEquipment型。
     *          1件も登録されていない場合、空(要素数0)のList。
     * @throws DataIOException     施設管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException 施設管理ファイルのデータに問題がある場合
     */
/* UC02で実装します
    public List<Equipment> getAllEquipments() throws ■■■■■■■■, ■■■■■■■■■■ {

        //======================================================================
        // 実装のヒント!
        // ・施設情報アクセスクラスを使い、すべての施設情報を取得する。
        // ・取得した施設情報(String[]型)を施設インスタンス(Equipment型)に変換する必要がある。
        // ・Listに対して繰り返しを行う際は 拡張for文 を用いる。
        //======================================================================

        // 取得した施設インスタンスの参照は、Listに格納して返す。
        // そのため、施設インスタンスの参照を保持するArrayListを用意する。
        List<Equipment> equipments = new ArrayList<Equipment>();

        // 施設情報アクセスクラスを使って、全ての施設情報をListとして取得します。
        List<String[]> equipmentDataList = equipmentDataAccessor.getAllEquipmentData();

        // 取得した全ての施設情報から、施設インスタンスを生成する。
        // 生成した施設インスタンスの参照は、用意したArrayListに格納する。
        for (String[] equipmentData : ■■■■■■■■■) {

            // String[]で表される施設情報を、Equipmentインスタンスに変換する
            ■■■■■ anEquipment = ■■■■■■■■■■■■■■■■■;

            // 施設をArrayListに追加する。
            equipments.add(anEquipment);

        }

        return ■■■■■;

    }
*/

    /**
     * 指定された検索条件を満たす施設を検索する。<br>
     * 検索条件の施設番号と同じ施設番号を持つ施設を検索します。
     *
     * @param targetEquipmentNumber  検索条件の施設番号
     * @return 検索条件を満たす施設インスタンス。
     *          検索条件を満たす施設がない場合 null。
     * @throws DataIOException 施設管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException 施設管理ファイルのデータに問題がある場合
     */
/* UC02で実装します
    public Equipment findEquipment(int targetEquipmentNumber)
            throws DataIOException, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・施設情報アクセスクラスから戻ってくる施設情報は String[]型である。
        // ・取得した施設情報(String[]型)を施設インスタンス(Equipment型)に変換する必要がある。
        //======================================================================

        // 施設情報アクセスクラスを使って、検索条件の施設番号に合致する施設情報をString[]型として取得する
        String[] equipmentData = ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■;

        if (■■■■■■■ != null) {
            // 合致するデータがある場合の処理
            // String[]で表される施設情報を、Equipmentインスタンスに変換する
            Equipment foundEquipment = ■■■■■■■■■■■■■■■■■;

            return foundEquipment;

        } else {
            // 合致するデータがない場合、nullを返す
            ■■■ ■■;
        }
    }
*/

    /**
     * 指定された検索条件を満たす施設を検索する。<br>
     * 検索条件の施設名に一致する施設を検索します。
     *
     * @param targetEquipmentName           検索条件の施設名
     * @return 検索条件を満たす施設インスタンス。
     *          検索条件を満たす施設がない場合 null。
     * @throws DataIOException 施設管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException 施設管理ファイルのデータに問題がある場合
     */
/* UC04で実装します
    public Equipment findEquipment(String targetEquipmentName)
            throws DataIOException, DataFormatException {
    }
*/

    /**
     * 施設を登録する。<br>
     * 引数の施設名、定員数、内線番号で施設情報を登録する。
     *
     * @param equipmentName    施設名
     * @param capacity           定員数
     * @param extensionNumber    内線番号
     * @return                      施設番号
     * @throws DataFormatException  施設管理ファイルのデータに問題がある場合
     * @throws DataIOException      施設管理ファイルの書き込みに失敗した場合
     */
/* UC04で実装します
    public Equipment addEquipment(String equipmentName,
                                    int capacity, String extensionNumber)
                                            throws DataFormatException, DataIOException {
    }
*/
}
