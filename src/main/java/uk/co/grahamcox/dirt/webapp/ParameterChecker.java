/****************************************************************************************************************
 * Copyright (c) 2015 OCLC, Inc. All Rights Reserved.
 * <p>
 * OCLC proprietary information: the enclosed materials contain
 * proprietary information of OCLC, Inc. and shall not be disclosed in whole or in
 * any part to any third party or used by any person for any purpose, without written
 * consent of OCLC, Inc.  Duplication of any portion of these materials shall include this notice.
 ******************************************************************************************************************/
package uk.co.grahamcox.dirt.webapp;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Mechanism to check for parameters that are required to be present but aren't
 */
public final class ParameterChecker {
    /** The acutal collection of missing parameters */
    private final Set<String> missingParameters = new HashSet<>();

    /**
     * Private constructor, forcing users to use the static method instead
     */
    private ParameterChecker() {

    }

    /**
     * Check the given parameter
     * @param name the name of the parameter
     * @param value the value of the parameter
     * @return this, for chaining
     */
    public ParameterChecker and(final String name, final Optional<?> value) {
        if (!value.isPresent()) {
            missingParameters.add(name);
        }
        return this;
    }

    /**
     * Check the given parameter
     * @param name the name of the parameter
     * @param value the value of the parameter
     * @return this, for chaining
     */
    public static ParameterChecker check(final String name, final Optional<?> value) {
        return new ParameterChecker().and(name, value);
    }

    /**
     * If we've recorded any missing parameters then throw a MissingParameterException
     * @throws MissingParameterException if we've recorded any missing parameters
     */
    public void andThrow() {
        if (!missingParameters.isEmpty()) {
            throw new MissingParameterException(missingParameters);
        }
    }
}
