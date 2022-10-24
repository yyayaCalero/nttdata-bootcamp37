package com.nttdata.bootcamp.msbankaccount.domain.enums;

public enum EnumValidator {
	
    YES("SI"),
    NO("NO"),
    NOTHING("NINGUNO");

    
    private final String value;

    EnumValidator(String value) {
        this.value = value;
    }
	public String getValue() {
		return value;
	}

}
