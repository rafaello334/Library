package data;

import java.io.Serializable;

public abstract class User implements Serializable
{
	private static final long serialVersionUID = 3187078139801519107L;

	private String firstName;
	private String lastName;
	private String pesel;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getPesel()
	{
		return pesel;
	}

	public void setPeselL(String pesel)
	{
		this.pesel = pesel;
	}

	public User(String firstName, String lastName, String pesel)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.pesel = pesel;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pesel == null) ? 0 : pesel.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (pesel == null)
		{
			if (other.pesel != null)
				return false;
		} else if (!pesel.equals(other.pesel))
			return false;
		if (firstName == null)
		{
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null)
		{
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return firstName + " " + lastName + " - " + pesel;
	}
}