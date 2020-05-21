/*
 * EquipmentReservationSystem.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.business;

// java.timeパッケージのインポート
//  LocalDate, LocalDateTime (日時関連)


// java.utilパッケージのインポート
//  List (コレクション関連)


// com.mamezou.ers.exceptionsパッケージのインポート
//  DataFormatException, DataIOException, DataValueException

/**
 * 施設予約システムクラス
 * @author Mamezou
 */
public class EquipmentReservationSystem {

    /** 施設管理クラスのインスタンスへの参照を格納する変数 */
    private EquipmentMaster equipmentMaster;

    /** 予約管理クラスのインスタンスへの参照を格納する変数 */
    private ReservationMaster reservationMaster;

    /** ユーザアカウント管理クラスのインスタンスへの参照を格納する変数 */
    private UserAccountMaster userAccountMaster;

    /**
     * コンストラクタ。
     */
    public EquipmentReservationSystem() {
        setupDefaultMaster();
    }

    /**
     * コンストラクタです。
     * @param equipmentMaster EquipmentMaster
     * @param reservationMaster ReservationMaster
     * @param userAccountMaster UserAccountMaster
     */
    public EquipmentReservationSystem(
                EquipmentMaster equipmentMaster,
                ReservationMaster reservationMaster,
                UserAccountMaster userAccountMaster) {
        this.equipmentMaster = equipmentMaster;
        this.reservationMaster = reservationMaster;
        this.userAccountMaster = userAccountMaster;

        setupDefaultMaster();
    }

    /**
     * Masterのインスタンスがnullの場合、デフォルトのMasterを設定します。
     */
    private void setupDefaultMaster() {
        if (this.userAccountMaster == null) {
            // ユーザアカウント管理クラスのインスタンスを生成し、属性userAccountMasterに代入する。
            this.userAccountMaster = new UserAccountMaster();
        }
        if (this.equipmentMaster == null) {
            // 施設管理クラスのインスタンスを生成し、属性equipmentMasterに代入する。
            this.equipmentMaster = new EquipmentMaster();
        }
        if (this.reservationMaster == null) {
            // 予約管理クラスのインスタンスを生成し、属性reserveMasterに代入する。
            this.reservationMaster = new ReservationMaster();
        }
    }
    /**
     * 検索条件を満たすユーザを検索する。<br>
     * 検索条件のログイン名・パスワードと同じログイン名・パスワードをもつ
     * ユーザを検索する。
     * @param  loginName           ログイン名
     * @param  password            パスワード
     * @return ユーザアカウント
     * @throws DataIOException     ユーザアカウント管理ファイルの読み込みに
     *                              失敗した場合
     * @throws DataValueException  ユーザアカウントが検索できなかった場合
     * @throws DataFormatException ユーザアカウント管理ファイルのデータに
     *                              問題がある場合
     */
/* UC01で実装します
    public UserAccount findUserAccount(String loginName, String password)
            throws DataIOException, DataValueException, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・ユーザアカウント管理クラスに検索処理を任せる。
        // ・該当するユーザアカウントが存在しない場合は、例外をスローする。
        //======================================================================

        // ユーザアカウント管理クラスを使い、引数のログイン名、パスワードに
        // 合致するユーザアカウントインスタンスの参照を取得する。
        UserAccount foundUserAccount = ■■■■■■■■■.findUserAccount(loginName, password);

        if (■■■■■■■■ != null) {
        // ユーザアカウントインスタンスが取得できた場合、そのインスタンスを返す。
            return foundUserAccount;

        } else {
            // ユーザアカウントが取得できなかった場合
            // DataValueExceptionを該当するメッセージで初期化し、スローする。
            throw new DataValueException("ログイン名もしくはパスワードが異なります。");
        }
    }
*/

    /**
     * 検索条件を満たすユーザを検索する。<br>
     * 検索条件のユーザアカウント番号と同じユーザアカウント番号をもつ
     * ユーザを検索する。見つからない場合、nullを返す。
     * @param targetUserAccountNumber 検索条件となるユーザアカウント番号
     * @return 見つかったユーザアカウント。見つからない場合 null。
     * @throws DataIOException
     *          ユーザアカウント管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException
     *          ユーザアカウント管理ファイルのデータに問題がある場合
     */
/* UC02で実装します
    public UserAccount findUserAccount(int targetUserAccountNumber)
            ■■■ ■■■■■■■, ■■■■■■■■■■ {

        // ユーザアカウント管理クラスを使って、引数のユーザアカウント番号に合致するユーザアカウントを検索する。
        UserAccount foundAccount = ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■;

        // 取得したユーザアカウントを返す
        return foundAccount;
    }
*/

