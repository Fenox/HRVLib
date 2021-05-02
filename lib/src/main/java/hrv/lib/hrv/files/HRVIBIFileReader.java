package hrv.lib.hrv.files;

import hrv.lib.common.ArrayUtils;
import hrv.lib.hrv.RRData;
import hrv.lib.units.TimeUnit;

import java.io.*;
import java.util.*;

public class HRVIBIFileReader {

	/**
	 * Reads the .ibi file that is specified in {@code filePath} and
	 * returns the data in form of a {@code List<Double>}
	 * @param filePath File to read
	 * @return Read data.
	 * @throws IOException throws IOException
	 */
	public RRData readIBIFile(String filePath, TimeUnit unit) throws IOException {
		List<Double> rr = new ArrayList<>();
		
		try(var reader = new BufferedReader(new FileReader(filePath))) {
			for(String line; (line = reader.readLine()) != null; ) {
				rr.add(Double.parseDouble(line));
			}
		}
		
		return createFromRRInterval(ArrayUtils.toPrimitive(rr, 0.0), unit);
	}
	
	private RRData createFromRRInterval(double[] rrInterval, TimeUnit unit) {
		var rrTimeAxis = new double[rrInterval.length];
		for (var i = 1; i < rrInterval.length; i++) {
			rrTimeAxis[i] = rrTimeAxis[i - 1] + rrInterval[i - 1];
		}

		return new RRData(rrTimeAxis, unit, rrInterval, unit);
	}
}
