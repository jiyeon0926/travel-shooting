package com.example.travelshooting.global.config.util;

import com.example.travelshooting.global.common.Const;

import java.time.LocalDate;

public class LockKeyUtil {

    private LockKeyUtil() {
    }

    public static String getReservationLockKey(LocalDate reservationDate, Long partId) {
        return Const.RESERVATION_LOCK_PREFIX + reservationDate + ":partId_" + partId;
    }
}
