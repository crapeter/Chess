package chess;

public class Stuff {
  private static final String[] ones = {
      "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"
  };
  private static final String[] tens = {
      "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
  };
  private static final String[] hundreds = {
      "",
      "One Hundred", "Two Hundred", "Three Hundred", "Four Hundred", "Five Hundred",
      "Six Hundred", "Seven Hundred", "Eight Hundred", "Nine Hundred"
  };
  private static final String[] teens = {
      "",
      "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
      "Sixteen", "Seventeen", "Eighteen", "Nineteen"
  };

  public static void main(String[] args) {
    System.out.println(convert(9999999.99));
    System.out.println(convert(400.60));
    System.out.println(convert(4.09));
    System.out.println(convert(4.99));
    System.out.println(convert(4));
    System.out.println(convert(1));
    System.out.println(convert(0.50));
    System.out.println(convert(0));
  }

  private static String convert(double num) {
    StringBuilder result = new StringBuilder();
    String number = String.format("%.2f", num); // Ensure consistent formatting
    String[] parts = number.split("\\."); // Split dollars and cents

    String dollars = parts[0];
    String cents = parts.length == 1 ? "" : parts[1];

    // Convert dollars to a long integer
    long dollarsValue = Long.parseLong(dollars);

    // Handle billions, millions, thousands, and hundreds
    long billions = dollarsValue / 1_000_000_000;
    long millions = (dollarsValue % 1_000_000_000) / 1_000_000;
    long thousands = (dollarsValue % 1_000_000) / 1_000;
    long hundreds = dollarsValue % 1_000;

    // Converting the number to English
    if (billions > 0) {
      result.append(get_num((int) billions)).append("Billion ");
    }
    if (millions > 0) {
      result.append(get_num((int) millions)).append("Million ");
    }
    if (thousands > 0) {
      result.append(get_num((int) thousands)).append("Thousand ");
    }
    if (hundreds > 0) {
      result.append(get_num((int) hundreds));
    }
    if (result.toString().isEmpty()) {
      result.append("Zero ");
    }

    // Dealing with Cents
    if (Integer.parseInt(cents) == 0) {
      if (result.toString().strip().equals("One")) {
        result.append("Dollar and No Cents");
      } else {
        result.append("Dollars and No Cents");
      }
    } else {
      result.append("Dollars and ").append(get_num(Integer.parseInt(cents))).append("Cents");
    }
    return result.toString().strip();
  }

  private static String get_num(int num) {
    StringBuilder number = new StringBuilder();
    if (!hundreds[num / 100].isEmpty()) {
      number.append(hundreds[num / 100]).append(" ");
    }
    if (tens[(num % 100) / 10].equals("Ten") && !ones[num % 10].isEmpty()) {
      number.append(teens[num % 10]).append(" ");
    } else {
      if (!tens[(num % 100) / 10].isEmpty()) {
        number.append(tens[(num % 100) / 10]).append(" ");
      }
      if (!ones[num % 10].isEmpty()) {
        number.append(ones[num % 10]).append(" ");
      }
    }
    return number.toString();
  }
}