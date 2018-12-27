import static org.junit.Assert.assertEquals;

import org.junit.Test;

import domain.Denomination;
import domain.Stack;

public class StackTests {

	@Test
	public void testStacks1AndAbove() {
		checkChipCounts(1.00, 0, 0, 0, 0, 0, 1);
		checkChipCounts(2.50, 0, 0, 0, 0, 1, 0);
		checkChipCounts(3.50, 0, 0, 0, 0, 1, 1);
		checkChipCounts(4.00, 0, 0, 0, 0, 0, 4);
	}

	@Test
	public void testStacks5AndAbove() {
		checkChipCounts(5.00, 0, 0, 0, 1, 0, 0);
		checkChipCounts(7.50, 0, 0, 0, 1, 1, 0);
		checkChipCounts(10.00, 0, 0, 0, 2, 0, 0);
		checkChipCounts(15.00, 0, 0, 0, 3, 0, 0);
		checkChipCounts(22.50, 0, 0, 0, 4, 1, 0);
	}

	@Test
	public void testStacks25AndAbove() {
		checkChipCounts(25.00, 0, 0, 1, 0, 0, 0);
		checkChipCounts(27.50, 0, 0, 1, 0, 1, 0);
		checkChipCounts(30.00, 0, 0, 1, 1, 0, 0);
		checkChipCounts(45.00, 0, 0, 1, 4, 0, 0);
	}

	@Test
	public void testStacks100AndAbove() {
		checkChipCounts(100.00, 0, 1, 0, 0, 0, 0);
		checkChipCounts(130.00, 0, 1, 1, 1, 0, 0);
		checkChipCounts(155.00, 0, 1, 2, 1, 0, 0);
	}

	@Test
	public void testStacks500AndAbove() {
		checkChipCounts(500.00, 1, 0, 0, 0, 0, 0);
		checkChipCounts(600.00, 1, 1, 0, 0, 0, 0);
		checkChipCounts(750.00, 1, 2, 2, 0, 0, 0);
		checkChipCounts(885.00, 1, 3, 3, 2, 0, 0);
		checkChipCounts(905.00, 1, 4, 0, 1, 0, 0);
		checkChipCounts(999.00, 1, 4, 3, 4, 0, 4);
		checkChipCounts(1050.00, 2, 0, 2, 0, 0, 0);
	}

	private void checkChipCounts(Double stackValue, int fiveHundreds, int hundreds, int twentyFives, int fives,
			int twoFifties, int ones) {
		Stack stack = new Stack(stackValue);
		assertEquals(fiveHundreds, stack.countTypeOfChip(Denomination.FIVE_HUNDRED));
		assertEquals(hundreds, stack.countTypeOfChip(Denomination.HUNDRED));
		assertEquals(twentyFives, stack.countTypeOfChip(Denomination.TWENTY_FIVE));
		assertEquals(fives, stack.countTypeOfChip(Denomination.FIVE));
		assertEquals(twoFifties, stack.countTypeOfChip(Denomination.TWO_FIFTY));
		assertEquals(ones, stack.countTypeOfChip(Denomination.ONE));
	}
}