    /**
     * 検索条件を満たす予約を検索し、結果をListとして返す。<br>
     * 引数の施設番号と利用日付に合致する予約情報を検索し、検索結果を
     * Listとして返す。Listの要素は合致した予約。<br>
     * @param targetEquipmentNumber    検索条件となる施設番号
     * @param targetDate           検索条件となる利用日付
     * @return 検索結果のList。各要素の型はReservation型。
     * @throws DataIOException
     *          予約管理ファイル、ユーザアカウント管理ファイル、施設管理
     *          ファイルの何れかの読み込みに失敗した場合
     * @throws DataValueException
     *          該当する予約が存在しない場合
     * @throws DataFormatException
     *          予約管理ファイル、ユーザアカウント管理ファイル、施設管理
     *          ファイルの何れかのデータに問題がある場合
     */
/* UC02で実装します
    public List<Reservation> findReservation(int targetEquipmentNumber, LocalDate targetDate)
            throws DataIOException, DataValueException, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・検索した予約に、施設と予約者をセットする。
        //   これにより、予約に施設と予約者の情報を関連付ける。
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        // ・予約が検索できない場合は、例外をスローする。
        //======================================================================

        // 予約管理クラスを使い、引数の施設番号と利用日付に合致する予約インスタンスの参照を、Listで取得する
        ■■■■■■■■ foundReservationList
                            = reservationMaster.■■■■■■■■(
                                                    ■■■■■■■■, ■■■■■■■■■);

        // 検索できた予約の件数分繰り返す。
        for (■■■■■ foundReservation : foundReservationList) {

            // 施設番号の取得
            int foundEquipmentNumber = foundReservation.getEquipmentNumber();

            // 予約インスタンスから取得した施設番号をもとに、施設インスタンスを取得する。
            Equipment foundEquipment = ■■■■■■■■■■■■■■■■■■;

            // 取得した施設インスタンスを、予約インスタンスに設定する
            foundReservation.setEquipment(■■■■■■■);

            // 予約者のユーザアカウント番号の取得
            int foundUserAccountNumber = ■■■■■■■■■■■■■■■■■■■■;

            // 予約インスタンスから取得した予約者のユーザアカウント番号をもとに、ユーザアカウントインスタンスを取得する。
            UserAccount foundReservedUser = ■■■■■■■■■■■■■■■■■■■■;

            // ユーザアカウントインスタンスを予約インスタンスに設定する
            foundReservation.setUserAccount(■■■■■■■■■);
        }

        if (foundReservationList.size() > 0) {
            // 合致する予約がある場合
            // 予約インスタンスの参照を格納したListを返す。
            return foundReservationList;

        } else {
            // 検索条件を満たす予約が存在しない場合
            // DataValueExceptionを該当するメッセージで初期化し、スローする。
            throw new DataValueException("該当する予約がありません。");

        }
    }
*/

    /**
     * システムに登録されている全ての施設を要素として持つリストを返します。
     * 施設が1件も登録されていない場合、空のリストを返します。
     * @return                      全施設情報(要素はEquipment型)
     * @throws DataIOException      施設管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException  施設管理ファイルのデータに問題がある場合
     */
/* UC02で実装します
    public List<Equipment> getAllEquipments() ■■■ ■■■■■■■■, ■■■■■■■■■■ {

        // 施設管理クラスを使って、全ての施設インスタンスの参照をListで取得する
        ■■■■■■■■ equipments = ■■■■■■■■■■■■■■■■■;

        // 取得した全ての施設インスタンスの参照のListを返す
        return equipments;
    }
*/

    /**
     * 施設を検索し、施設インスタンスの参照を返す。<br>
     * 引数の施設番号に合致する施設インスタンスの参照を返す。
     * @param  targetEquipmentNumber 施設番号
     * @return 施設インスタンス。該当する施設が存在しない場合 null。
     * @throws DataIOException       施設管理ファイルの読み込みに失敗した場合
     * @throws DataFormatException   施設管理ファイルのデータに問題がある場合
     */
/* UC02で実装します
    public Equipment findEquipment(int targetEquipmentNumber)
                        ■■■ ■■■■■■■■, ■■■■■■■■■■ {

        // 施設管理クラスを使って、検索条件の施設番号に合致する施設インスタンスを取得する
        Equipment foundEquipment
                    = ■■■■■■■■■■■■■■■■■■■■■■■■■■;

        // 取得した施設インスタンスの参照を返す
        return foundEquipment;
    }
*/

