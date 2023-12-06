package com.sf.types;

public enum ENOperationStatuses {

    ERR_BALANCE_NF(-1, "Ошибка при выполнении операции 'Текущий баланс пользователя'"),
    ERR_BALANCE_NOT_ENOUGH_MONEY(0, "Недостаточно средств"),
    SUCCESS_BALANCE(1, "Успех");

    private final int code;
    private final String annotation;

    ENOperationStatuses(int code, String annotation) {
        this.code = code;
        this.annotation = annotation;
    }

    public int getCode() {
        return code;
    }

    public String getAnnotation() {
        return annotation;
    }
}
