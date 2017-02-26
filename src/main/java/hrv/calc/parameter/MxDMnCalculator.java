package hrv.calc.parameter;

import common.ArrayUtils;
import hrv.RRData;

/**
 * Calculates the width of the variability (data.max - data.min)
 *  (see http://rainer-bartosch.de/HRV/Fachseminar_IMD_HRV.pdf)
 *  
 * @author Julian
 *
 */
public class MxDMnCalculator implements HRVDataProcessor {

	@Override
	public HRVParameter process(RRData data) {
		double max = ArrayUtils.max(data.getValueAxis());
		double min = ArrayUtils.min(data.getValueAxis());
		
		return new HRVParameter("MxDMn", max - min, "non");
	}

}