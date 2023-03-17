package kr.or.ddit.servlet05;

public class TempVO {
	private String key;
	private String value;
	
	public TempVO() {}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "tempVO [key=" + key + ", value=" + value + "]";
	}

}
