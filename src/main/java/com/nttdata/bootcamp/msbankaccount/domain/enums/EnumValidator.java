package com.nttdata.bootcamp.msbankaccount.domain.enums;

public enum EnumValidator {

    /**Tipo de dato SI: Usado para cuando si se cumple la condición.*/
    YES("SI"),
    /**Tipo de dato SI: Usado para cuando no se cumple la condición.*/
    NO("NO"),
    /**Tipo de dato SI: Usado para cuando no se aplica una condición.*/
    NOTHING("NINGUNO");

    /**Valor del enum.*/
    private final String value;

    EnumValidator(String value) {
        this.value = value;
    }
    /**Obtiene el valor del enum.*/

    public String getValue() {
     return value;
  	}

}
