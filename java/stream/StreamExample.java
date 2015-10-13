package kghoon.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class StreamExample {

	private List<Transaction> transactions;
	private List<Person> persons;
	
	@Test
	public void testReduce() {
		List<Integer> numbers = Arrays.asList(new Integer[]{1, 2, 3});
		int sum = numbers.stream().reduce(0, (a, b) -> a + b);
		assertEquals(sum, 6);
		
		int mul = numbers.stream().reduce(1, (a,b)->a*b);
		assertEquals(mul, 6);
		
		int max = numbers.stream().reduce(1, Integer::max);
		assertEquals(max, 3);
	}
	
	@Test
	public void testFindingAndMatching() {
		assertFalse(transactions.stream().allMatch(t -> t.getValue() > 100));

		Optional<Transaction> optTransaction = transactions.stream()
			.filter(t -> t.getType() == Transaction.GROCERY)
			.findAny();
		
		assertEquals(optTransaction.get().getValue(), 50);
	}
	
	@Before
	public void prepareTransactions() {
		transactions = Arrays.asList(new Transaction[]{
				new Transaction(Transaction.OTHER, 0),
				new Transaction(Transaction.GROCERY, 50),
				new Transaction(Transaction.OTHER, 100),
				new Transaction(Transaction.GROCERY, 150),
				new Transaction(Transaction.OTHER, 200)
		});
	}
	
	@Test
	public void testInfiniteStream() {
		Stream<Integer> numbers = Stream.iterate(0, n -> n + 10);
		int sum = numbers.limit(5).reduce(0, (a, b) -> a + b);
		assertEquals(sum, 100);
	}
	
	@Test
	public void testNumericStream() {
		int sum = transactions.stream()
			.mapToInt(Transaction::getValue)
			.sum();
		
		assertEquals(sum, 500);
		
		IntSummaryStatistics statistics = transactions.stream()
			.mapToInt(Transaction::getValue)
			.summaryStatistics();
		
		System.out.println(statistics.toString());
	}
	
	@Test
	public void testFiltering() {
		assertEquals(transactions.stream()
				.filter(t -> t.getType() == Transaction.GROCERY)
				.count(), 2);
		
		assertEquals(transactions.stream()
			.limit(2).count(), 2);
		
		assertEquals(transactions.stream().distinct().count(), transactions.size());
		
		assertEquals(transactions.stream().skip(2).count(), transactions.size() - 2);
	}
	
	@Before
	public void preparePersons() {
		persons = Arrays.asList(new Person[]{
			new Person("Jihoon", 34),
			new Person("Sungeun", 34),
			new Person("Youngoh", 33),
			new Person("Yongkyu", 33)
		});
	}
	
	@Test
	public void testMap() {
		assertEquals(transactions.stream().mapToInt(s -> s.getValue()).sum(), 500);
	}
	
	@Test
	public void testCollect() {
		List<Person> filtered = persons.stream()
			.filter(p -> p.getName().startsWith("Y"))
			.collect(Collectors.toList());
		
		assertEquals(filtered.size(), 2);
		System.out.println(filtered);
		
		Map<Integer, List<Person>> personsByAge = persons.stream().collect(Collectors.groupingBy(p -> p.getAge()));
		personsByAge.forEach((age, p) -> System.out.format("age:%s %s ", age, p));
	}
}

class Person {
	private String name;
	private int age;
	
	Person() {}
	
	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		return new StringBuffer()
				.append(getClass().getSimpleName()).append("{")
				.append("name=").append(name)
				.append(", age=").append(age)
				.toString();
	}
}

class Transaction {
	static final int GROCERY = 1;
	static final int OTHER = 2;
	
	private int type;
	private int value;
	
	Transaction() {}
	
	Transaction(int type, int value) {
		this.type = type;
		this.value = value;
	}
	
	public int getType() {
		return type;
	}
	
	public int getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("Transaction{")
				.append("type:").append(type).append(", ")
				.append("value:").append(value)
				.append("}").toString();
	}
}
