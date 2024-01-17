INSERT INTO USERS
(CREATED_TIME, UPDATE_TIME, ENABLED, FIRST_NAME, LAST_NAME, PASSWORD,
 REFRESH_TOKEN, TOKEN_EXPIRY_DATE, USER_UUID, USERNAME, LANGUAGE_ID)
VALUES (CURRENT_DATE, CURRENT_DATE, TRUE, 'abed', 'alrhman',
        '$2a$10$NTrmHL6sQv/Gwi9t/mAffObNU4YBAya8s0cungPVCDeBl1xweg8P6',
        null, null, 'f363b95f-bdfc-47d7-8f3f-50b903735d8a', 'abdalrhman@gmail.com', 'en'),
       (CURRENT_DATE, CURRENT_DATE, TRUE, 'moad', 'alzoul',
        '$2a$10$NTrmHL6sQv/Gwi9t/mAffObNU4YBAya8s0cungPVCDeBl1xweg8P6',
        null, null, '925c561f-309f-4bda-98ad-37ec2d71cf88', 'moath@gmail.com', 'en'),
       (CURRENT_DATE, CURRENT_DATE, TRUE, 'omar', 'ismail',
        '$2a$10$NTrmHL6sQv/Gwi9t/mAffObNU4YBAya8s0cungPVCDeBl1xweg8P6',
        null, null, '925c561f-309f-4bda-98ad-37ec2d71cf74', 'omar@gmail.com', 'en');