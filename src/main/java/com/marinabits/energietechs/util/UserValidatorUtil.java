package com.marinabits.energietechs.util;

import com.marinabits.energietechs.infra.exception.BusinessRoleException;
import com.marinabits.energietechs.infra.exception.EnergiTechException;
import com.marinabits.energietechs.infra.exception.error.BusinessErrorCodes;
import com.marinabits.energietechs.infra.exception.error.ErrorCategories;
import com.marinabits.energietechs.infra.util.WebUtils;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Abd-alrhman Alkraien.
 * @Date: 10/7/2023
 * @Time: 10:08 PM
 */
@Log4j2
public class UserValidatorUtil {

    public static Map<String, Object> checkUsersAuthority(final List<Integer> userIds) throws BusinessRoleException {


        isAuthenticated();

        Map<String, Object> userInfo = WebUtils.getAuthorizationHeader();


        return userInfo;
    }

    public static final Map<String, Object> getCurrentUser() throws BusinessRoleException {

        isAuthenticated();

        return WebUtils.getAuthorizationHeader();
    }


    private static void isAuthenticated() throws BusinessRoleException {

        log.info("check user authority");
        if (Objects.isNull(WebUtils.getAuthorizationHeader().get(Constant.USER_ID_MAP_KEY))) {

            throw new EnergiTechException(ErrorCategories.BUSINESS_ROLE_ERROR,
                    BusinessErrorCodes.PROCESS_NOT_AUTHORIZED);
        }
    }


}