    /**
     * 施設を予約する。<br>
     * 引数の施設番号、利用開始日時、利用終了日時、利用目的、
     * 予約者(予約を行うユーザアカウント)で予約を登録し、
     * 登録された予約を表す予約インスタンスを返す。
     * @param equipmentNumber    予約する施設の施設番号
     * @param startDateTime      予約する施設の利用開始日時
     * @param endDateTime        予約する施設の利用終了日時
     * @param purpose            予約する施設の利用目的
     * @param subscriber         予約者(予約を行うユーザアカウント)
     * @return                   登録された予約
     * @throws DataIOException
     *          予約管理ファイル、ユーザアカウント管理ファイル、施設管理ファイルの
     *          何れかの読み込みに失敗した場合。<br>
     *          または予約管理ファイルの書き込みに失敗した場合
     * @throws DataValueException
     *          引数の施設番号に該当する施設が存在しない場合<br>
     *          または既に予約が登録されている場合
     * @throws DataFormatException
     *          予約管理ファイル、ユーザアカウント管理ファイル、施設管理ファイルの
     *          データに問題がある場合
     */
/* UC03で実装します
    public Reservation addReservation(int equipmentNumber,
                                        LocalDateTime startDateTime, LocalDateTime endDateTime,
                                        String purpose, UserAccount subscriber)
                throws DataIOException, DataValueException, DataFormatException {

        //========================================================================
        // 実装のヒント!
        // ・指定された施設番号の施設が、存在するか検索して確認する。施設が存在しない場合は、例外をスローする。
        // ・指定された施設は、指定の日時に予約が入っていないか検索して確認する。
        //   既に予約がある場合は、例外をスローする。
        // ・予約登録後、予約インスタンスに施設インスタンスと予約者のユーザアカウントインスタンスを設定する。
        //========================================================================

        // 施設管理クラスを使って、引数の施設番号に合致する施設インスタンスの参照を取得する。
        Equipment foundEquipment = ■■■■■■■■■■■■■■■■■■■■■■■;

        // 入力された施設番号が存在する場合の処理
        if (foundEquipment != null) {

            // 予約管理クラスを使って、引数の施設番号の施設が、引数の利用開始日時から利用終了日時の間で
            // 予約登録されている場合、その予約を取得する。
            ■■■■■ ■■■■■■■■■
                            = ■■■■■■■■■■■■■■■■■(
                                    ■■■■■■■, ■■■■■■■, ■■■■■■■);

            if (■■■■■■■■■■■■) {
                // 予約がなかった場合の処理

                // 予約者のユーザアカウント番号を取得する
                int loginUserNumber = ■■■■■■■■■■■;

                // 予約管理クラスを使い、引数の情報で予約を登録する
                Reservation newReservation = ■■■■■■■■■■■■■■■■(
                                                ■■■■■■■■, ■■■■■■■■,
                                                ■■■■■■■, ■■■■■■, ■■■■);

                // 登録した予約に施設を関連付ける(設定する)
                ■■■■■■■■■■■■■■■■■■■■■■;

                // 登録した予約に予約者(ユーザアカウント)を関連付ける(設定する)
                ■■■■■■■■■■■■■■■■■■■■■■;

                // 取得した予約インスタンスの参照を返す。
                return newReservation;

            } else {
                // すでに予約があった場合
                // DataValueExceptionを該当するメッセージで初期化し、スローする。
                ■■■ new ■■■■■■■■■(■■■■■■■■■■);

            }
        } else {
            // 入力された施設番号が存在しない場合、DataValueExceptionをスローする
            ■■■ new ■■■■■■■■■(■■■■■■■■■■■■■■■■);

        }
    }
*/

    /**
     * 施設を登録する。<br>
     * 引数の施設名、定員数、内線番号で施設を登録し、施設番号を返す。
     * @param newEquipmentName      登録する施設名
     * @param newCapacity           登録する定員数
     * @param newExtensionNumber    登録する内線番号
     * @return                      登録された施設
     * @throws DataIOException      施設管理ファイルの書き込みに失敗した場合
     * @throws DataValueException   施設が既に登録されている場合
     * @throws DataFormatException  施設管理ファイルのデータに問題がある場合
     */
/* UC04で実装します
    public Equipment addEquipment(
                        String newEquipmentName, int newCapacity, String newExtensionNumber)
                    throws DataIOException, DataValueException, DataFormatException {
    }
*/

    /**
     * ユーザアカウントを登録する。<br>
     * 指定されたログイン名、パスワード、実名、内線番号、
     * 部署名、権限でユーザアカウントを登録する。
     *
     * @param loginName            登録するユーザアカウント
     * @param password             登録するパスワード
     * @param realName             登録するユーザの実名
     * @param extensionNumber      登録する内線番号
     * @param divisionName         登録する部署名
     * @param authority            登録する権限
     *
     * @return                     登録されたユーザアカウント
     *
     * @throws DataIOException     ユーザアカウント管理ファイルの書き込みに失敗した場合
     * @throws DataValueException  ユーザアカウントが既に登録されている場合
     * @throws DataFormatException ユーザアカウント管理ファイルのデータに問題がある場合
     */
/* UC05で実装します。
    public UserAccount addUserAccount(
                            String loginName, String password,
                            String realName, String extensionNumber,
                            String divisionName, int authority)
                ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ {
    }
*/
}
