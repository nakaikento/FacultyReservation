/*
 * 作成日: 2004/07/08
 *
 * この生成されたコメントの挿入されるテンプレートを変更するため
 * ウィンドウ > 設定 > Java > コード生成 > コードとコメント
 */
package com.mamezou.commons.utility;

/**
 * 文字列に関するユーティリティメソッドを提供するクラス<br>
 * 全てのメソッドはnullセーフ(引数にnullが渡されても
 * NullPointerExceptionが発生しない)となっています。<br>
 * また、本クラスの全てのメソッドはクラスメソッドとして提供
 * されるため、インスタンス化できない仕様となっています。
 * @author Mamezou
 */
public class StringUtils {

    /**
     * 引数paramが空文字列("")でないことをチェックする。<br>
     * 引数の文字列長が0の場合、falseを返す。
     * @param str 文字列
     * @return 空文字列の場合 true。
     */
    public static boolean isNotEmpty(String str) {
        if (str == null) {
            return false;
        }
        return str.length() != 0;
    }

    /**
     * 引数strが空文字列("")であるかチェックする。<br>
     * @param str チェック対象の文字列
     * @return str が空文字列の場合 true。
     *          str が null または 空文字列ではない場合 false。
     */
    public static boolean isEmpty(String str) {
        if (str == null) {
            return false;
        }
        return str.length() == 0;
    }

    /**
     * 文字列が数字からのみ構成されるかチェックする。
     * @param str チェック対象の文字列
     * @return str が数字だけで構成される場合 true。
     *          str が null または 数字以外の文字が含まれる場合 false。
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("[0-9]*");
    }

    /**
     * 文字列長のチェックを行う。<br>
     * 指定された文字列の長さが指定された長さと等しい場合 true を
     * 返却する。それ以外の場合 false を返却する。
     * @param length   文字列長
     * @param str    文字列
     * @return strの文字列長がlengthと等しい場合 true。
     *          strがnullまたはlengthと等しくない場合 false。
     */
    public static boolean checkLength(int length, String str) {
        if (str == null) {
            return false;
        }
        return str.length() == length;
    }

    /**
     * 文字列長の範囲チェックを行う。<br>
     * 指定された文字列の長さが範囲内に収まっている場合 true を
     * 返却する。それ以外の場合は false を返却する。
     * @param min    文字列長の最小桁数
     * @param max    文字列長の最大桁数
     * @param str  文字列
     * @return strの文字列長がmin以上max以下の場合 true。
     *          strがnull または min 未満 または max より大きい場合 false。
     */
    public static boolean checkRangeOfLength(int min, int max, String str) {
        if (str == null) {
            return false;
        }
        //引数の文字列長を取得
        int length = str.length();

        //引数の文字列長が指定した最小値、最大値内にあるかチェック
        return length >= min && length <= max;
    }
}
