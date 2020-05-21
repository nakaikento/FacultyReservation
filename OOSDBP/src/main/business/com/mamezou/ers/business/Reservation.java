/*
 * Reservation.java
 * Copyright (C) 2004 Mamezou Co., Ltd. All rights reserved.
 */

//  パッケージ宣言
package com.mamezou.ers.business;

// java.timeパッケージのインポート
// LocalDateTime (日時関連)
import java.time.LocalDateTime;

/**
 * 予約クラス
 * @author Mamezou
 */
public class Reservation {

    /** 予約番号 */
    private int number;

    /** 利用開始日時 */
    private LocalDateTime startDateTime;

    /** 利用終了日時 */
    private LocalDateTime endDateTime;

    /** 使用目的 */
    private String purpose;

    /** 予約した施設の施設番号 */
    private int equipmentNumber;

    /** 予約したユーザのユーザアカウント番号 */
    private int userAccountNumber;

    /** 予約したユーザ */
    private UserAccount reserveUser;

    /** 予約した施設 */
    private Equipment equipment;

    /**
     * コンストラクタ。
     */
    public Reservation() {
        super();
    }

    /**
     * コンストラクタ。<br>
     * @param number            予約番号
     * @param startDateTime         利用開始日時
     * @param endDateTime           利用終了日時
     * @param purpose           利用目的
     * @param equipmentNumber   予約対象の施設の施設番号
     * @param userAccountNumber 予約したユーザのユーザアカウント番号
     */
    public Reservation(int number, LocalDateTime startDateTime, LocalDateTime endDateTime,
            String purpose, int equipmentNumber, int userAccountNumber) {

        setNumber(number);
        setStartDateTime(startDateTime);
        setEndDateTime(endDateTime);
        setPurpose(purpose);
        setEquipmentNumber(equipmentNumber);
        setUserAccountNumber(userAccountNumber);

    }

    /**
     * 予約番号を設定する
     * @param number 予約番号
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * 予約番号を取得する
     * @return 予約番号
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * 利用開始日時を設定する
     * @param startDateTime 利用開始日時
     */
    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * 利用開始日時を取得する
     * @return 利用開始日時
     */
    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    /**
     * 利用終了日時を設定する
     * @param endDateTime 利用終了日時
     */
    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * 利用終了日時を取得する
     * @return 利用終了日時
     */
    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    /**
     * 利用目的を設定する
     * @param purpose 利用目的
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    /**
     * 利用目的を取得する
     * @return 利用目的
     */
    public String getPurpose() {
        return this.purpose;
    }

    /**
     * 施設番号を設定する
     * @param equipmentNumber 施設番号
     */
    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    /**
     * 施設番号を取得する
     * @return  施設番号
     */
    public int getEquipmentNumber() {
        return this.equipmentNumber;
    }

    /**
     * 予約を行ったユーザのユーザアカウント番号を設定する
     * @param userAccountNumber ユーザアカウント番号
     */
    public void setUserAccountNumber(int userAccountNumber) {
        this.userAccountNumber = userAccountNumber;
    }

    /**
     * 予約を行ったユーザのユーザアカウント番号を取得する
     * @return ユーザアカウント番号
     */
    public int getUserAccountNumber() {
        return this.userAccountNumber;
    }

    /**
     * 施設インスタンスを設定する
     * @param equipment 施設インスタンス
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * 施設インスタンスを取得する
     * @return 施設インスタンス
     */
    public Equipment getEquipment() {
        return this.equipment;
    }

    /**
     * 予約を行ったユーザを設定する
     * @param reserveUser 予約を行ったユーザのユーザアカウント
     */
    public void setUserAccount(UserAccount reserveUser) {
        this.reserveUser = reserveUser;
    }

    /**
     * 予約を行ったユーザを取得する
     * @return 予約を行ったユーザのユーザアカウント
     */
    public UserAccount getUserAccount() {
        return this.reserveUser;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDateTime == null) ? 0 : endDateTime.hashCode());
        result = prime * result
                + ((equipment == null) ? 0 : equipment.hashCode());
        result = prime * result + equipmentNumber;
        result = prime * result + number;
        result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
        result = prime * result
                + ((reserveUser == null) ? 0 : reserveUser.hashCode());
        result = prime * result
                + ((startDateTime == null) ? 0 : startDateTime.hashCode());
        result = prime * result + userAccountNumber;
        return result;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) obj;
        if (endDateTime == null) {
            if (other.endDateTime != null) {
                return false;
            }
        } else if (!endDateTime.equals(other.endDateTime)) {
            return false;
        }
        if (equipment == null) {
            if (other.equipment != null) {
                return false;
            }
        } else if (!equipment.equals(other.equipment)) {
            return false;
        }
        if (equipmentNumber != other.equipmentNumber) {
            return false;
        }
        if (number != other.number) {
            return false;
        }
        if (purpose == null) {
            if (other.purpose != null) {
                return false;
            }
        } else if (!purpose.equals(other.purpose)) {
            return false;
        }
        if (reserveUser == null) {
            if (other.reserveUser != null) {
                return false;
            }
        } else if (!reserveUser.equals(other.reserveUser)) {
            return false;
        }
        if (startDateTime == null) {
            if (other.startDateTime != null) {
                return false;
            }
        } else if (!startDateTime.equals(other.startDateTime)) {
            return false;
        }
        if (userAccountNumber != other.userAccountNumber) {
            return false;
        }
        return true;
    }

    /* (非 Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Reservation [endDateTime=").append(endDateTime).append(
                ", equipment=").append(equipment).append(", equipmentNumber=")
                .append(equipmentNumber).append(", number=").append(number)
                .append(", purpose=").append(purpose).append(", reserveUser=")
                .append(reserveUser).append(", startDateTime=").append(startDateTime)
                .append(", userAccountNumber=").append(userAccountNumber)
                .append("]");
        return builder.toString();
    }


}
