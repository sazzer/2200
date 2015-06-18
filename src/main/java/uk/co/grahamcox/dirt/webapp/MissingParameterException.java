/****************************************************************************************************************
 * Copyright (c) 2015 OCLC, Inc. All Rights Reserved.
 * <p>
 * OCLC proprietary information: the enclosed materials contain
 * proprietary information of OCLC, Inc. and shall not be disclosed in whole or in
 * any part to any third party or used by any person for any purpose, without written
 * consent of OCLC, Inc.  Duplication of any portion of these materials shall include this notice.
 ******************************************************************************************************************/
package uk.co.grahamcox.dirt.webapp;

import java.util.Set;

/**
 * Exception to throw when mandatory parameters are missing
 */
public class MissingParameterException extends RuntimeException {
    /** The names of the missing parameters */
    private final Set<String> parameterNames;

    /**
     * Construct the exception
     * @param parameterNames the names of the missing parameters
     */
    public MissingParameterException(final Set<String> parameterNames) {
        this.parameterNames = parameterNames;
    }

    /**
     * Get the missing parameter names
     * @return the missing parameter names
     */
    public Set<String> getParameterNames() {
        return parameterNames;
    }
}
