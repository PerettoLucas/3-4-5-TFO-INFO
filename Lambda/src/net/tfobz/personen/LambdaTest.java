package net.tfobz.personen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaTest
{
	
	static Person p1 = new Person("Lucas",17);
	static Person p2 = new Person("Filo",17);
	static Person p3 = new Person("KAhnung",33);
	static Person p4 = new Person("Sep", 88);
	static Person p5 = new Person("Franz", 78);
	static Handwerker h1 = new Handwerker("simon",30,"hydrauliker");
	static Handwerker h2 = new Handwerker("tommy",60,"Freiberufler");
	
	public static void main(String[] args)
	{
		System.out.println("Übung 3");
		LambdaTest.ListToStreamOutput();
		
		System.out.println("Übung 4");
		LambdaTest.StreamBuilderOutput();
		
		System.out.println("Übung 5");
		LambdaTest.IntStreamoutput();
		
		System.out.println("Übung 6");
		LambdaTest.DoubleStreamOutput();
		
		System.out.println("Übung 7");
		LambdaTest.GroesstenWert();
		
		System.out.println("Übung 9");
		LambdaTest.summeIntOutput();
	
		System.out.println("Übung 11");
		LambdaTest.kommaSepariert();
		
		System.out.println("Übung 12");
		LambdaTest.insUndendliche();
		
		System.out.println("�bung 10");
		LambdaTest.personlistInfo();
		
		System.out.println("Übung 13");
		LambdaTest.gruppieren();
		
		System.out.println("Übung 14");
		LambdaTest.gruppierenNachNamenLaenge();
		
	}

	public static void ListToStreamOutput()
	{
		java.util.List<Object> list = new ArrayList<Object>();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(h1);
		list.add(h2);
		
		list.stream().filter(x -> x instanceof Handwerker).forEach(System.out::println);
	}
	
	public static void StreamBuilderOutput()
	{
		Stream.Builder<Person> builder = Stream.builder();
		
		builder.add(h1);
		builder.add(h2);
		builder.add(p1);
		builder.add(p2);
		builder.add(p3);
		
		System.out.println(builder.build().filter(x -> x instanceof Handwerker).count());
	}
	
	public static void IntStreamoutput()
	{
		System.out.println(IntStream.range(100,201).filter(x -> x % 2 == 0).sum());
		System.out.println(IntStream.range(100,201).filter(x -> x % 2 == 0).average().getAsDouble());
	}
	
	public static void DoubleStreamOutput() 
	{
		Stream.of(1.0,3.0,6.0,8.0)
					.mapToInt(Double::intValue)
					.mapToObj(i -> "a" + i)
				    .forEach(System.out::println);
	}
	
	public static void GroesstenWert()
	{
		Random rd = new Random(); 
		int[] arr = new int[10];
		for (int i = 0; i < arr.length; i++) 
			arr[i] = rd.nextInt(); 
		
		
		
		IntStream.of(arr).forEach(System.out::println);
		
		System.out.print("Größter Wert:");
		IntStream stream = IntStream.of(arr);
		
		System.out.println(stream
				.reduce((x,y) -> x > y ? x : y)
				.getAsInt());
		
		System.out.println("Übung 8");
		
		IntStream stream2 = IntStream.of(arr);
		
		List<Integer> list = stream2.boxed().collect(Collectors.toList());	
		
		System.out.println(list.toString());
	}
	
	public static void summeIntOutput() 
	{
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(1);
		list.add(2);
		list.add(100);
		list.add(3);
		
		System.out.println("SUM : " + list.stream().collect(Collectors.summarizingInt(x -> (int) x)).getSum());
		
	}
	
	public static void kommaSepariert()
	{
		IntStream
				.range(1,201)
				.mapToObj(z -> z + "")
				.reduce((x,y) -> x +" , "+ y)
				.ifPresent(System.out::println);
	}
	
	public static void insUndendliche()
	{
		IntStream
				.iterate(0,x -> x + 1)
				.skip(11)
				.limit(10)
				.forEach(System.out::println);
	}
	
	public static void personlistInfo()
	{
		List<Person> list = new ArrayList<>();
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		
		System.out.println("Anzahl Personen : " + list.stream().collect(Collectors.counting()).intValue());
		
		System.out.println("Summe des Alters aller Personen : " + list.stream().collect(Collectors.summarizingInt(Person::getAlter)).getSum());
		
		System.out.println("Minimum des Alters aller Personen : " + list.stream().collect(Collectors.summarizingInt(x -> x.getAlter())).getMin());
		
		System.out.println("Maximum des Alters aller Personen : " + list.stream().collect(Collectors.summarizingInt(x -> x.getAlter())).getMax());
		
		System.out.println("Durchschnitt des Alters aller Personen : " + list.stream().collect(Collectors.summarizingInt(x -> x.getAlter())).getAverage());
	}
	
	
	public static void gruppieren()
	{
		List<Person> list = new ArrayList<>();
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		
		
		Supplier<Stream<Person>> supplier = () -> list.stream();
		
		
		System.out.println(supplier.get()
								.map(p -> { 
									p.getName().charAt(0);
									return p;
								})
								.collect(Collectors.groupingBy(p -> p.getName().charAt(0))));
		
		System.out.println("gruppieren nach instance of Handwerker : ");
		
		System.out.println(supplier.get()
				.collect(Collectors.groupingBy(p -> p.getClass().getSimpleName()))
				);
		
		
		System.out.println("Gruppieren nach anfangsbuchstabe , count : ");
		
		System.out.println(supplier.get().collect(Collectors.groupingBy(p -> p.getName().charAt(0), Collectors.counting())));
		
	}
	
	public static void gruppierenNachNamenLaenge()
	{
		
		List<Person> list = new ArrayList<>();
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		
		List<Person> list2 = list.stream().filter(p -> p.getName().length() > 3).collect(Collectors.toList());
		
		list2.forEach(System.out::println);
		
	}
	
	
}
