package hrv.calc.frequency;

import hrv.calc.frequency.psd.PowerSpectrum;

public class HFnuCalculator implements HRVPowerSpectrumProcessor {

	double lfLowerLimit;
	double lfUpperLimit;
	double hfLowerLimit;
	double hfUpperLimit;
	
	public HFnuCalculator(double lfLowerLimit, double lfUpperLimit, double hfLowerLimit, double hfUpperLimit) {
		this.lfLowerLimit = lfLowerLimit;
		this.lfUpperLimit = lfUpperLimit;
		this.hfLowerLimit = hfLowerLimit;
		this.hfUpperLimit = hfUpperLimit;
	}
	
	@Override
	public double process(PowerSpectrum ps) {
		
		PowerSpectrumIntegralCalculator calcLf = new PowerSpectrumIntegralCalculator(lfLowerLimit, lfUpperLimit);
		double lf = calcLf.process(ps);
		
		PowerSpectrumIntegralCalculator calcHf = new PowerSpectrumIntegralCalculator(hfLowerLimit, hfUpperLimit);
		double hf = calcHf.process(ps);
		
		
		return hf / (lf + hf);
	}

}