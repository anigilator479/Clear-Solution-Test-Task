package org.example;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class DateTimeApi {

  /**
   * Return the current date as a String depending on a query.
   * <p>
   * The query can be passed for the whole date or for it's part:
   * - FULL - current date as a whole: year, month, day of month
   * formatted as `YYYY-MM-DD` (a default return value);
   * - YEAR - current year;
   * - MONTH - name of the current month;
   * - DAY - current day of month;
   * In any other case throw DateTimeException.
   **/
  public String todayDate(DateTimePart datePart) {
    if (Objects.requireNonNull(datePart) == DateTimePart.DAY) {
      return String.valueOf(LocalDate.now().getDayOfMonth());
    } else if (datePart == DateTimePart.YEAR) {
      return String.valueOf(LocalDate.now().getYear());
    } else if (datePart == DateTimePart.MONTH) {
      return String.valueOf(LocalDate.now().getMonth());
    } else if (datePart == DateTimePart.FULL) {
      return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    throw new DateTimeException("Wrong date part" + datePart);
  }

  /**
   * Given an Array of 3 elements, where
   * - 1-st element is a `year`;
   * - 2-nd element is s `month`;
   * - 3-rd element is a `day of month`;
   * <p>
   * Return LocalDate built from these elements. If Array contains more than 3 elements - throw DateTimeException.
   */
  public LocalDate getDate(Integer[] dateParams) {
    if (dateParams.length < 3) {
      throw new DateTimeException("Wrong dateParams" + Arrays.toString(dateParams));
    }
    return LocalDate.of(dateParams[0], dateParams[1],dateParams[2]);
  }

  /**
   * Given the time and the number of hours to add, return the changed time.
   */
  public LocalTime addHours(LocalTime localTime, Integer hoursToAdd) {
    return localTime.plusHours(hoursToAdd);
  }

  /**
   * Given the time and the number of minutes to add, return the changed time.
   */
  public LocalTime addMinutes(LocalTime localTime, Integer minutesToAdd) {
    return localTime.plusMinutes(minutesToAdd);
  }

  /**
   * Given the time and the number of seconds to add, return the changed time.
   */
  public LocalTime addSeconds(LocalTime localTime, Integer secondsToAdd) {
    return localTime.plusSeconds(secondsToAdd);
  }

  /**
   * Given the date and the number of weeks to add, return the changed date.
   */
  public LocalDate addWeeks(LocalDate localDate, Integer numberOfWeeks) {
    return localDate.plusWeeks(numberOfWeeks);
  }

  /**
   * Given a random `someDate` date, return one of the following Strings:
   * - "`someDate` is after `currentDate`"
   * if `someDate` is in the future relating to the `current date`;
   * - "`someDate` is before `currentDate`"
   * if `someDate` is in the past relating to the `current date`;
   * - "`someDate` is today"
   * if `someDate` is today;
   */
  public String beforeOrAfter(LocalDate someDate) {
    if (someDate.isAfter(LocalDate.now())) {
      return someDate + " is after " + LocalDate.now();
    }
    if (someDate.isBefore(LocalDate.now())) {
      return someDate + " is before " + LocalDate.now();
    }
    if (someDate.equals(LocalDate.now())) {
      return someDate + " is today";
    }
    return someDate + " is in the past relating to the " + LocalDate.now();
  }

  /**
   * Given a String representation of a date and some timezone,
   * return LocalDateTime in this timezone.
   */
  public LocalDateTime getDateInSpecificTimeZone(String dateInString, String zone) {
    ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateInString, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    ZonedDateTime targetZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of(zone));
    return targetZonedDateTime.toLocalDateTime();
  }

  /**
   * Given some LocalDateTime, return an OffsetDateTime with the local time offset applied
   * (`+02:00` for Ukraine).
   * <p>
   * Example: we receive a LocalDateTime with a value `2019-09-06T13:17`.
   * We should return the OffsetDateTime with a value `2019-09-06T13:17+02:00`,
   * where `+02:00` is the offset for our local timezone.
   * <p>
   * OffsetDateTime is recommended to use for storing date values in a database.
   */
  public OffsetDateTime offsetDateTime(LocalDateTime localTime) {
    return localTime.atOffset(ZoneOffset.ofHours(2));
  }

  /**
   * Given a String formatted as `yyyyMMdd`,
   * return LocalDate object built from this String.
   */
  public LocalDate parseDate(String date) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd"));
  }

  /**
   * Given a String formatted as `d MMM yyyy` (MMM - Sep, Oct, etc),
   * return LocalDate object built from this String.
   */
  public LocalDate customParseDate(String date) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH));
  }

  /**
   * Given some LocalDateTime, return a String formatted as
   * `day(2-digit) month(full name in English) year(4-digit) hours(24-hour format):minutes`.
   * <p>
   * Example: "01 January 2000 18:00".
   */
  public String formatDate(LocalDateTime dateTime) {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm", Locale.ENGLISH));
  }
}
