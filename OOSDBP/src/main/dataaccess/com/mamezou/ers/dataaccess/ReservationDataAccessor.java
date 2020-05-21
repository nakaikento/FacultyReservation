/*
 * ReservationDataAccessor.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

// パッケージ宣言
package com.mamezou.ers.dataaccess;

/**
 * 予約情報操作クラス
 * @author Mamezou
 */
public class ReservationDataAccessor {

    /** 予約管理ファイルのパス（デフォルト） */
    private static final String DEFAULT_FILE_PATH = "reservation.csv";

    /** 基本情報操作クラスのインスタンスへの参照を保持する */
    private BaseDataAccessor baseAccessor;

    /**
     * コンストラクタです。
     */
    public ReservationDataAccessor() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * コンストラクタです。
     * @param filePath 予約管理ファイルのパス
     */
    public ReservationDataAccessor(String filePath) {
        baseAccessor = new BaseDataAccessor(filePath);
    }

    /**
     * 永続化された予約情報を復元し、リストとして返します。<br>
     * 1件の予約情報はStringの配列として復元されます。このString[]を
     * 要素として持つListが永続化されていた全ての予約情報となります。
     *
     * @return 復元された予約情報のList。Listの要素の型は全てString[]。
     *          1つの要素が1件の予約情報を表します。
     * @throws DataIOException 永続化された予約情報の復元に失敗した場合
     */
/* UC02で実装します
    private List<String[]> load() throws ■■■■■■■■ {
        try {
            List<String[]> reservationDataList = new ArrayList<String[]>();
            ■■■■■■.load(reservationDataList);
            return reservationDataList;
        } catch (■■■■■■ e) {
            throw new ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■;
        }
    }
*/

    /**
     * 登録済の予約情報から検索条件を満たす予約情報を抽出します。<br>
     * 検索条件を満たす予約情報の集合をListとして返します。検索条件を
     * 満たす予約情報が存在しない場合、空のListを返します。
     *
     * @param targetEquipmentNumber       検索条件となる施設番号
     * @param targetDate       検索条件となる利用日。
     * @return 予約情報のリスト。該当する予約情報がない場合、空のリストを返す。
     *          リストの要素の型はString[]。
     * @throws DataIOException      永続化された予約情報の復元に失敗した場合
     * @throws DataFormatException
     *          復元された予約情報の施設番号または利用開始日時が規定の書式で
     *          表現されていない場合。(施設番号に英字が含まれる、など)
     */
/* UC02で実装します
    public List<String[]> findReservation(int targetEquipmentNumber, LocalDate targetDate)
            throws DataIOException, DataFormatException {

        //=====================================================================
        // 実装のヒント!
        // ・検索した結果の予約情報を格納していくArrayListを準備する。
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        // ・文字列からローカル日時への変換にはDateUtilsクラスを用いる。文字列の書式は YYYY/MM/DD。
        //  変換の際には、DateTimeParseException がスローされる可能性がある。
        // ・ローカル日付の比較には、LocalDateクラスのisEqualを使う。
        // ・配列の要素にアクセスする際には、ArrayIndexOutOfBoundsException がスローされる可能性がある。
        // ・文字列を数値に変換する際には、NumberFormatException がスローされる可能性がある。
        //======================================================================

        ■■ {

            // 検索結果の予約情報を格納するためのListを用意する。
            List<String[]> foundReservationList = ■■■■■■■■■■■■;

            // 予約情報管理ファイルから、登録されている全予約情報を取得する。
            List<String[]> reservationDataList = load();

            // 条件にあう予約情報を検索する
            //   登録済の予約情報の件数だけ繰り返す
            for (■■■■ ■■■■■■■ : ■■■■■■■■■■) {

                // 予約情報から利用開始日時を取り出し、LocalDateTime型に変換する。
                // 変換した利用開始日時から日付部分のみ抽出する
                LocalDateTime startDateTime = DateUtils.■■■■■■■■■■(reservationData[1]);
				LocalDate startDate = startDateTime.■■■■■■■;

                // 取得した予約情報から施設番号を取り出し、数値に変換する。
                int equipmentNumber = Integer.parseInt(reservationData[4]);

                // 検索条件(日付・施設番号）と復元された予約情報(日付・施設番号）が合致する場合、
                // 検索結果のListに予約情報を格納する。
                if (■■■■■■■■■■■■■■■■■
                        && equipmentNumber == targetEquipmentNumber) {

                    // 予約情報をListに追加
                    ■■■■■■■■■■■■■■■■■■■■■;

                }
            }

            // 検索結果の予約情報を格納したListを返す。
            return ■■■■■■■■■■;

        } ■■■ (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException(
                        "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■", ■);
        } ■■■ (DateTimeParseException e) {
            // DateTimeParseExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException(
                        "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■", ■);

        } ■■■ (NumberFormatException e) {
            // NumberFormatExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            ■■■ new DataFormatException(
                        "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■", ■);

        }
    }
*/

