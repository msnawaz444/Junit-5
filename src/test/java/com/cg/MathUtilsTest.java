package com.cg;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("when running MathUtils")
class MathUtilsTest {

	MathUtils mathUtils;
	TestInfo testInfo;
	TestReporter testReporter;

	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to be run before all");
	}

	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("Running "+ testInfo.getDisplayName()+ " with args "+ testInfo.getTags());
	}

	@AfterEach
	void cleanup() {
		System.out.println("Cleaning up....");
	}

	@Nested
	@DisplayName("add method")
	@Tag("Math")
	class AddTest {

		@Test
		@DisplayName("when adding the two positive number")
		void testAddPositive() {
			assertEquals(2, mathUtils.add(1, 1), "should return the right sum");
		}

		@Test
		@DisplayName("when adding the two negative number")
		void testAddNegative() {
			assertEquals(-2, mathUtils.add(-1, -1),()-> "should return the right sum");
		}
	}

	@Test
	@Tag("Math")
	@DisplayName("Testing multiply method")
	void testMultiply() {
		// assertEquals(4, mathUtils.multiply(2, 2), "should return the right product");
		assertAll(() -> assertEquals(4, mathUtils.multiply(2, 2)), 
				  () -> assertEquals(0, mathUtils.multiply(2, 0)),
				  () -> assertEquals(-2, mathUtils.multiply(2, -1)));
	}

	@Test
	@Tag("Math")
	@EnabledOnOs(OS.WINDOWS)
	@DisplayName("Testing divide method")
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtils.divide(1, 0), "divide by zero should throw");
	}

	@RepeatedTest(3)
	@Tag("Circle")
	@DisplayName("Testing calculate area of circle")
	void testComputeCircleRadius(RepetitionInfo repetitionInfo) {
		assertEquals(314.1592653589793, mathUtils.computeCircleArea(10), "should return right area");
	}

	@Test
	@Disabled
	@DisplayName("TDD method should not run")
	void testDisabled() {
		fail("This test should be disabled");
	}
}
