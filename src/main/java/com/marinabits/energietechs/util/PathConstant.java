package com.marinabits.energietechs.util;

/**
 * @author: Abd-alrhman Alkraien.
 * @Date: 10/4/2023
 * @Time: 9:01 PM
 */
public class PathConstant {

    public static final String ID_KEY = "/{id}";

    public static final String LEARNING_ASSETS = "/learning-assets";

    public static final String PROJECT_PATH = "/project";

    public static final String ADMIN_PATH = "/admin";

    public static final String PREPARE_BUSINESS_LAYOUT_PATH = "/prepare-business-layout";
    private static final String DRAFT = "/draft";

    public static final String DRAFT_PROJECT_PATH = DRAFT + PROJECT_PATH;
    public static final String DRAFT_BUSINESS_LAYOUT_PATH = DRAFT + "/business-layout";

    public static final String DRAFT_COMPONENT_DRAW_PATH = DRAFT + "/component-draw";

    public static final String SHARE_PROJECT = "/share-project";

    public static final String USER_ID = "/{userId}";
    public static final String PROJECT_ID = "/{projectId}";

    public static final String SUBMIT_PATH = "/submit";

    public static final String DRAFT_CONFIGURATION = DRAFT + "/configuration";
    public static final String LOAD_CONFIGURATION_PATH = DRAFT + "/load-configuration";

    public static final String PRE_DEFINED_PROFILE_PATH = "/pre-defined-profiles";

    public static final String CALCULATE_PRE_DEFINED_PROFILE_MONTHLY_AVERAGE_PATH = "/calculate-monthly-avg";
    public static final String BLOCK_DIAGRAM_PATH = "/block-diagram";

    public static final String DRAW_COMPONENT_PATH = "/draw-component";

    public static final String COMPONENT_ID_KEY = "/{componentId}";

    public static final String LOAD_PATH = "/load";

    public static final String LOAD_ID = "/{loadId}";
    public static final String SAMPLE_LOAD_PATH = "/sample-load";

    public static final String UPLOAD_LOAD = "/upload-load";

    public static final String CALCULATE = "/calculate";

    public static final String GENERATE_PATH = "/generate-load";


    public static final String ADD_LOAD_PREFIX_PATH = PathConstant.DRAW_COMPONENT_PATH + PathConstant.COMPONENT_ID_KEY + PathConstant.LOAD_PATH + PathConstant.LOAD_ID;
}
