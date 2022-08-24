package com.paychex.timekeeper.constant;

public class Messages {
    public static final String INVALID_ID = "ID not found";
    public static final String DELETED = "Successfully deleted";
    public static final String INVALID_ROLE = "Invalid role";
    public static final String INVALID_PW = "Invalid Password";
    public static final String WRONG_PW = "Password incorrect";
    public static final String ACTIVE_SHIFT = "Cannot start a new shift before completing previous shift";
    public static final String NO_ACTIVE = "User does not have an active shift";
    public static final String INVALID_START = "Cannot start a break while another break is open";
    public static final String DOUBLE_BREAK = "Cannot have multiple breaks during the same shift";
    public static final String INVALID_END = "Cannot end a break before starting";
    public static final String INCOMPLETE = "Break and Lunch must be complete before ending shift";
    public static final String UNAUTHORIZED = "You must have admin credentials to use this service";
    public static final String PW_REQUIRED = "Password is required for admin users";
}
