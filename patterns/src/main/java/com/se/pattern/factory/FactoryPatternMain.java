package com.se.pattern.factory;

/**
 * Класс FactoryPatternMain — основной, обращающийся к классу AccountFactory
 * для получения объектов Account . Он передает фабричному методу аргумент, со-
 * держащий информацию о типе счета, например
 * SAVING («сберегательный») или
 * CURRENT («текущий»).
 * Класс AccountFactory возвращает объект типа, переданного  фабричному методу.
 */
public class FactoryPatternMain {

    public static void main(String[] args) {
        AccountFactory accountFactory = new AccountFactory();
// Получаем объект класса SavingAccount и вызываем
// его метод accountType()
        Account savingAccount = accountFactory.getAccount("SAVING");
// Вызываем метод accountType класса SavingAccount
        savingAccount.accountType();
// Получаем объект класса CurrentAccount и вызываем
// его метод accountType()
        Account currentAccount = accountFactory.getAccount("CURRENT");
// Вызываем метод accountType класса CurrentAccount
        currentAccount.accountType();
    }
}
