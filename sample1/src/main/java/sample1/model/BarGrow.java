package sample1.model;

public enum BarGrow {
	BAR,GROW;
	
	public static BarGrow getBarGrow(String bargrow) {
		for (BarGrow ret : BarGrow.values()) {
			if (bargrow.equalsIgnoreCase(ret.name())) {
				return ret;	
			}
		}
		throw new RuntimeException("Tipo invalido");
	}
}
