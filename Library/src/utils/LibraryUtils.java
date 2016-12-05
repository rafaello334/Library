package utils;

import data.Book;
import data.Library;
import data.Magazine;

public class LibraryUtils
{

	public static void printBooks(Library lib)
	{
		printPublications(lib, Book.class);
	}

	public static void printMagazines(Library lib)
	{
		printPublications(lib, Magazine.class);
	}

	private static void printPublications(Library lib, Class cl)
	{
		long countMagazines = lib.getPublications().values().stream().filter(cl::isInstance)
				.sorted(new Library.AlphabeticalComparator()).peek(System.out::println).count();

		if (countMagazines == 0)
		{
			System.out.println("W bibliotece nie znaleziono publikacji typu " + cl.getSimpleName());
		}
	}

	public static void printUsers(Library lib)
	{
		lib.getUsers().values().stream().sorted((a, b) -> a.getLastName().compareTo(b.getLastName()))
				.forEach(System.out::println);
	}
}