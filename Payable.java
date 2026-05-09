/*
 * Name: Max Ramos
 * Date: May 9, 2026
 * Assignment: Phase Final Project - Employee Management Application
 * Purpose: Interface used to define payment behavior for employee types.
 */

public interface Payable {
    /*
     * This method is required for any class that implements Payable.
     * Each employee type calculates pay differently, so the method is declared
     * here and implemented inside each subclass.
     */
    double calculatePay();
}