    /**
     * 登録済の予約情報から検索条件を満たす予約情報を抽出します。<br>
     * 抽出される予約情報は、検索条件の施設番号と一致し、かつ、検索条件の
     * 利用開始日時～利用終了日時の間に利用時間が重なっている予約情報です。<br>
     * 検索条件を満たす予約情報をString[]として返します。検索条件を
     * 満たす予約情報が存在しない場合、nullを返します。
     *
     * @param  targetEquipmentNumber 検索条件となる施設番号
     * @param  targeStartDateTime    検索条件となる利用開始日時(YYYY/MM/DD hh:mm)
     * @param  targetEndDateTime     検索条件となる利用終了日時(YYYY/MM/DD hh:mm)
     * @return 検索条件を満たす予約情報。該当する予約情報がない場合 null。
     * @throws DataIOException      永続化された予約情報の復元に失敗した場合
     * @throws DataFormatException
     *          復元された予約情報の施設番号または利用開始日時が規定の書式で
     *          表現されていない場合。(施設番号に英字が含まれる、など)
     */
/* UC03で実装します
    public String[] findReservation(int targetEquipmentNumber,
            LocalDateTime targeStartDateTime, LocalDateTime targetEndDateTime) throws DataIOException,
            DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        // ・文字列からローカル日時への変換にはDateUtilsクラスを用いる。
        // ・配列の要素にアクセスする際には、ArrayIndexOutOfBoundsException がスローされる可能性がある。
        // ・文字列からDate型に変換する際には、ParseException がスローされる可能性がある。
        // ・文字列を数値に変換する際には、NumberFormatException がスローされる可能性がある。
        //======================================================================

        ■■ {
            // 予約情報管理ファイルから、登録されている
            // 全予約情報を取得する。
            List<String[]> reservationDataList = ■■■;
            // 条件にあう予約情報を検索する
            //   登録済の予約情報の件数だけ繰り返す
            for (■■■■ ■■■■■■■■ : ■■■■■■■■■■) {

                // 予約情報から施設番号を取り出し、整数(int)型に変換する。
                int equipmentNumber = ■■■■■■■■■■■■■■■■■■;

                // 検索条件の施設番号と、予約情報の施設番号が合致する場合、
                // 利用時間が重なっているかを確認する。
                if (■■■■■■■■■■■■■■■■■■■■) {

                    // 取得した予約情報から利用開始日時を取り出し、LocalDateTime型に変換する。
                    LocalDateTime startDateTime =
                            ■■■■■■■■■■■■■■■■■■■■■■■■■■■■;

                    // 取得した予約情報から利用終了日時を取り出し、LocalDateTime型に変換する。
                    LocalDateTime endDateTime =
                            ■■■■■■■■■■■■■■■■■■■■■■■■■■■■;

                    // 検索条件の利用時間帯と、取得した予約情報の利用時間帯が
                    // 重なっている場合、検索結果として取得した予約情報を返す。
                    boolean isOverlapped = ■■■■■■■■■■■■■(
                            startDateTime, endDateTime, targeStartDateTime, targetEndDateTime);
                    if (■■■■■■) {
                        return ■■■■■■■■;
                    }
                }
            }

            // 検索条件を満たす予約情報が存在しない場合、nullを返す。
            return ■■;

        } ■■■ (■■■■■■■■■■■■■■■ ■) {
            // ArrayIndexOutOfBoundsExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException(
                        "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■", ■);
        } ■■■ (■■■■■■■ ■) {
            // DateTimeParseExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException(
                        "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■", ■);

        } ■■■ (■■■■■■■■■■■ ■) {
            // NumberFormatExceptionの捕捉
            // DataFormatExceptionを該当するメッセージで初期化し、スローする。
            throw new DataFormatException(
                        "■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■", ■);

        }
    }
*/

