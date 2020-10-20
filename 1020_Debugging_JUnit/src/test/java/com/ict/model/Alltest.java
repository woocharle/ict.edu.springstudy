package com.ict.model;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// JunitTest를 한번에 하기.

@RunWith(Suite.class)
@SuiteClasses({Ex02_test.class, Ex02_test2.class, Ex03_test.class})
public class Alltest {


}
