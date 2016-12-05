package app;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import utils.DataReader;
import utils.FileManager;
import utils.LibraryUtils;
import data.Book;
import data.Library;
import data.LibraryUser;
import data.Magazine;

public class LibraryControl
{
	// zmienna do komunikacji z u¿ytkownikiem
	private DataReader dataReader;
	private FileManager fileManager;

	// "biblioteka" przechowuj¹ca dane
	private Library library;

	public LibraryControl()
	{
		dataReader = new DataReader();
		fileManager = new FileManager();
		try
		{
			library = fileManager.readLibraryFromFile();
			System.out.println("Wczytano dane biblioteki z pliku ");
		} catch (ClassNotFoundException | IOException e)
		{
			library = new Library();
			System.out.println("Utworzono now¹ bazê biblioteki.");
		}
	}

	/*
	 * G³ówna pêtla programu, która pozwala na wybór opcji i interakcjê
	 */
	public void controlLoop()
	{
		Option option = null;
		while (option != Option.EXIT)
		{
			try
			{
				printOptions();
				option = Option.createFromInt(dataReader.getInt());
				switch (option)
				{
				case ADD_BOOK:
					addBook();
					break;
				case ADD_MAGAZINE:
					addMagazine();
					break;
				case PRINT_BOOKS:
					printBooks();
					break;
				case PRINT_MAGAZINES:
					printMagazines();
					break;
				// DODANE
				case ADD_USER:
					addUser();
					break;
				case PRINT_USERS:
					printUsers();
					break;
				case EXIT:
					exit();
				}
			} catch (InputMismatchException e)
			{
				System.out.println("Wprowadzono niepoprawne dane, publikacji nie dodano");
			} catch (NumberFormatException | NoSuchElementException e)
			{
				System.out.println("Wybrana opcja nie istnieje, wybierz ponownie:");
			}
		}
		// zamykamy strumieñ wejœcia
		dataReader.close();
	}

	private void printOptions()
	{
		System.out.println("Wybierz opcjê: ");
		for (Option o : Option.values())
		{
			System.out.println(o);
		}
	}

	private void addBook()
	{
		Book book = dataReader.readAndCreateBook();
		library.addBook(book);
	}

	private void printBooks()
	{
		LibraryUtils.printBooks(library);
	}

	private void addMagazine()
	{
		Magazine magazine = dataReader.readAndCreateMagazine();
		library.addMagazine(magazine);
	}

	private void printMagazines()
	{
		LibraryUtils.printMagazines(library);
	}

	// DODANE
	private void addUser()
	{
		LibraryUser user = dataReader.readAndCreateLibraryUser();
		library.addUser(user);
	}

	// DODANE
	private void printUsers()
	{
		LibraryUtils.printUsers(library);
	}

	private void exit()
	{
		fileManager.writeLibraryToFile(library);
	}

	private enum Option
	{
		EXIT(0, "Wyjœcie z programu"),
		ADD_BOOK(1, "Dodanie ksi¹¿ki"),
		ADD_MAGAZINE(2,"Dodanie magazynu/gazety"),
		PRINT_BOOKS(3, "Wyœwietlenie dostêpnych ksi¹¿ek"),
		PRINT_MAGAZINES(4,"Wyœwietlenie dostêpnych magazynów/gazet"),
		ADD_USER(5,"Dodanie nowego u¿ytkownika"),
		PRINT_USERS(6,"Wyœwietlenie listy u¿ytkowników");

		private int value;
		private String description;

		Option(int value, String desc)
		{
			this.value = value;
			this.description = desc;
		}

		@Override
		public String toString()
		{
			return value + " - " + description;
		}

		public static Option createFromInt(int option) throws NoSuchElementException
		{
			Option result = null;
			try
			{
				result = Option.values()[option];
			} catch (ArrayIndexOutOfBoundsException e)
			{
				throw new NoSuchElementException("Brak elementu o wskazanym ID");
			}

			return result;
		}
	}
}