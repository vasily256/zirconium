package com.bellintegrator.zirconium.dao.impl;

class CustomQueries {

    /**
     * SQL-запрос на удаление неиспользуемых телефонных номеров
     */
    static final String DELETE_UNUSED_PHONES
            = "DELETE " +
              "FROM phone " +
              "WHERE id IN (:id) " +
                  "AND NOT EXISTS (SELECT phone_id " +
                                  "FROM office_phone " +
                                  "WHERE phone_id = id) " +
                  "AND NOT EXISTS (SELECT phone_id " +
                                  "FROM user_phone " +
                                  "WHERE phone_id = id) " +
                  "AND NOT EXISTS (SELECT phone_id " +
                                  "FROM organization_phone " +
                                  "WHERE phone_id = id);";
}
