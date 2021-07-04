package at.electro.shop.service.transaction.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LocalDateTimeConverterTest {

  private LocalDateTimeConverter localDateTimeConverter = new LocalDateTimeConverter();

  @Test
  public void shouldConvertLocalDateToTimestamps() {
    Timestamp timestamp = localDateTimeConverter.convertToDatabaseColumn(
        LocalDateTime.of(2021, 5, 25, 0, 0, 0));

    Assertions.assertEquals("2021-05-25 00:00:00.0", timestamp.toString());
  }

  @Test
  public void shouldConvertTimestampToLocalDate() {
    LocalDateTime localDateTime = localDateTimeConverter
        .convertToEntityAttribute(Timestamp.valueOf("2021-05-25 00:00:00.0"));

    Assertions.assertEquals("2021-05-25T00:00", localDateTime.toString());
  }

  @Test
  public void shouldReturnNullIfGivenNullLocalDateTime() {
    Assertions.assertEquals(null, localDateTimeConverter.convertToDatabaseColumn(null));
  }

  @Test
  public void shouldReturnNullIfGivenNullTimestamp() {
    Assertions.assertEquals(null, localDateTimeConverter.convertToEntityAttribute(null));
  }
}