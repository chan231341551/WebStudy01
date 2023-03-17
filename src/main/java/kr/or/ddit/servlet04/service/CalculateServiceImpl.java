package kr.or.ddit.servlet04.service;

public class CalculateServiceImpl implements CalculateService {

	
	@Override
	public int calcultatePlus(int x , int y) {
		int rst = x + y;
		return rst;
	}

	@Override
	public int calcultateMinus(int x , int y) {
		int rst = x - y;
		return rst;
	}

	@Override
	public int calcultateMultiple(int x , int y) {
		int rst = x * y;
		return rst;
	}

	@Override
	public int calcultateDivide(int x , int y) {
		int rst = x / y;
		return rst;
	}

}
