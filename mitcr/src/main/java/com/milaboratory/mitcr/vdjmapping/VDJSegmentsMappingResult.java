/*
 * MiTCR <http://milaboratory.com>
 *
 * Copyright (c) 2010-2013:
 *     Bolotin Dmitriy     <bolotin.dmitriy@gmail.com>
 *     Chudakov Dmitriy    <chudakovdm@mail.ru>
 *
 * MiTCR is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.milaboratory.mitcr.vdjmapping;

/**
 * Result for joint V, J and D(optional) mapping
 *
 * @author Bolotin Dmitriy (bolotin.dmitriy@gmail.com)
 * @author Shugay Mikhail (mikhail.shugay@gmail.com)
 */
public class VDJSegmentsMappingResult {
    private final VJSegmentMappingResult[] vjResults;
    private final SegmentMappingResult dResult;

    public VDJSegmentsMappingResult(VJSegmentMappingResult[] vjResults, SegmentMappingResult dResult) {
        this.vjResults = vjResults;
        this.dResult = dResult;
    }

    public VJSegmentMappingResult getVResult() {
        return vjResults[0];
    }

    public VJSegmentMappingResult getJResult() {
        return vjResults[1];
    }

    public SegmentMappingResult getDResult() {
        return dResult;
    }

    /**
     * Get result by mapper index (0 - V, 1 - J)
     *
     * @param index mapper index
     */
    public SegmentMappingResult getResult(int index) {
        if (index < 0 && index > 2)
            throw new IndexOutOfBoundsException();
        if (index == 2)
            return dResult;
        return vjResults[index];
    }

    /**
     * Gets V and J mapping results in array
     *
     * @return array with V and J mapping results
     */
    public VJSegmentMappingResult[] getVJResultsArray() {
        return vjResults;
    }

    /**
     * Gets the cumulative score of V and J segment mappings
     *
     * @return cumulative score of V and J segment mappings
     */
    public float score() {
        float score = 0;
        for (int i = 0; i < 2; ++i)
            if (vjResults[i] != null)
                score += vjResults[i].getScore();
        return score;
    }

    /**
     * Tells if both V and J genes are mapped
     */
    public boolean isGood() {
        return vjResults[0] != null && vjResults[1] != null;
    }
}