    /**
     * 登録済の予約情報から検索条件を満たす予約情報を抽出します。<br>
     * 抽出される予約情報は、検索条件の施設番号と一致し、かつ、検索条件の
     * 利用開始日時～利用終了日時の間に利用時間が重なっている予約情報です。<br>
     * 検索条件を満たす予約情報をString[]として返します。検索条件を
     * 満たす予約情報が存在しない場合、nullを返します。
     * @param  targetReservationNumber 検索条件となる予約番号
     * @return 検索条件を満たす予約情報。該当する予約情報がない場合 null。
     * @throws DataIOException      永続化された予約情報の復元に失敗した場合
     * @throws DataFormatException
     *          復元された予約情報の施設番号または利用開始日時が規定の書式で
     *          表現されていない場合。(施設番号に英字が含まれる、など)
     */
/* UC03で実装します
    public String[] findReservation(int targetReservationNumber)
            throws DataIOException, DataFormatException {

        //======================================================================
        // 実装のヒント!
        // ・Listに対して、繰り返しを行う際は、拡張for文を用いる。
        // ・配列の要素にアクセスする際には、ArrayIndexOutOfBoundsException がスローされる可能性がある。
        // ・文字列からDate型に変換する際には、ParseException がスローされる可能性がある。
        // ・文字列を数値に変換する際には、NumberFormatException がスローされる可能性がある。
        //======================================================================

        try {
            // 予約情報管理ファイルから、登録されている
            // 全予約情報を取得する。
            List<String[]> reservationDataList = ■■■;

            // 取得した予約情報の検索
            for (■■■■ ■■■■■■■■ : ■■■■■■■■■■) {

                // 予約情報から予約番号を取り出し、整数(int)型に変換する。
                int reservationNumber = ■■■■■■■■■■■■■■■■■■;

                // 検索条件の予約番号と、取得した予約情報の予約番号が
                // 一致している場合、検索結果として取得した予約情報を返す。
                if (■■■■■■■■■■■■■■■■■■■■■■) {
                    ■■■ ■■■■■■■■;
                }

            }
            // 検索条件を満たす予約情報が存在しない場合、nullを返す。
            ■■■ ■■;

        } ■■■ (ArrayIndexOutOfBoundsException e) {
            // ArrayIndexOutOfBoundsExceptionをキャッチし、適切な例外型でラップして再スローする
            ■■■ ■■ ■■■■■■■■■■(
                        "予約管理ファイルのデータ(予約番号)の位置が不正です。", e);
        } ■■■ (NumberFormatException e) {
            // NumberFormatExceptionをキャッチし、適切な例外型でラップして再スローする
            ■■■ ■■■ ■■■■■■■■■■(
                        "予約管理ファイルのデータ(予約番号)の数値変換に失敗しました。", e);
        }
    }
*/

    /**
     * 次に付与されるべき予約番号を返す。<br>
     * 予約が1件も登録されていない場合、予約番号は1から始まります。
     *
     * @return 登録されている予約情報の件数+1の値
     * @throws DataIOException 予約管理ファイルの読み込みに失敗した場合
     */
/* UC03で実装します
    public int getNextSeq() throws DataIOException {
        // 予約情報管理ファイルから、登録されている全予約情報を取得する。
        List<String[]> reservationDataList = ■■■;

        //現在の予約数+1の値を返す。
        return ■■■■■■■■■■■■■ + 1;
    }
*/

    /**
     * 予約情報を登録する。<br><br>
     * 引数のString型配列に格納されている予約データを予約管理ファイルに登録する。
     *
     * @param  reservationData 登録対象の予約情報
     * @throws DataIOException 予約管理ファイルの書き込みに失敗した場合
     */
/* UC03で実装します
    private void save(String[] reservationData) throws DataIOException {
        try {
            ■■■■■■■■■■■■■■■■■;
        } catch (■■■■■ e) {
            throw new DataIOException("予約管理ファイルの書き込みに失敗しました。", e);
        }
    }
*/

    /**
     * 予約情報を永続化する。<br>
     * 引数のString型配列に格納されている予約データを永続化する。
     *
     * @param  newReservationData 新規に永続化する予約情報
     * @throws DataIOException 予約管理ファイルの書き込みに失敗した場合
     */
/* UC03で実装します
    public void addReservation(String[] newReservationData)
            throws DataIOException {
        ■■■■■■■■■■■■;
    }
*/
}
