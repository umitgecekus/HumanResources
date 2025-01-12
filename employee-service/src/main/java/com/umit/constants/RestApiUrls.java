package com.umit.constants;

public class RestApiUrls {

    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";

    private static final String ROOT = DEV + VERSION;

    public static final String EMPLOYEE = ROOT + "/employee";

    public static final String ADD_EMPLOYEE =  "/add-employee";
    public static final String FIND_EMPLOYEE_BY_TOKEN = "/find-employee-by-token";

    public static final String UPDATE_EMPLOYEE =  "/update-employee";

    public static final String MANAGER_OR_ADMIN_UPDATE_EMPLOYEE = "/manager-or-admin-update-employee";

    public static final String GET_EMPLOYEES_BY_MANAGER_ID = "/get-employees-by-manager-id";
    public static final String FIND_BY_ID = "find-by-id";
    public static final String UPDATE_SALARY_EMPLOYEE =  "/update-salary-employee";

}
