package com.umit.constants;

public class RestApiUrls {

    private static final String VERSION ="/v1";
    private static final String DEV="/dev";

    private static final String ROOT= DEV + VERSION;
    public static final String COMPANY = ROOT + "/company";

    public static final String CREATE= "/create";
    public static final String UPDATE= "/update";
    public static final String GET_ALL= "/get-all";
    public static final String GET_ALL_COMPANY_COUNT= "/get-all-company-count";
    public static final String GET_ALL_PENDING_COMPANIES= "/get-all-pending-companies";
    public static final String APPROVE_COMPANY= "/approve-company";
    public static final String REJECT_COMPANY= "/reject-company";

}
